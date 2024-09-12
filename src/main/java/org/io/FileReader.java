package org.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class FileReader {

    /**
     * Reads config files in the resources directory
     * @param fileName "root" is resources. filename includes path starting after "root" + filename
     * @return Properties object of config file
     */
    public Properties getConfig(String fileName) {
        String resource = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        String filePath = resource + fileName;

        Properties props = new Properties();
        // try to load config file
        try {
            props.load(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }

    /**
     * Creates an File Instance of the file thats passed as an argument
     * @param fileName "root" is resources. filename includes path starting after "root" + filename
     * @return Instance of File
     */
    public File getResourceFile(String fileName) {
        String resource = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        File file = new File(resource + fileName);

        return file;
    }

    /**
     * Reads and parses json files
     * @param fileName
     * @return parsed json-file
     */
    public JsonNode jsonParser(String fileName) {
        JsonNode jsonNode;
        File resourceFile = this.getResourceFile(fileName);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            jsonNode = objectMapper.readTree(resourceFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonNode;
    }

}
