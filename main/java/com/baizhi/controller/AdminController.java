package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    public String login(Admin admin, String code){
        //获取主题
        Subject subject = SecurityUtils.getSubject();
        //获取令牌
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(),admin.getPassword());
        try {
            subject.login(token);
            return "jsp/main/main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "jsp/login";
        }

    }
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "jsp/login";
    }
}
