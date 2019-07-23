package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.PageNum;
import com.baizhi.service.AdminService;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<Album> query(int page, int rows) {
        PageNum<Album> pageNum = new PageNum<>();
        pageNum.setRows( albumDao.selectAll(page,rows));
        int i = albumDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }
    @Override
    public String insert(Album album) {
        String id= UUID.randomUUID().toString();
        album.setAlbumId(id);
        albumDao.insert(album);
        return id;
    }

    @Override
    public void delete(String id) {
        albumDao.delete(id);
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }



}
