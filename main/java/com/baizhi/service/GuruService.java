package com.baizhi.service;

import com.baizhi.entity.*;

import java.util.List;

public interface GuruService {
    public PageNum<Guru> query(int page, int rows);
    String insert(Guru guru);
    void delete(String id);
    void update(Guru guru);
    List<Guru> queryAll();
    void updateStatus(String id,String status);
}
