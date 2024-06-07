package com.g10.demo.services.exportFile;

import java.io.ByteArrayInputStream;

public interface ExportFileService {
    ByteArrayInputStream exportFile(String content);

    String getName();
}
