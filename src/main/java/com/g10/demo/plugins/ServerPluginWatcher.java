package com.g10.demo.plugins;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServerPluginWatcher extends PluginWatcher {
    public PluginManager shapeManager;

    @Autowired
    public ServerPluginWatcher(PluginManager shapeManager) throws IOException {
        super("target/classes/com/g10/demo/services/");
        this.shapeManager = shapeManager;
    }

    public void load() {
        try {
            shapeManager.loadServerPlugin();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
