package com.g10.demo.config;

import com.g10.demo.plugins.ExportFilePluginWatcher;
import com.g10.demo.plugins.ServerPluginWatcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppWatcher implements CommandLineRunner {
    private final ServerPluginWatcher serverPluginWatcher;
    private final ExportFilePluginWatcher exportFilePluginWatcher;

    public AppWatcher(ServerPluginWatcher shapeWatcherService,
                      ExportFilePluginWatcher exportFilePluginWatcher) {
        this.serverPluginWatcher = shapeWatcherService;
        this.exportFilePluginWatcher = exportFilePluginWatcher;
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(serverPluginWatcher::startWatching).start();
        new Thread(exportFilePluginWatcher::startWatching).start();
    }
}
