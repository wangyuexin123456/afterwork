package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.PageNum;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.jasper.security.SecurityUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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

    @Test
    public void test3(){
        //获取安全管理器
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory();
        SecurityManager instance = iniSecurityManagerFactory.getInstance();
        //指明使用的那个安全管理器
        SecurityUtils.setSecurityManager(instance);
        //获取主题
        Subject subject = SecurityUtils.getSubject();
        //获取令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("王五","123456");
        System.out.println(usernamePasswordToken+"[[[[[[[");
        try {
            subject.login(usernamePasswordToken);
            boolean b = subject.hasRole("vip");
            if(b){
                System.out.println("这是一个VIP");
            }else{
                System.out.println("这不是vip");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);
    }
    @Autowired
    private AdminDao adminDao;
    @Test
    public void test4(){
        Admin admin = adminDao.selectBy("王五");
        System.out.println(admin);
    }
}
