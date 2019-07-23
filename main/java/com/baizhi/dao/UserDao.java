package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserText;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseDao<User>{

    @Override
    List<User> selectAll(@Param("page") int page,@Param("rows") int rows);

    @Override
    int selectCount();

    @Override
    void insert(User user);

    @Override
    void delete(String id);

    @Override
    void update(User user);

    void updateStatus(String userId,String status);
    List<User> select();
    User selectOne(String phone);
    List<UserText> selectByData();
    List<UserText> selectBySex();
}
