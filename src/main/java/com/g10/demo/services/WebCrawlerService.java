package com.g10.demo.services;


import com.g10.demo.type.StoryOverview;
import com.g10.demo.type.SearchResultStory;

import java.util.List;

public interface WebCrawlerService {
    public StoryOverview getDetails(String url);
    public List<SearchResultStory> search(String keyword);
    public List<SearchResultStory> getStoryByGenre(String genre);
    public List<SearchResultStory> getRecommendation();


}
