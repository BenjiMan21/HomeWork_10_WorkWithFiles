package com.nikiforov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonParsingWithJacksonTest {

    private final ClassLoader cl = ZipCsvReadFileTest.class.getClassLoader();

    @Test
    @DisplayName("Парсинг json файла помощью библиотеки Jackson")
    void jsonParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("jsonfile.json");
             Reader reader = new InputStreamReader(is)) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(is);

            String name = jsonNode.get("name").asText();
            int age = jsonNode.get("age").asInt();
            JsonNode teachersNode = jsonNode.get("teachersName");
            JsonNode courseInfoNode = jsonNode.get("courseInfo");

            String[] teachersArray = objectMapper.treeToValue(teachersNode, String[].class);

            Assertions.assertEquals("Alex", name);
            Assertions.assertEquals(34, age);
            Assertions.assertArrayEquals(new String[]{"Stanislav", "Dmitriy"}, teachersArray);
            Assertions.assertEquals("Java", courseInfoNode.get("language").asText());
            Assertions.assertEquals(24, courseInfoNode.get("group").asInt());
        }
    }
}
