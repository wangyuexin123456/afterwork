package com.baizhi.controller;
import com.baizhi.entity.Album;
import com.baizhi.entity.PageNum;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/album")
public class AlbumController{
    @Autowired
    private AlbumService albumService;
    @RequestMapping("queryAll")
    public PageNum<Album> queryAll(int page,int rows){
        PageNum<Album> list = albumService.query(page, rows);
        return list;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile cover, HttpServletRequest request, HttpServletResponse response){
        System.out.println(id+"-----________________");
        if(cover.getOriginalFilename()==null||cover.getOriginalFilename().equals("")){
            System.out.println("1111");
        }else {
            String filename=null;
            if(cover.getOriginalFilename().length()>20){
                filename=cover.getOriginalFilename();
            }else{
            filename = cover.getOriginalFilename();
            }
            String realPath = request.getSession().getServletContext().getRealPath("/uploadCover");
            File file1 = new File(realPath);
            if (!file1.exists()) {
                file1.mkdir();
            }
            try {
                cover.transferTo(new File(realPath + "/" + filename));
                Album album=new Album();
                album.setAlbumId(id);

                album.setCover(filename);
                albumService.update(album);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("operation")
    public String operation(String[] id, Album album,String oper){
        if("add".equals(oper)){
            String s = albumService.insert(album);
            return s;
        }else if("del".equals(oper)){
            albumService.delete(id[0]);
        }else{
            album.setAlbumId(id[0]);
            album.setCover(null);
            albumService.update(album);
            return album.getAlbumId();
        }
        if(id.length>0){
            for (String s : id) {
                albumService.delete(s);
            }
        }
        return null;
    }
}