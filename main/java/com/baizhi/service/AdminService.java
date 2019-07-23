package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    public Map<String,Object> query(Admin admin, String code);
}
