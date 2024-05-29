package com.g10.demo.controllers;

import com.g10.demo.plugins.PluginManager;
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
        successApiResponse.setData(pluginManager.getAllNames());
        return ResponseEntity.ok(successApiResponse);
    }

    @GetMapping("/{serverName}/overview")
    ResponseEntity<?> getOverview(@PathVariable String serverName, @RequestParam String url) {
        System.out.println(serverName);
        System.out.println(url);
        return ResponseEntity.ok(pluginManager.getPlugin(serverName).getDetails(url));
    }

    @GetMapping("/{serverName}/search")
    ResponseEntity<?> search(@PathVariable String serverName, String keyword) {
        return ResponseEntity.ok(pluginManager.getPlugin(serverName).search(keyword));
    }

    @GetMapping("/{serverName}/genre")
    ResponseEntity<?> getStoryByGenre(@PathVariable String serverName, String genre) {
        return ResponseEntity.ok(pluginManager.getPlugin(serverName).getStoryByGenre(genre));
    }

    @GetMapping("/{serverName}/recommendation")
    ResponseEntity<?> getRecommendation(@PathVariable String serverName) {
        return ResponseEntity.ok(pluginManager.getPlugin(serverName).getRecommendation());
    }
}
