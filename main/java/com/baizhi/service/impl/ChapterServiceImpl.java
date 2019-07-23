package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.PageNum;
import com.baizhi.service.AdminService;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<Chapter> selectAll(String id, int page, int rows) {
        PageNum<Chapter> pageNum = new PageNum<>();
        pageNum.setRows(chapterDao.selectAll(id,page,rows));
        int i = chapterDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }

    @Override
    public String insert(Chapter chapter) {
        String id= UUID.randomUUID().toString();
        chapter.setChapterId(id);
        chapterDao.insert(chapter);
        return id;
    }

    @Override
    public void delete(String id) {
        chapterDao.delete(id);
    }

    @Override
    public void update(Chapter chapter) {
        chapterDao.update(chapter);
    }
}
