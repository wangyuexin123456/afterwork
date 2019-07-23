package com.baizhi.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageNum<T> implements Serializable {
    private int page;
    private int total;
    private int records;
    private List<T> rows;
}
