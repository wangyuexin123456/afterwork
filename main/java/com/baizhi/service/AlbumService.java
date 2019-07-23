package com.baizhi.service;


import com.baizhi.entity.Album;
import com.baizhi.entity.PageNum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumService {
    public PageNum<Album> query(int page, int rows);
    String insert(Album album);
    void delete(String id);
    void update(Album album);
}
