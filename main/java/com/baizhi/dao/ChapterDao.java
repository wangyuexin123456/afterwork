package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao extends BaseDao<Chapter>{
    List<Chapter> selectAll(@Param("id") String id,@Param("page") int page, @Param("rows") int rows);
    @Override
    int selectCount();

    @Override
    void insert(Chapter chapter);

    @Override
    void delete(String id);

    @Override
    void update(Chapter chapter);
}
