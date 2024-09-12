package io;

import com.fasterxml.jackson.databind.JsonNode;
import org.io.FileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    @DisplayName("getConfig")
    void getConfig() {
        FileReader fileReader = new FileReader();
        Properties config = fileReader.getConfig("mail.config");
        assertEquals("smtp.gmail.com", config.get("smtphost"));
        assertEquals("587", config.get("smtpport"));
    }

    @Test
    void getResourceFile() {
        FileReader fileReader = new FileReader();
        File file = fileReader.getResourceFile("files/Bewerbung_Holzky.pdf");
        assertNotNull(file);
    }

    @Test
    void jsonParser() {
        FileReader fileReader = new FileReader();
        JsonNode jsonNode = fileReader.jsonParser("files/mail.json");
        assertEquals("michel.holzky@gmail.com", jsonNode.get("from").asText());
        String subject = "02 Challenge accepted. Meine Bewerbung auf euer Plakat";
        assertEquals(subject, jsonNode.get("subject").asText());

    }
}