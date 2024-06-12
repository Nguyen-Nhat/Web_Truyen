package com.g10.demo.services.export_file;

import java.io.ByteArrayInputStream;

public interface ExportFileService {
    ByteArrayInputStream exportFile(String content);

    String getName();
}
