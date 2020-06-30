package com.feed.news.repository;

import com.feed.news.entity.News;
import com.feed.news.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.LEFT;
import static org.hibernate.hql.internal.antlr.SqlTokenTypes.ON;

@Repository
public interface NewsFeedRepo extends JpaRepository<News,Integer> {

    @Query(value="SELECT news_name FROM news  JOIN disliked ON disliked.n_id = news.news_id WHERE disliked.u_id=:id" , nativeQuery = true)
    List<String> extractbyUserid(@Param("id") int id);

}
