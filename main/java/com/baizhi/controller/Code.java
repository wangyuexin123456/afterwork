package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class Code {
    @RequestMapping("code")
    public String getCode(HttpServletResponse response, HttpSession session) {
        //获取验证码文字
        String code = SecurityCode.getSecurityCode();
        //获取验证码图片
        BufferedImage image = SecurityImage.createImage(code);
        //获取作用域
        //存储验证码用于前台获取
        session.setAttribute("code", code);
        System.out.println(code);
        ServletOutputStream out =null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
