package com.project.application.mapper;

import java.util.List;
import java.util.Map;

import com.project.application.bean.Article;
import com.project.application.core.Mapper;

public interface ArticleMapper extends Mapper<Article> {
	List<Article> SelectArticle();
	List<Article> SelectArticleByCondition(Map<String,Object> params);
	Article SelectArticlebyId(int id);
	int UpdateBrTimes(int id);
}