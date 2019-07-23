package com.baizhi.service;


import com.baizhi.entity.Chapter;
import com.baizhi.entity.PageNum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterService {
    PageNum<Chapter> selectAll(String id,int page,int rows);
    String insert(Chapter chapter);
    void delete(String id);
    void update(Chapter chapter);
}
