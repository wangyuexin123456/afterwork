package com.baizhi.dao;


import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminDao {
    Admin selectBy(String username);
    public Admin select(Admin admin);
    public void insert(Admin admin);
}
