package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private HttpServletRequest request;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String,Object> query(Admin admin,String code) {
        String cc=(String)request.getSession().getAttribute("code");
        System.out.println(cc);
        Map<String,Object> map=new HashMap<>();
        if(cc.equals(code)) {
            Admin select = adminDao.select(admin);
            if (select!= null) {
                System.out.println("正确");
                map.put("status","200");
                return map;
            } else {
                System.out.println("错误");
                map.put("status","250");
                map.put("message","账号或密码错误");
                return map;
            }
        }else{
            map.put("status","300");
            map.put("message","验证码错误");
            return map;
        }
    }
}
