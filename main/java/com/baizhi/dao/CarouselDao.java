package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselDao extends BaseDao<Carousel>{
    @Override
    List<Carousel> selectAll(@Param("page") int page, @Param("rows") int rows );
    @Override
    int selectCount();
    @Override
    void insert(Carousel carousel);
    @Override
    void delete(String id);
    @Override
    void update(Carousel carousel);
    Carousel selectOne(String id);
}
