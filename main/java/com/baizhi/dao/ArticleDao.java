package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao extends BaseDao<Article>{
    List<Article> selectAllById(@Param("id") String id,@Param("page") int page,@Param("rows") int rows);
    @Override
    List<Article> selectAll(@Param("page")int page,@Param("rows")int rows);
    List<Article> selectAllId(@Param("id") String id,@Param("page")int page,@Param("rows")int rows);
    Article selectById(String id);
    @Override
    int selectCount();

    @Override
    void insert(Article article);

    @Override
    void delete(String id);

    @Override
    void update(Article article);
}
