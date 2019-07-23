package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 上师
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guru implements Serializable {
    private String guruId;
    private String name;
    private String profile;
    private String status;
    private String sex;

}
