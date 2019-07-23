package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Guru;
import com.baizhi.entity.PageNum;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @RequestMapping("updateStatus")
    public String updateStatus(String status,String id) {
        System.out.println(status + "_______" + id + "++++++++++");
        if (status.equals("正常")) {
            guruService.updateStatus(id, "冻结");
            return "1";
        } else {
            guruService.updateStatus(id, "正常");
            return "1";
        }
    }
    @RequestMapping("queryAll")
    public PageNum<Guru> queryAll(int page,int rows){
        return guruService.query(page,rows);
    }
    @RequestMapping("query")
    public List<Guru> query(){
        return guruService.queryAll();
    }
    @RequestMapping("operation")
    public String operation(String[] id, Guru guru, String oper) {
        if (id.length > 0) {
            for (String s : id) {
                guruService.delete(s);
            }
        }else if ("add".equals(oper)) {
            String s = guruService.insert(guru);
            return s;
        } else if ("del".equals(oper)) {
            guruService.delete(id[0]);
        } else {
            guru.setGuruId(id[0]);
            guru.setProfile(null);
            guruService.update(guru);
            return id[0];
        }
        return null;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile profile, HttpServletRequest request) {
        System.out.println(id + "++++++++");
        if (profile.getOriginalFilename() == null || profile.getOriginalFilename().equals("")) {
            System.out.println("1111");
        } else {
            String filename = profile.getOriginalFilename();
            String realPath = request.getSession().getServletContext().getRealPath("/guruProfile");
            File file1 = new File(realPath);
            if (!file1.exists()) {
                file1.mkdir();
            }
            try {
                profile.transferTo(new File(realPath + "/" + filename));
                Guru guru=new Guru();
                guru.setGuruId(id);
                guru.setProfile(filename);
                guruService.update(guru);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
