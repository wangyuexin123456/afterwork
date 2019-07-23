package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.CounterDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Counter;
import com.baizhi.service.AdminService;
import com.baizhi.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterDao counterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Counter> query() {
        return counterDao.selectAll();
    }
}
