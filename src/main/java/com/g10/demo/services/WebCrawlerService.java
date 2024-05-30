package com.g10.demo.services;


import com.g10.demo.type.ChapterInfor;
import com.g10.demo.type.StoryDetail;
import com.g10.demo.type.StoryOverview;
import com.g10.demo.type.SearchResultStory;

import java.util.List;

public interface WebCrawlerService {
    public StoryOverview getOverview(String url);
    public List<SearchResultStory> search(String keyword, int page);
    public List<SearchResultStory> getStoryByGenre(String genre, int page);
    public List<SearchResultStory> getRecommendation();
    public String getName();
    public StoryDetail getDetails(String url);
    public List<ChapterInfor> getChapterInfoByPage(String url,int page);

}
