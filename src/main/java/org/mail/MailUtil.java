package org.mail;

import com.fasterxml.jackson.databind.JsonNode;
import org.io.FileReader;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MailUtil {
    private static Properties getMailHostProperties() {

        Properties mailProperties = new FileReader().getConfig("mail.config");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailProperties.get("smtphost"));
        props.put("mail.smtp.port", mailProperties.get("smtpport"));

        return props;
    }

    /**
     * Read secret config
     * @param filePath filepath + filename, "recources" is already given. e.g "files/message.txt"
     * @return secrets - Instance of properties file
     */
    static Properties getSecrets(String filePath) {
        Properties secrets = new FileReader().getConfig(filePath);

        return secrets;
    }

    private static Session getSession() {
        Properties props = getMailHostProperties();
        Properties secrets = getSecrets("secret/pass.config");

        String username = (String) secrets.get("from");
        String password = (String) secrets.get("password");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return  session;
    }

    /**
     * @param jsonNode Node with all mail contents in a json object
     * @return true, if mail was successfully send
     */
    public static boolean sendMailTo(JsonNode jsonNode) {
        Session session = getSession();

        // Read contents of email from json file
        String from = jsonNode.get("from").asText();
        String subject = jsonNode.get("subject").asText();
        String message = jsonNode.get("message").asText();
        String mailTo = jsonNode.get("mailTo").asText();
        // read list of attachment files
        List<String> filePathList = new ArrayList<>();
        jsonNode.get("filePath").elements().forEachRemaining(element -> filePathList.add(element.asText()));

        try {
            // create a message with headers
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(mailTo)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // container for MimeBodyParts
            Multipart mpart = new MimeMultipart();

            // create message body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html; charset=utf-8");
            mpart.addBodyPart(messageBodyPart);

            // attach files to be attached to the mail
            for (String filePath : filePathList) {
                File file = new FileReader().getResourceFile(filePath);
                try {
                    MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                    attachmentBodyPart.attachFile(file);
                    mpart.addBodyPart(attachmentBodyPart);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            msg.setContent(mpart);

            // send the message
            Transport.send(msg);

        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
        }

        // because there is no possibility to get an instant response during runtime,
        // i have to assume a value. I prefer to choose "true"
        return true;
    }
}
