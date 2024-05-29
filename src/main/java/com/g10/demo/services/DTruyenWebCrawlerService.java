package com.g10.demo.services;

import com.g10.demo.type.ChapterInfor;
import com.g10.demo.type.SearchResultStory;
import com.g10.demo.type.StoryDetail;
import com.g10.demo.type.StoryOverview;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DTruyenWebCrawlerService implements WebCrawlerService{

    @Override
    public StoryDetail getDetails(String url) {
        return null;
    }

    @Override
    public List<ChapterInfor> getChapterInfoByPage(String url) {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements chapterElements = doc.select("#chapters li a");
            return chapterElements.stream()
                    .map(element -> {
                        String chapterUrl = element.attr("href");
                        String chapterTitle = element.text();
                        String[] parts = element.attr("title").split(" - ");
                        int chapterNumber = 1;
                        for (String part : parts) {
                            if (part.startsWith("Chương")) {
                                String[] chapterParts = part.split(" ");
                                chapterNumber = Integer.parseInt(chapterParts[1]);
                            }
                        }
                        return new ChapterInfor(chapterTitle, chapterUrl, chapterNumber);
                    })
                    .toList();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return  null;
    }

    @Override
    public String getName() {
        return "dtruyen";
    }

    public int getMaxPage(String url) {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements pagination = doc.select(".pagination li");
            return !pagination.isEmpty() ? Integer.parseInt(pagination.get(pagination.size() - 2).text()) : 1;
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        return 1;
    }

    @Override
    public StoryOverview getOverview(String url) {
        try {
            // GET OVERVIEW
            System.out.println(url + "123");
            Document doc = Jsoup.connect(url).get();
            String coverImage = doc.selectFirst("#story-detail img.cover").attr("src");
            String title = doc.selectFirst("#story-detail h1.title").text();
            String description = doc.selectFirst("#story-detail div.description").text();
            String author = doc.selectFirst("#story-detail p.author a[itemprop=author]").attr("title");
            String genres = doc.select("#story-detail .story_categories a[itemprop=genre]")
                    .stream()
                    .map(Element::text)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");

            //GET RATING
            double rating = Double.parseDouble(doc.selectFirst("#story-detail .rate [data-score]").attr("data-score"));
            Elements ratingElements = doc.select("#story-detail .rate .small em strong > span");
            int totalRating = Integer.parseInt(ratingElements.get(1).text());

            //GET MAX CHAPTER
            int maxChapter = this.getMaxPage(url);
            return new StoryOverview(coverImage, title, description, author, genres, rating, totalRating, maxChapter);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<SearchResultStory> search(String keyword) {
        try{
            keyword = keyword.split(" ").length > 1 ? keyword.replace(" ", "-") : keyword;
            String url = "https://dtruyen.com/searching/" + keyword +"/lastupdate/all/all";
            System.out.println(url);
            Document doc = Jsoup.connect(url).get();
            Elements storyElements = doc.select(".list-stories .story-list");
            System.out.println(storyElements.size());
            int maxPage = this.getMaxPage(url);
            return storyElements.stream()
                    .map(element -> {
                        Element a = element.selectFirst("a");
                        Element temp = a.selectFirst("img");
                        String coverImage = temp.attr("data-layzr");
                        String urlStory = a.attr("href");
                        String title = a.attr("title");
                        String author = element.selectFirst("p[itemprop=author]").text();

                        String lastChapter = element.selectFirst("p.last-chapter").text();

                        String lastDayUpdate = element.selectFirst(".last-chap .last-updated").text();
                        Date lastDateUpdate = new Date(Long.parseLong(lastDayUpdate) * 1000);

                        return new SearchResultStory(coverImage, title, author, lastChapter, lastDateUpdate,urlStory, maxPage);
                    })
                    .toList();
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<SearchResultStory> getStoryByGenre(String genre) {
        genre = genre.split(" ").length > 1 ? genre.replace(" ", "-") : genre;
        String url = "https://dtruyen.com/" + genre;
        System.out.println(url);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements storyElements = doc.select(".list-stories .story-list");
            int maxPage = this.getMaxPage(url);
            return storyElements.stream()
                    .map(element -> {
                        Element a = element.selectFirst("a");
                        Element temp = a.selectFirst("img");
                        String coverImage = temp.attr("data-layzr");
                        String urlStory = a.attr("href");
                        String title = a.attr("title");
                        String author = element.selectFirst("p[itemprop=author]").text();

                        String lastChapter = element.selectFirst("p.last-chapter").text();

                        String lastDayUpdate = element.selectFirst(".last-chap .last-updated").text();
                        Date lastDateUpdate = new Date(Long.parseLong(lastDayUpdate) * 1000);

                        return new SearchResultStory(coverImage, title, author, lastChapter, lastDateUpdate,urlStory, maxPage);
                    })
                    .toList();

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<SearchResultStory> getRecommendation() {
        return List.of();
    }
}
