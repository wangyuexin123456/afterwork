package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.PageNum;
import com.baizhi.service.AdminService;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<Carousel> query(int page, int rows) {
        PageNum<Carousel> pageNum = new PageNum();
        pageNum.setRows( carouselDao.selectAll(page,rows));
        int i = carouselDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }

    @Override
    public String insert(Carousel carousel) {
        String id=UUID.randomUUID().toString();
        carousel.setCarouselId(id);
        carouselDao.insert(carousel);
        return id;
    }

    @Override
    public void delete(String id) {
        carouselDao.delete(id);
    }

    @Override
    public void update(Carousel carousel) {

        carouselDao.update(carousel);
    }
}
