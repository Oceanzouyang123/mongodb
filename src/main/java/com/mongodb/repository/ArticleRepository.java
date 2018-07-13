package com.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.entity.Article;

public interface ArticleRepository extends MongoRepository<Article,Long>{

	//根据title字段查询
	List<Article> findByTitle(String title);
	
	
}
