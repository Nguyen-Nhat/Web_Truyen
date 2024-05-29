package com.g10.demo.type;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterInfor {
    private String url;
    private String title;
    private int chapterNumber;
}
