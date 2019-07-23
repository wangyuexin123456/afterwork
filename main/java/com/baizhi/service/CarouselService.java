package com.baizhi.service;

import com.baizhi.entity.Carousel;
import com.baizhi.entity.PageNum;

import java.util.List;

public interface CarouselService {
     public PageNum<Carousel> query(int page, int rows);
     public String insert(Carousel carousel);
     public void delete(String id);
     public void update(Carousel carousel);
}
