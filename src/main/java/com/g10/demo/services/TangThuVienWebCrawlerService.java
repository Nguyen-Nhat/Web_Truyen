package com.g10.demo.services;

import com.g10.demo.type.ChapterInfor;
import com.g10.demo.type.SearchResultStory;
import com.g10.demo.type.StoryDetail;
import com.g10.demo.type.StoryOverview;

import java.util.List;

public class TangThuVienWebCrawlerService implements WebCrawlerService{
    @Override
    public StoryOverview getOverview(String url) {
        return null;
    }

    @Override
    public List<SearchResultStory> search(String keyword, int page) {
        return List.of();
    }

    @Override
    public List<SearchResultStory> getStoryByGenre(String genre, int page) {
        return List.of();
    }

    @Override
    public List<SearchResultStory> getRecommendation() {
        return List.of();
    }

    @Override
    public String getName() {
        return "tangthuvien";
    }

    @Override
    public StoryDetail getDetails(String url) {
        return null;
    }

    @Override
    public List<ChapterInfor> getChapterInfoByPage(String url, int page) {
        return List.of();
    }
}
