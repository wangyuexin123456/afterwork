package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.PageNum;
import com.baizhi.entity.User;
import com.baizhi.entity.UserText;

import java.util.List;
import java.util.Map;

public interface UserService {
    public PageNum<User> query(int page, int rows);
    String insert(User user);
    void delete(String id);
    void update(User user);
    void updateStatus(String userId,String status);
    List<User> queryAll();
    Map<String,Object> queryOne(String phone, String password, String code);
    Map<String,Object> regist(User user);
    List<UserText> queryByDate();
    List<UserText> queryBySex();
}
