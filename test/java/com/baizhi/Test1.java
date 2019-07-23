package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.PageNum;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1(){
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Class<User> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            String name=fields[i].getName();
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(i);
            cell.setCellValue(name);
        }
        List<User> users = userDao.selectAll(1, 5);
        for(int i=0;i<fields.length;i++){
            for(int j=0;j<users.size();j++){
                User user = users.get(j);
                Class<? extends User> aClass = user.getClass();
                Field[] fields1 = aClass.getFields();
                Row row = sheet.createRow(i+1);
               for(int z=0;z<fields1.length;z++){
                   Cell cell = row.createCell(z);
               }
            }
        }



    }
}
