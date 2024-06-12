package com.g10.demo.services.export_file;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.*;

public class ExportPdfService implements ExportFileService {
    @Override
    public ByteArrayInputStream exportFile(String content) {
        String htmlContent = content.replace("\\n", " ");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            HtmlConverter.convertToPdf(htmlContent, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override
    public String getName() {
        return "Pdf";
    }
}
