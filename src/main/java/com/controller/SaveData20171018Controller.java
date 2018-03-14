package com.controller;

import com.model.DataLink;
import com.model.SaveData20171018;
import com.model.SaveData20171018_1;
import com.model.XiaoHua;
import com.service.SaveData20171018Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by honor on 2017/10/26.
 */
@Controller
@RequestMapping("/savedata")
public class SaveData20171018Controller {
    @Autowired
    SaveData20171018Service saveData20171018Service;



    @RequestMapping(value = "/addXiaoHua" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void addXiaoHua(XiaoHua xiaoHua) throws Exception{
        saveData20171018Service.addXiaoHua(xiaoHua);
    }


    @RequestMapping(value = "/savedata1" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map saveData1(SaveData20171018_1 data) throws Exception{
        Map map = saveData20171018Service.saveData1(data);
        return map;
    }

    @RequestMapping(value = "/addlink" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void addLink() throws Exception{
        saveData20171018Service.addLink();
    }

    @RequestMapping(value = "/savedata" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map saveData(SaveData20171018 data) throws Exception{
        Map map = saveData20171018Service.saveData(data);
        return map;
    }

    @RequestMapping(value = "/setrank" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void setRank() throws Exception{
        saveData20171018Service.setRank();
    }

    @RequestMapping(value = "/getlinkall" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List getLinkAll() throws Exception{

        List<DataLink> list =  saveData20171018Service.getLinkAll();
        List<String> linkList = new ArrayList<>();
        for (DataLink dataLink:list
             ) {
            linkList.add(dataLink.getLink());
        }
        return linkList;
    }




}
