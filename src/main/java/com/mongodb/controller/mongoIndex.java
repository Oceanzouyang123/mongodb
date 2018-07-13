package com.mongodb.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.entity.Article;
import com.mongodb.entity.Zouyang;
import com.mongodb.repository.ArticleRepository;
import com.mongodb.repository.ZouyangRepository;

@Controller
public class mongoIndex {

	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ZouyangRepository  zouyangRepository;
	
    @Autowired
    private MongoTemplate mongoTemplate; 
	
	//查询所有
	@RequestMapping("queryAll")
	@ResponseBody
	public String queryAll(){
		List<Zouyang> zouyangList = zouyangRepository.findAll();
		System.out.println(zouyangList.size());
		//for (int i = 0; i < zouyangList.size(); i++) {
		//	System.out.println(zouyangList.get(i).getTitle()+"=="+zouyangList.get(i).getTags());
		//}
		return "1";
	}
	
	//新增
	@RequestMapping("insertMongo")
	@ResponseBody
	public String insertMongo(){
		for(int i=1236637;i<999999999;i++){
			Zouyang zouyang = new Zouyang();
			zouyang.setUrl("url"+i);
			zouyang.setBy("by"+i);
			zouyang.setLikes(100+i);
			zouyang.setTags("tags"+i);
			zouyang.setDecription("decription"+i);
			zouyang.setTitle("title"+i);
			zouyangRepository.insert(zouyang);
		}		
		return "1";
	}
	
	//统计所有数据个数
	@RequestMapping("queryCount")
	@ResponseBody
	public String queryCount(){
		Criteria criteria = Criteria.where("");
        Query query = new Query(criteria);
        long start = System.currentTimeMillis();
		Long count = mongoTemplate.count(query, Zouyang.class);
		long end = System.currentTimeMillis();
		System.out.println("count总数统计耗时--"+(start-end)+"--count=="+count);
		return "1";
	}
	
	//统计条件查询个数
	@RequestMapping("queryCountByTitle")
	@ResponseBody
	public String queryCountByTitle(){
		Criteria criteria = Criteria.where("title").is("title1");
        Query query = new Query(criteria);
        long start = System.currentTimeMillis();
		Long count = mongoTemplate.count(query, Zouyang.class);
		long end = System.currentTimeMillis();
		System.out.println("count总数统计耗时--"+(start-end)+"--count=="+count);
		return "1";
	}
	
	//repisitory条件查询
	@RequestMapping("queryByBy")
	@ResponseBody
	public String queryByTitle(){
		String title = "zouyang教程";		
		long start = System.currentTimeMillis();
		List<Zouyang> zouyangList = zouyangRepository.findByBy(title);
		long end = System.currentTimeMillis();
		System.out.println("耗时--"+(start-end));
		System.out.println("mongodb like size=="+zouyangList.size()+"--"+zouyangList.get(0).getBy());
		//List<Article> articleList = articleRepository.findByTitle(title);
		return "1";
	}
	
	//repisitory条件查询根据字段tags
	//tags: ['mongodb', 'database', 'NoSQL'] 查询tags时，三个参数任意一个查询都可以查到
	@RequestMapping("queryByTags")
	@ResponseBody
	public String findByTags(){
		String tags = "database";
		long start = System.currentTimeMillis();
		List<Zouyang> zouyangList = zouyangRepository.findByTags(tags);
		long end = System.currentTimeMillis();
		System.out.println("耗时--"+(start-end)+"--"+zouyangList.get(0).getTags());
		return "1";
	}
	
	//MongoTemplate条件查询by字段
	@RequestMapping("queryByMongoTemplateBy")
	@ResponseBody
	public String queryByMongoTemplate(){
		Criteria criteria = Criteria.where("by").is("zouyang教程");
        Query query = new Query(criteria);
        long start = System.currentTimeMillis();
        List<Zouyang> zouyangList = mongoTemplate.find(query, Zouyang.class);
        long end = System.currentTimeMillis();
		System.out.println("耗时--"+(start-end));
        System.out.println("template=="+zouyangList.size());
		return "1";
	}
	
	//MongoTemplate条件查询title字段
	@RequestMapping("queryByMongoTemplateTitle")
	@ResponseBody
	public String queryByMongoTemplateTitle(){
		Criteria criteria = Criteria.where("title").is("title2");
        Query query = new Query(criteria);
        long start = System.currentTimeMillis();
        List<Zouyang> zouyangList = mongoTemplate.find(query, Zouyang.class);
        long end = System.currentTimeMillis();
		System.out.println("耗时--"+(start-end));
        System.out.println("template=="+zouyangList.size());
		return "1";
	}
	
	//MongoTemplate模糊匹配条件查询by字段
	@RequestMapping("likeByBy")
	@ResponseBody
	public String likeByTitle(){
		String title="菜鸟";
		Pattern pattern = Pattern.compile("^.*" + title + ".*$");
		//Pattern pattern = Pattern.compile(title);
		Query query = new Query();
		query.addCriteria(Criteria.where("by").regex(pattern));
		long start = System.currentTimeMillis();
		List<Zouyang> zouyangList = mongoTemplate.find(query, Zouyang.class);
		long end = System.currentTimeMillis();
		System.out.println("耗时--"+(start-end));
		System.out.println("mongodb like size=="+zouyangList.size()+"--"+zouyangList.get(0).getBy());
		//List<Article> articleList = articleRepository.findByTitle(title);
		return "1";
	}
	
}
