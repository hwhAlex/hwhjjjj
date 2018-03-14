package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.BeanUtil;
import com.common.HttpRequest;
import com.common.SFHttpClient;
import com.dao.AllContentDao;
import com.model.AllContent;
import com.param.AllContentParam;
import com.service.AllContentService;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by honor on 2018/1/31.
 */
@Service
public class AllContentServiceImpl implements AllContentService {

    @Autowired
    private AllContentDao allContentDao;
    @Override
    public List<Integer> sendAllContent(Integer id) {
        List<AllContent> contentList = allContentDao.getNoHandle(id);
        List<Integer> idList = new ArrayList<>();
        for (AllContent allContent:contentList
             ) {
           idList.add(allContent.getId());
        }
        /*HttpClient httpClient = SFHttpClient.getHttpClient(443);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("token", "99d43670-5fac-11e7-907b-a6006ad3dba0"));
        nvps.add(new BasicNameValuePair("contentList", JSON.toJSONString(contentList)));

        HttpPost httpPost = new HttpPost("http://127.0.0.1:8086/darendata/insertContent.wb");
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        HttpResponse response = null;
        String resultString = "";
        try {
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
           e.printStackTrace();
        }*/
        Map paramMap = new HashMap();
        paramMap.put("token","99d43670-5fac-11e7-907b-a6006ad3dba0");
        paramMap.put("contentList",JSON.toJSONString(contentList));


       // String result = HttpRequest.jsonPost("http://127.0.0.1:8086/darendata/insertContent.wb",paramMap);
        //String result = SFHttpClient.doPost("http://molitest.willbe.net.cn/editor/darendata/insertContent.wb",paramMap);
        String result = SFHttpClient.doPost("http://baowen.molimediagroup.com/editor/darendata/insertContent.wb",paramMap);
        //System.out.println(result);
        try {
            Map resultMap = JSON.parseObject(result,Map.class);
            if(resultMap.get("status") != null && Integer.parseInt(resultMap.get("status").toString()) == 0){
                allContentDao.updateDone(idList);
                System.out.println("-----传送成功---------");
            }else {
                System.out.println("失败失败");
            }
        }catch (Exception e){

        }


        return idList;
    }
}
