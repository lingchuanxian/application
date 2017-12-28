package com.project.application.service.impl;

import com.project.application.mapper.ArticleMapper;
import com.project.application.bean.Article;
import com.project.application.service.ArticleService;
import com.project.application.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@Service("ArticleService")
@Transactional
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {
    @Resource
    private ArticleMapper apArticleMapper;

	@Override
	public List<Article> SelectArticle() {
		return apArticleMapper.SelectArticle();
	}

	@Override
	public List<Article> SelectArticleByCondition(Map<String, Object> params) {
		return apArticleMapper.SelectArticleByCondition(params);
	}

	@Override
	public Article SelectArticlebyId(int id) {
		return apArticleMapper.SelectArticlebyId(id);
	}

	@Override
	public int UpdateBrTimes(int id) {
		return apArticleMapper.UpdateBrTimes(id);
	}

}
