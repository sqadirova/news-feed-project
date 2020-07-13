package com.feed.news.entity.db;

import com.feed.news.entity.db.XUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name="news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "news_id")
    private int news_id;

    @Column (name = "news_name")
    private String news_name;

    @Column (name = "news_url")
    private String news_url;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "disliked",
    joinColumns = {@JoinColumn(name = "n_id", referencedColumnName ="news_id" )},
            inverseJoinColumns = {@JoinColumn(name = "u_id", referencedColumnName = "user_id")}
    )
    private Set<XUser> users;


    public News(String news_name, String news_url) {
        this.news_name=news_name;
        this.news_url=news_url;

    }
}