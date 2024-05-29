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
    private String author;
    private String lastChapter;
    private Date lastDayUpdate;
    private String url;
    private int maxPage;
}
