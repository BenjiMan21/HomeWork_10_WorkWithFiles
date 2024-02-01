package com.nikiforov;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.UserInfo;
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
            UserInfo userInfo = objectMapper.readValue(reader, UserInfo.class);


            Assertions.assertEquals("Alex", userInfo.getName());
            Assertions.assertEquals(34, userInfo.getAge());
            Assertions.assertEquals("Stanislav", userInfo.getTeachersName()[0]);
            Assertions.assertEquals("Dmitriy", userInfo.getTeachersName()[1]);
            Assertions.assertEquals("Java", userInfo.getCourseInfo().getLanguage());
            Assertions.assertEquals(24, userInfo.getCourseInfo().getGroup());
        }
    }
}
