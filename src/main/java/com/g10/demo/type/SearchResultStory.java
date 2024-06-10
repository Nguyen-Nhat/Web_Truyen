package com.g10.demo.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchResultStory {
    private String coverImage;
    private String title;
    private Author author;
    private String lastChapter;
    private Date lastDayUpdate;
    private String url;
    private int maxPage;

    //Remove this constructor
    public SearchResultStory(String coverImage, String title, String authorName, String lastChapter, Date lastDayUpdate, String url, int maxPage) {
        Author author = new Author(authorName, "");
        this.coverImage = coverImage;
        this.title = title;
        this.author = author;
        this.lastChapter = lastChapter;
        this.lastDayUpdate = lastDayUpdate;
        this.url = url;
        this.maxPage = maxPage;
    }
}
