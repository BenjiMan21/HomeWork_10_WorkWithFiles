package com.nikiforov;


import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipCsvReadFileTest {
    private final ClassLoader cl = ZipCsvReadFileTest.class.getClassLoader();

    @Test
    @DisplayName("Чтение CSV файла из архива")
    void zipCsvParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("archive.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(
                            new String[] {"Student","Language"}, content.get(0)
                    );
                }
            }
        }
    }
}