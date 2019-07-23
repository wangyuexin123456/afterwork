package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao extends BaseDao<Album> {

    @Override
    List<Album> selectAll(@Param("page") int page, @Param("rows") int rows);

    @Override
    int selectCount();

    @Override
    void insert(Album album);

    @Override
    void delete(String id);

    @Override
    void update(Album album);
    Album selectOne(String id);
}
