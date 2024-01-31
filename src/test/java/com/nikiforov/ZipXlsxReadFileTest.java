package com.nikiforov;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipXlsxReadFileTest {
    private final ClassLoader cl = ZipXlsxReadFileTest.class.getClassLoader();

    @Test
    @DisplayName("Чтение xlsx файла из архива")
    void zipXlsxParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("archive.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals("Никифоров А.А.",
                            xls.excel.getSheet("Лист1").getRow(1).getCell(0).getStringCellValue());
                }
            }
        }
    }
}
