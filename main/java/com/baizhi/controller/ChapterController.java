package com.baizhi.controller;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.PageNum;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumDao albumDao;
    @RequestMapping("queryAll")
    public PageNum<Chapter> queryAll(int page,int rows,String id){
        PageNum<Chapter> chapterPageNum = chapterService.selectAll(id, page, rows);
        return chapterPageNum;
    }
    @RequestMapping("operation")
    public String operation(String[] id, Chapter chapter, String oper){
        if("add".equals(oper)){
            System.out.println(chapter+"+++++++++");
            String insert = chapterService.insert(chapter);
            return insert;
        }else if("del".equals(oper)){
            chapterService.delete(id[0]);
        }else{
            chapter.setAlbumId(id[0]);
            chapterService.update(chapter);
            return id[0];
        }
        if(id.length>0){
            for (String s : id) {
                chapterService.delete(s);
            }
        }
        return null;
    }
    @RequestMapping("download")
    public void download(String downPath, HttpSession session, HttpServletResponse response) throws IOException {

        String realPath=session.getServletContext().getRealPath("/uploadDownPath");
        byte[] bs= FileUtils.readFileToByteArray(new File(realPath+"/"+downPath));
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(downPath,"UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bs);
        if(outputStream!=null){ outputStream.flush();}
        if(outputStream!=null){outputStream.close();}
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile downPath, HttpServletRequest request, String albumId){
        System.out.println(id+"++++++++++++++");
        System.out.println(albumId+"++++++++++++++");
        if(downPath.getOriginalFilename()==null||downPath.getOriginalFilename().equals("")){
            System.out.println("1111");
        }else {
            String filename = downPath.getOriginalFilename();
            String realPath = request.getSession().getServletContext().getRealPath("/uploadDownPath");
            File file1 = new File(realPath);
            if (!file1.exists()) {
                file1.mkdir();
            }
            try {
                downPath.transferTo(new File(realPath + "/" + filename));
                File file = new File(realPath, filename);
                long length = file.length();
                Chapter chapter=new Chapter();
               chapter.setChapterId(id);
               chapter.setSize((double) length);
               chapter.setDownPath(filename);
               chapter.setAlbumId(albumId);
               chapterService.update(chapter);
                Album album = albumDao.selectOne(albumId);
                album.setCount(album.getCount()+1);
                albumDao.update(album);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
