package com.baizhi.controller;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.baizhi.entity.PageNum;
import com.baizhi.entity.User;
import com.baizhi.entity.UserText;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    public Map<String,Object> login(String phone,String password,String code){
        Map<String, Object> map = userService.queryOne(phone, password, code);
        System.out.println("登录信息"+map);
        return map;
    }
    @RequestMapping("regist")
    public Map<String,Object> regist(User user){
        Map<String, Object> regist = userService.regist(user);
        goEasy();
        System.out.println("注册信息"+regist);
        return regist;
    }
    @RequestMapping("queryAll")
    public PageNum<User> queryAll(int page, int rows) {
        PageNum<User> query = userService.query(page, rows);
        return query;
    }
    @RequestMapping("text")
    public Map<String,Object> text(){
        Map<String,Object> map=new HashMap<>();
        List<UserText> userText = userService.queryByDate();
        List<UserText> userText2 = userService.queryBySex();
        System.out.println(userText);
        map.put("User",userText);
        map.put("User2",userText2);
        return map;
    }
    @RequestMapping("operation")
    public String operation(String[] id, User user, String oper) {
        if ("add".equals(oper)) {
            String s = userService.insert(user);
            return s;
        } else if ("del".equals(oper)) {
            userService.delete(id[0]);
        } else {
            user.setUserId(id[0]);
            user.setHead(null);
            userService.update(user);
            return id[0];
        }
        if (id.length > 0) {
            for (String s : id) {
                userService.delete(s);
            }
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(String id, MultipartFile head, HttpServletRequest request) {
        System.out.println(id + "++++++++");
        if (head.getOriginalFilename() == null || head.getOriginalFilename().equals("")) {
            System.out.println("1111");
        } else {
            String filename = head.getOriginalFilename();
            String realPath = request.getSession().getServletContext().getRealPath("/userHead");
            File file1 = new File(realPath);
            if (!file1.exists()) {
                file1.mkdir();
            }
            try {
                head.transferTo(new File(realPath + "/" + filename));
                User user = new User();
                user.setUserId(id);
                user.setHead(filename);
                userService.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("updateStatus")
    public String updateStatus(String status,String id) {
        System.out.println(status + "_______" + id + "++++++++++");
        if (status.equals("正常")) {
            userService.updateStatus(id, "冻结");
            return "1";
        } else {
            userService.updateStatus(id, "正常");
            return "1";
        }
    }
    @RequestMapping("importTo")
    public void importTo(MultipartFile file) throws Exception {
        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        List<User> users = new ArrayList<>();
        System.out.println(lastRowNum);
        for (int i = 1; i < lastRowNum; i++) {
            User user = new User();
            Row row = sheet.getRow(i+1);
            Cell cell = row.getCell(0);
            String id = cell.getStringCellValue();
            user.setUserId(id);
            Cell cell1 = row.getCell(1);
            user.setPhone(cell1.getStringCellValue());
            Cell cell2 = row.getCell(2);
            user.setPassword(cell2.getStringCellValue());
            Cell cell3 = row.getCell(3);
            user.setSalt(cell3.getStringCellValue());
            Cell cell4 = row.getCell(4);
            user.setDharmaName(cell4.getStringCellValue());
            Cell cell5 = row.getCell(5);
            user.setProvince(cell5.getStringCellValue());
            Cell cell6 = row.getCell(6);
            user.setCity(cell6.getStringCellValue());
            Cell cell7 = row.getCell(7);
            user.setGender(cell7.getStringCellValue());
            Cell cell8 = row.getCell(8);
            user.setPersonalSign(cell8.getStringCellValue());
            Cell cell9 = row.getCell(9);
            user.setProfile(cell9.getStringCellValue());
            Cell cell10 = row.getCell(10);
            user.setStatus(cell10.getStringCellValue());
            Cell cell11= row.getCell(11);
            user.setGuruId(cell11.getStringCellValue());
            Cell cell12 = row.getCell(12);
            user.setHead(cell12.getStringCellValue());
            Cell cell13 = row.getCell(13);
            String value = cell13.getStringCellValue();
            System.out.println(value);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(value);
            user.setRegistTime(date);
            users.add(user);
        }
        for (User user : users) {
            System.out.println(user);
        }
    }
    @RequestMapping("onexport")
    public void export(HttpServletResponse response) throws Exception {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment;filename=ExcelTest.xls");
        List<User> users = userService.queryAll();
        System.out.println(users);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户","用户表"),
                User.class, users);
        workbook.write(response.getOutputStream());
        workbook.close();
    }
    @RequestMapping("goEasy")
    public void goEasy(){
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-60bc04e03c8f4072976612989be0da54");
        Map<String, Object> text = text();
        String s = JSON.toJSONString(text);
        goEasy.publish("content",s);
    }
}
