package com.nikiforov;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipPdfReadFileTest {
    private final ClassLoader cl = ZipPdfReadFileTest.class.getClassLoader();

    @Test
    @DisplayName("Чтение PDF файла из архива")
    void zipPdfParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("archive.zip");
        ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("QA.GURU"));
                }
            }
        }
    }
}
