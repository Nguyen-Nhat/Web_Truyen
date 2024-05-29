package com.g10.demo.controllers;


import com.g10.demo.services.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/demo")
public class DemoController {
    @Autowired
    private WebCrawlerService webCrawlerService;
    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok( webCrawlerService.search("tinh linh"));
    }
}