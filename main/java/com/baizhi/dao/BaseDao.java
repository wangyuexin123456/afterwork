package com.baizhi.dao;



import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {
    List<T> selectAll(int page, int rows);
    int selectCount();
    void insert(T t);
    void delete(String id);
    void update(T t);
}
