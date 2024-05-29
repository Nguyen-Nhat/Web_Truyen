package com.g10.demo.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchResultStory {
    private String coverImage;
    private String title;
    private String author;
    private int lastChapter;
    private int lastDayUpdate;
    private String url;

}
