package com.baizhi.controller;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.PageNum;
import com.baizhi.service.AdminService;
import com.baizhi.service.CarouselService;
import org.apache.http.HttpResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CarouselDao carouselDao;
    @RequestMapping("queryAll")
    @ResponseBody
    public PageNum queryAllEmp(int page, int rows){
        return carouselService.query(page,rows);
    }
    @RequestMapping("upload")
    @ResponseBody
    public void upload(String id,MultipartFile imgpath, HttpServletRequest request, HttpServletResponse response){
        Carousel carousel = new Carousel();
        carousel.setCarouselId(id);
        //没改变图片或者没传数据
        if(imgpath.getOriginalFilename()==null||imgpath.getOriginalFilename().equals("")){
            Carousel carousel1 = carouselDao.selectOne(id);
            carousel.setImgpath(carousel1.getImgpath());
            carouselService.update(carousel);
        }else {
            String filename = imgpath.getOriginalFilename();
            Carousel carousel1 = carouselDao.selectOne(id);
            //传了数据但是没改变图片
            if (filename.equals(carousel1.getImgpath())) {
                carousel.setImgpath(filename);
                carouselService.update(carousel);
            } else {
                //图片改变
                String realPath = request.getSession().getServletContext().getRealPath("/upload");
                File file1 = new File(realPath);
                if (!file1.exists()) {
                    file1.mkdir();
                }
                try {
                    imgpath.transferTo(new File(realPath + "/" + filename));
                    carousel.setImgpath(filename);
                    carousel.setImgpath(imgpath.getOriginalFilename());
                    System.out.println(filename);
                    carouselService.update(carousel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @RequestMapping("operation")
    @ResponseBody
    public String operation(String[] id,Carousel carousel,String oper,HttpSession session) throws IOException {
        if("add".equals(oper)){
            String s = carouselService.insert(carousel);
            return s;
        }else if("del".equals(oper)){
            carouselService.delete(id[0]);
        }else{
            carousel.setCarouselId(id[0]);
            carousel.setImgpath(null);
            carouselService.update(carousel);
            return id[0];
        }
        if(id.length>0){
            for (String s : id) {
                carouselService.delete(s);
            }
        }
        return null;
    }
}
