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

}
