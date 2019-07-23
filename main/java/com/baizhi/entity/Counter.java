package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 计数器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counter implements Serializable {
    private String counterId;
    private String userId;
    private String courseId;
    private String title;
    private Integer count;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}
