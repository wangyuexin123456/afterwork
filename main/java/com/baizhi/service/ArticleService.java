package com.baizhi.service;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.PageNum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    public PageNum<Article> query(int page, int rows);
    String insert(Article article);
    Article queryOne(String id);
    void delete(String id);
    void update(Article article);
    PageNum<Article> selectAllById(String id, int page,int rows);
    PageNum<Article> selectAllId(String id, int page,int rows);
}
