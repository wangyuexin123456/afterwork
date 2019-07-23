package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Article;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.PageNum;
import com.baizhi.service.AdminService;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<Article> query(int page, int rows) {
        PageNum pageNum = new PageNum<Article>();
        pageNum.setRows(articleDao.selectAll(page,rows));
        int i = articleDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }

    @Override
    public String insert(Article article) {
        String id= UUID.randomUUID().toString();
        article.setArticleId(id);
        articleDao.insert(article);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Article queryOne(String id) {
        return articleDao.selectById(id);
    }

    @Override
    public void delete(String id) {
        articleDao.delete(id);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<Article> selectAllById(String id, int page, int rows) {
        PageNum<Article> pageNum = new PageNum<>();
        pageNum.setRows(articleDao.selectAllById(id,page,rows));
        int i = articleDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<Article> selectAllId(String id, int page, int rows) {
        PageNum<Article> pageNum = new PageNum<>();
        pageNum.setRows(articleDao.selectAllId(id,page,rows));
        int i = articleDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }

}
