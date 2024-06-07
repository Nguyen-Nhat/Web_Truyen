package com.g10.demo.plugins;

import java.io.IOException;
import java.nio.file.*;

abstract class PluginWatcher {
    private final WatchService watchService;
    private final Path pathToWatch;

    public PluginWatcher(String dirToWatch) throws IOException {
        this.pathToWatch = Paths.get(dirToWatch);
        this.watchService = FileSystems.getDefault().newWatchService();
        this.pathToWatch.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE);
    }

    public void load() {
        System.out.println("Load file watcher service");
    }

    public void startWatching() {
        System.out.println("Start watching directory: " + pathToWatch);
        WatchKey key;
        while (true) {
            try {
                key = watchService.take();
            } catch (InterruptedException ex) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("File deleted: " + fileName);
                }
                else if (kind == StandardWatchEventKinds.ENTRY_CREATE)
                    System.out.println("File added: " + fileName);
                load();
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
