package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Utils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMD5 {
    @Autowired
    private AdminDao adminDao;
    @Test
    public void test1(){

        Md5Hash md5Hash = new Md5Hash("123456","abcdef",1024);
        String s = md5Hash.toHex();

        System.out.println(s);
    }
}
