package com.g10.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Story {
    private String coverImage;
    private String title;
    private String description;
    private String author;
    private String genre;
    private double rating;
    private int totalRating;
}
