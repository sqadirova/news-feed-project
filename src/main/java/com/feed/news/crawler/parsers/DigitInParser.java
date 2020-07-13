package com.feed.news.crawler.parsers;

import com.feed.news.entity.db.Article;
import com.feed.news.crawler.DateTimeFormats;
import com.feed.news.crawler.JsoupParser;
import com.feed.news.crawler.Website;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DigitInParser implements JsoupParser {
    static List<Article> articles = new ArrayList();

    @SneakyThrows
    @Override
    public List<Article> getArticles() {
        Document document = Jsoup.connect("https://www.digit.in/news/").get();
        Elements elements = document.getElementsByClass("Thumb-new");
        for (Element element : elements) {
            String header = element.select(".product-desc").text();
            String content = element.select(".product-desc").text();
            String link =element.getElementsByTag("a").first().attr("href");
            String imageLink = element.getElementsByTag("img").first().attr("data-src");
            LocalDate date = convertStringToDate(element.getElementsByTag("span").first().text(), DateTimeFormats.ABBR_MONTH_FORMAT);

            articles.add(new Article(header, content, link, imageLink, date, Website.DigitIn));
        }

        return articles;
    }
}



