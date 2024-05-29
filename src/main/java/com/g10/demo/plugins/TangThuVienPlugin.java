package com.g10.demo.plugins;

import com.g10.demo.services.WebCrawlerService;
import com.g10.demo.type.SearchResultStory;
import com.g10.demo.type.StoryOverview;

import java.util.List;

public class TangThuVienPlugin implements WebCrawlerService {
    @Override
    public StoryOverview getDetails(String url) {
        return null;
    }

    @Override
    public List<SearchResultStory> search(String keyword) {
        return List.of();
    }

    @Override
    public List<SearchResultStory> getStoryByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<SearchResultStory> getRecommendation() {
        return List.of();
    }

    @Override
    public String getName() {
        return "Tang Thu Vien";
    }
}
