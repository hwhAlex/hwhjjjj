package com.service.impl;

import com.dao.DataLinkDao;
import com.dao.SaveData20171018Dao;
import com.dao.SaveData20171018_1Dao;
import com.dao.XiaoHuaDao;
import com.model.DataLink;
import com.model.SaveData20171018;
import com.model.SaveData20171018_1;
import com.model.XiaoHua;
import com.service.SaveData20171018Service;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by honor on 2017/10/26.
 */
@Service
public class SaveData20171018ServiceImpl implements SaveData20171018Service {
    @Autowired
    SaveData20171018Dao saveData20171018Dao;
    @Autowired
    SaveData20171018_1Dao saveData20171018_1Dao;
    @Autowired
    DataLinkDao dataLinkDao;
    @Autowired
    XiaoHuaDao xiaoHuaDao;


    @Override
    public void addXiaoHua(XiaoHua xiaoHua) {
        if(xiaoHua.getLink()!=null){
            XiaoHua data = xiaoHuaDao.getByLink(xiaoHua.getLink());
            if(data == null){
                xiaoHuaDao.insert(xiaoHua);
            }
        }
    }

    @Override
    public List<DataLink> getLinkAll() {
        return dataLinkDao.getAll();
    }


    @Override
    public Map saveData1(SaveData20171018_1 data) {
        Map<String,Object> returnMap= new HashMap<>();
        String link = data.getLink();
        String itemId = data.getItemId();
        SaveData20171018_1 oldData = saveData20171018_1Dao.getByLinkAndItem(link,itemId);

        if(oldData == null){
            Integer linkCount = saveData20171018_1Dao.getLinkCount(link);
            data.setRank(linkCount+1);
            saveData20171018_1Dao.insert(data);
            returnMap.put("err",false);
            returnMap.put("msg","add");
        }else {
            data.setId(oldData.getId());
            data.setRank(oldData.getRank());
            saveData20171018_1Dao.update(data);
            returnMap.put("err",false);
            returnMap.put("msg","update");
        }
        return returnMap;

    }

    @Override
    public Map saveData(SaveData20171018 data){
        Map<String,Object> returnMap= new HashMap<>();
        String link = data.getLink();
        String itemId = data.getItemId();
        SaveData20171018 oldData = saveData20171018Dao.getByLinkAndItem(link,itemId);

        if(oldData == null){
            Integer linkCount = saveData20171018Dao.getLinkCount(link);
            data.setRank(linkCount+1);
            saveData20171018Dao.insert(data);
            returnMap.put("err",false);
            returnMap.put("msg","add");
        }else {
            data.setId(oldData.getId());
            data.setRank(oldData.getRank());
            saveData20171018Dao.update(data);
            returnMap.put("err",false);
            returnMap.put("msg","update");
        }
        return returnMap;

    }

    @Override
   public void setRank(){
       List<SaveData20171018> list = saveData20171018Dao.findRankZero();
       for (SaveData20171018 data:list
            ) {
           String link = data.getLink();
           Integer count = saveData20171018Dao.getLinkCount(link);
           data.setRank(count);
           saveData20171018Dao.update(data);
       }

   }

    @Override
    public List<SaveData20171018> getAll() {
        return saveData20171018Dao.findAll();
    }

    @Override
    public void addLink() {
        Workbook wb = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("E:/Desktop/link.xlsx");
            wb = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        int i = 1;
        while (true) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String link = row.getCell(16).getStringCellValue();
                link = link.trim();
                link = link.replaceAll(" ","");
                link = link.replaceAll(" ","");
                if(!"".equals(link)){
                    String contentId = "";
                    int index = link.indexOf("contentId=");
                    if (index != -1) {
                        int cha = link.length() - index;
                        if (cha <= 22) {
                            contentId = link.substring(index + 10, link.length());

                        } else {
                            contentId = link.substring(index + 10, index + 22);
                        }
                        contentId = "%" + contentId + "%";
                        List<SaveData20171018> data = saveData20171018Dao.getLinkAs(contentId);
                        if (data.isEmpty()) {
                            DataLink havingLink = dataLinkDao.getByLink(link);
                            if(havingLink == null){
                                DataLink dataLink = new DataLink();
                                dataLink.setLink(link);
                                dataLinkDao.insert(dataLink);
                            }

                        }
                    } else {
                        DataLink havingLink = dataLinkDao.getByLink(link);
                        if(havingLink == null){
                            DataLink dataLink = new DataLink();
                            dataLink.setLink(link);
                            dataLinkDao.insert(dataLink);
                        }
                    }
                }
                i++;
                if(i == 10570){
                    System.out.println("end ======="+i);
                    break;
                }
            } else {
                System.out.println("end ======="+i);
                break;
            }


        }
    }


}
