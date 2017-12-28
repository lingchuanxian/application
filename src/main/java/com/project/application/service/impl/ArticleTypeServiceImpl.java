package com.project.application.service.impl;

import com.project.application.mapper.ArticleTypeMapper;
import com.project.application.bean.ArticleType;
import com.project.application.service.ArticleTypeService;
import com.project.application.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@Service("ArticleTypeService")
@Transactional
public class ArticleTypeServiceImpl extends AbstractService<ArticleType> implements ArticleTypeService {
    @Resource
    private ArticleTypeMapper apArticleTypeMapper;

}
