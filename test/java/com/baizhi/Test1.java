package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.PageNum;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    private UserService userService;
    @Test
    public void test1(){
        PageNum<User> user = userService.query(1, 5);
        System.out.println(user);
    }
    @Test
    public void test2(){
        userService.delete("0d2f101a-396f-4801-a81c-6454d0478140");
    }


}
