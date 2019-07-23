package com.baizhi.dao;

import com.baizhi.entity.Guru;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruDao extends BaseDao<Guru>{

    @Override
    List<Guru> selectAll(@Param("page") int page,@Param("rows") int rows);
    List<Guru> select();
    @Override
    int selectCount();

    @Override
    void insert(Guru guru);

    @Override
    void delete(String id);

    @Override
    void update(Guru guru);
    void updateStatus(String id,String status);
}
