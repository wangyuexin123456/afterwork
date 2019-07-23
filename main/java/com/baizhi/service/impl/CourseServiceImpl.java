package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.CourseDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Course;
import com.baizhi.service.AdminService;
import com.baizhi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Course> query() {
        return courseDao.selectAll();
    }
}
