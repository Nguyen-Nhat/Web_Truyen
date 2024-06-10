package com.g10.demo.services.exportFile;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Service
public class ExportTxtService implements ExportFileService{
    @Override
    public ByteArrayInputStream exportFile(String content) {
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getName() {
        return "txt";
    }
}
