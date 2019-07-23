package com.baizhi.dao;

import com.baizhi.entity.Counter;

import java.util.List;

public interface CounterDao {
    public List<Counter> selectAll();
}
