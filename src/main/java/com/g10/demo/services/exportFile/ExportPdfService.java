package com.g10.demo.services.exportFile;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.*;

public class ExportPdfService implements ExportFileService {
    @Override
    public ByteArrayInputStream exportFile(String content) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph(content));

        document.close();

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override
    public String getName() {
        return "Pdf";
    }
}
