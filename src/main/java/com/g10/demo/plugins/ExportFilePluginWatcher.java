package com.g10.demo.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExportFilePluginWatcher extends PluginWatcher{
    public PluginManager shapeManager;

    @Autowired
    public ExportFilePluginWatcher(PluginManager shapeManager) throws IOException {
        super("target/classes/com/g10/demo/services/export_file");
        this.shapeManager = shapeManager;
    }

    public void load() {
        try {
            shapeManager.loadExportFilePlugin();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
