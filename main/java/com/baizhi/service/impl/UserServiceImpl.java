package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.*;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public PageNum<User> query(int page, int rows) {
        PageNum<User> pageNum = new PageNum();
        pageNum.setRows( userDao.selectAll(page,rows));
        int i = userDao.selectCount();
        pageNum.setPage(page);
        pageNum.setRecords(i);
        int total= i%rows==0? i/rows: i/rows+1;
        pageNum.setTotal(total);
        return pageNum;
    }

    @Override
    public String insert(User user) {
        String id= UUID.randomUUID().toString();
        user.setUserId(id);
        userDao.insert(user);
        return id;
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }
    @Override
    public List<User> queryAll(){
       return userDao.select();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void updateStatus(String userId,String status) {
        userDao.updateStatus(userId,status);
    }
    @Override
    public Map<String,Object> queryOne(String phone, String password, String code) {
        User u = userDao.selectOne(phone);
        System.out.println(u);
        String cc = (String) request.getSession().getAttribute("code");
        Map<String, Object> map = new HashMap<>();
        if(!cc.equals(code)){
            map.put("status", "-500");
            map.put("message", "验证码错误");
        } else if (u != null) {
            String salt = u.getSalt();
            String pwd = MD5Utils.getPassword(password + salt);
            System.out.println("加密后密码"+pwd);
            System.out.println("密码"+u.getPassword());
            if (!u.getPassword().equals(pwd)) {
                map.put("status", "-300");
                map.put("message", "密码错误");
            } else {
                map.put("status", "200");
                map.put("User", u);
            }
        } else {
            map.put("status", "-200");
            map.put("message", "账号或密码错误");
        }
        return map;
    }

    @Override
    public Map<String, Object> regist(User user) {
        User user1 = userDao.selectOne(user.getPhone());
        Map<String, Object> map = new HashMap<>();
        if(user1 !=null){
            map.put("status", "-400");
            map.put("message", "手机号已存在");
        }else {
            String userId = UUID.randomUUID().toString();
            String salt = MD5Utils.getSalt();
            String password = MD5Utils.getPassword(user.getPassword() + salt);
            user.setUserId(userId);
            user.setPassword(password);
            user.setSalt(salt);
            user.setStatus("正常");
            user.setRegistTime(new Date());
            userDao.insert(user);
            map.put("status", "200");
            User u = userDao.selectOne(user.getPhone());
            map.put("User", u);
        }
        return map;
    }

    @Override
    public List<UserText> queryByDate() {
        return userDao.selectByData();
    }
    @Override
    public List<UserText> queryBySex() {
        return userDao.selectBySex();
    }
}
