package com.huize.website.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.huize.website.entity.Article;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer>{
	@Query("select s from contents as s where cid=?")
	public Article findById(Integer cid);
	@Query("select s from contents as s where title=?")
	public Article findByTitle(String title);
}
