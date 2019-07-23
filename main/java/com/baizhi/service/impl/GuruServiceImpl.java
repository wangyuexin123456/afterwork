package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Guru;
import com.baizhi.entity.PageNum;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<Guru> query(int page, int rows) {
        PageNum<Guru> pageNum = new PageNum();
        pageNum.setRows( guruDao.selectAll(page,rows));
        int i = guruDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }

    @Override
    public String insert(Guru guru) {
        String id= UUID.randomUUID().toString();
        guru.setGuruId(id);
        guruDao.insert(guru);
        return id;
    }

    @Override
    public void delete(String id) {
        guruDao.delete(id);
    }

    @Override
    public void update(Guru guru) {
        guruDao.update(guru);
    }
    @Override
    public List<Guru> queryAll(){
        return guruDao.select();
    }

    @Override
    public void updateStatus(String id, String status) {
        guruDao.updateStatus(id,status);
    }
}
