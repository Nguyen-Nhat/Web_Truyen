package com.g10.demo.services;


import com.g10.demo.models.Story;

public interface WebCrawlerService {
    public Story getDetails(String url);
}
