package com.feed.news.crawler.parsers;

import com.feed.news.crawler.*;
import com.feed.news.entity.Article;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabrParser extends RestTemplateService implements JsoupParser {

    List<Article> articles = new ArrayList<>();;
    Document doc;

//    public HabrParser() {
//        this.articles = new ArrayList<>();
//        this.doc = rootPage("https://habr.com/en/flows/develop/");
//    }

    @SneakyThrows
    @Override
    public List<Article> getArticles() {
          Document doc = Jsoup.connect("https://habr.com/en/flows/develop/").get();
        Elements elements = doc.getElementsByTag("article");
        for (Element element : elements) {
            String header = element.select(".post__title_link").text();
            String content = header;
            String link = element.select(".post__title_link").attr("href");
            String image = element.getElementsByTag("img").attr("src");
            LocalDate date = convertStringToDate(element.select(".post__time").text(), DateTimeFormats.HABR_FORMAT);

            articles.add(new Article(header, content, link, image, date, Website.Habr));
        }

        return articles;
    }


}
