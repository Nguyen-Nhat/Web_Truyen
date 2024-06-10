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
public class StoryOverview {
    private String coverImage;
    private String title;
    private String description;
    private Author author;
    private String genre;
    private double rating;
    private int totalRating;
    private int totalViews;
    private Date updatedDate;
    private String status;
    private int maxPageOfChapter;

    //Remove constructor
    public StoryOverview(String coverImage, String title, String description, String authorName, String genres, double rating, int totalRating, int totalViews, Date updatedDate, String status, int maxChapter) {
        Author author = new Author(authorName, "");
        this.coverImage = coverImage;
        this.title = title;
        this.description = description;
        this.author = author;
        this.genre = genres;
        this.rating = rating;
        this.totalRating = totalRating;
        this.totalViews = totalViews;
        this.updatedDate = updatedDate;
        this.status = status;
        this.maxPageOfChapter = maxChapter;
    }
}
