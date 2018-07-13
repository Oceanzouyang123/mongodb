package com.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.entity.Zouyang;

public interface ZouyangRepository extends MongoRepository<Zouyang,Long>{
	
	//根据title字段查询
	List<Zouyang> findByTitle(String title);
	
	//根据by字段查询
	List<Zouyang> findByBy(String by);
	
	//根据tags字段查询
	List<Zouyang> findByTags(String tags);
}
