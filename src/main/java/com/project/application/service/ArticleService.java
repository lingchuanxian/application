package com.project.application.service;
import java.util.List;
import java.util.Map;

import com.project.application.bean.Article;
import com.project.application.core.Service;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
public interface ArticleService extends Service<Article> {
	List<Article> SelectArticle();
	List<Article> SelectArticleByCondition(Map<String,Object> params);
	Article SelectArticlebyId(int id);
	int UpdateBrTimes(int id);
}
