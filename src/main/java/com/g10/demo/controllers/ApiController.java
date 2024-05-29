package com.g10.demo.controllers;

import com.g10.demo.plugins.PluginManager;
import com.g10.demo.services.WebCrawlerService;
import com.g10.demo.type.response.SuccessApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private PluginManager pluginManager;

    @Autowired
    public ApiController(PluginManager pluginManager) throws Exception {
        this.pluginManager = pluginManager;
        if (pluginManager.getAllNames().length == 0) {
            pluginManager.loadPlugin();
        }
    }

    @GetMapping("/servers")
    ResponseEntity<?> getServers() {
        SuccessApiResponse successApiResponse = new SuccessApiResponse();
        successApiResponse.setStatus("201");
        successApiResponse.setData(pluginManager.getAllNames());
        return ResponseEntity.ok(successApiResponse);
    }

    @GetMapping("/{serverName}/overview")
    ResponseEntity<?> getOverview(@PathVariable String serverName,
                                  @RequestParam String url) {

        SuccessApiResponse successApiResponse = new SuccessApiResponse();
        successApiResponse.setStatus("success");
        WebCrawlerService plugin = pluginManager.getPlugin(serverName);
        successApiResponse.setData(plugin.getOverview(url));
        return ResponseEntity.ok(successApiResponse);
    }

    @GetMapping("/{serverName}/search")
    ResponseEntity<?> search(@PathVariable String serverName,
                             @RequestParam String q) {
        SuccessApiResponse successApiResponse = new SuccessApiResponse();
        WebCrawlerService plugin = pluginManager.getPlugin(serverName);
        successApiResponse.setStatus("success");
        successApiResponse.setData(plugin.search(q));
        return ResponseEntity.ok(successApiResponse);
    }

    @GetMapping("/{serverName}/genre")
    ResponseEntity<?> getStoryByGenre(@PathVariable String serverName,
                                      @RequestParam String genre) {
        SuccessApiResponse successApiResponse = new SuccessApiResponse();
        successApiResponse.setStatus("success");
        WebCrawlerService plugin = pluginManager.getPlugin(serverName);
        successApiResponse.setData(plugin.getStoryByGenre(genre));
        return ResponseEntity.ok(successApiResponse);
    }

    @GetMapping("/{serverName}/recommendation")
    ResponseEntity<?> getRecommendation(@PathVariable String serverName) {
        SuccessApiResponse successApiResponse = new SuccessApiResponse();
        successApiResponse.setStatus("success");
        WebCrawlerService plugin = pluginManager.getPlugin(serverName);
        successApiResponse.setData(plugin.getRecommendation());
        return ResponseEntity.ok(successApiResponse);
    }

    @GetMapping("/{serverName}/chapter")
    ResponseEntity<?> getChapter(@PathVariable String serverName,
                                @RequestParam String url) {
        SuccessApiResponse successApiResponse = new SuccessApiResponse();
        successApiResponse.setStatus("success");
        WebCrawlerService plugin = pluginManager.getPlugin(serverName);
        successApiResponse.setData(plugin.getChapterInfoByPage(url));
        return ResponseEntity.ok(successApiResponse);
    }
}
