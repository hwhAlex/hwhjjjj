package com.controller;

import com.model.DarenWeibo;
import com.model.User;
import com.param.DarenParam;
import com.service.DarenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by honor on 2017/11/17.
 */
@Controller
@RequestMapping("/daren")
public class DarenController {
    @Autowired
    DarenService darenService;

    @RequestMapping(value = "/addData" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Integer addData(DarenParam param) throws Exception{
       Integer id =   darenService.addData(param);

        return id;
    }

    @RequestMapping(value = "/addWeibo" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Integer addWeibo(String  weiboData) throws Exception{
        Integer id =   darenService.addWeibo(weiboData);

        return id;
    }

}
