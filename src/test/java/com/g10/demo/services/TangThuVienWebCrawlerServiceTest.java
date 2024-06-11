package com.g10.demo.services;

import com.g10.demo.type.Author;
import com.g10.demo.type.StoryOverview;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TangThuVienWebCrawlerServiceTest {
    private final TangThuVienWebCrawlerService tangThuVienWebCrawlerService
            = new TangThuVienWebCrawlerService();

    @Test
    void getOverview() {
       String url = "https://truyen.tangthuvien.vn/doc-truyen/quy-bi-chi-chu";
       StoryOverview storyOverview = tangThuVienWebCrawlerService.getOverview(url);
       assertNotNull(storyOverview);
       assertEquals("Quỷ Bí Chi Chủ - 诡秘之主", storyOverview.getTitle());
       Author author = new Author("Ái Tiềm Thủy đích Ô Tặc", "https://truyen.tangthuvien.vn/tac-gia?author=1011");
       assertEquals(author, storyOverview.getAuthor());
       assertEquals("Đã hoàn thành", storyOverview.getStatus());
    }

    @Test
    void search() {
        String keyword = "tình cảm";
        int page = 1;
        var stories = tangThuVienWebCrawlerService.search(keyword, page);
        assertFalse(stories.isEmpty());
        assertEquals(8, stories.size());
    }

    @Test
    void getStoryByGenre() {
        String genres = "huyen-huyen";
        int page = 1;
        var stories = tangThuVienWebCrawlerService.getStoryByGenre(genres, page);
        assertFalse(stories.isEmpty());
        assertEquals(20, stories.size());
    }

    @Test
    void getRecommendation() {
        var stories = tangThuVienWebCrawlerService.getRecommendation();
        assertFalse(stories.isEmpty());
        assertEquals(18, stories.size());
    }

    @Test
    void getDetails() {
        String url = "https://truyen.tangthuvien.vn/doc-truyen/quy-bi-chi-chu/chuong-1";
        var storyDetails = tangThuVienWebCrawlerService.getDetails(url);
        assertNotNull(storyDetails);
        assertEquals("Quỷ Bí Chi Chủ", storyDetails.getTitle());
        assertEquals("Kinzie", storyDetails.getAuthor());

    }

    @Test
    void getChapterInfoByPage() {
        String url = "https://truyen.tangthuvien.vn/doc-truyen/quy-bi-chi-chu";
        int page = 1;
        var chapters = tangThuVienWebCrawlerService.getChapterInfoByPage(url, page);
        assertFalse(chapters.isEmpty());
        assertEquals(75, chapters.size());
    }

    @Test
    void getGenres() {
        var genres = tangThuVienWebCrawlerService.getGenres();
        assertFalse(genres.isEmpty());
        assertEquals(12, genres.size());
    }
}