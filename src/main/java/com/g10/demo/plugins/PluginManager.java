package com.g10.demo.plugins;

import com.g10.demo.exception.AppException;
import com.g10.demo.services.WebCrawlerService;

import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PluginManager {
    private final Map<String, WebCrawlerService> plugins = new ConcurrentHashMap<>();
    private static final String DEFAULT_CLASS_DIR = "target/classes";

    public void loadPlugin() throws Exception {
        File directory = new File(DEFAULT_CLASS_DIR);
        URL classUrl = directory.toURI().toURL();
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{classUrl}, this.getClass().getClassLoader())) {
            Files.walk(directory.toPath())
                    .filter(path -> path.toString().endsWith(".class"))
                    .forEach(path -> {
                        String className = getClassName(directory, path.toFile());
                        try {
                            // Kiểm tra xem plugin đã được load chưa
                            if (!plugins.containsKey(className)) {
                                Class<?> clazz = Class.forName(className, true, classLoader);
                                if (WebCrawlerService.class.isAssignableFrom(clazz) &&
                                        !clazz.isInterface() &&
                                        !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) {
                                    WebCrawlerService webCrawlerService
                                            = (WebCrawlerService) clazz.getDeclaredConstructor().newInstance();
                                    plugins.put(webCrawlerService.getName().toLowerCase(), webCrawlerService);
                                    System.out.println("Loaded plugin: " + className);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    private String getClassName(File root, File file) {
        String rootPath = root.getPath();
        String filePath = file.getPath();
        String className = filePath.substring(rootPath.length() + 1, filePath.length() - 6);
        return className.replace(File.separatorChar, '.');
    }

    public WebCrawlerService getPlugin(String className) {
        WebCrawlerService plugin = plugins.get(className);

        if (plugin == null) {
            throw new AppException("Plugin not found: " + className,400);
        }
        return plugin;
    }

    public String[] getAllNames() {
        return plugins.keySet().toArray(new String[0]);
    }
}
