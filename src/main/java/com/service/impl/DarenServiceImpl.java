package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.dao.*;
import com.model.*;
import com.param.DarenParam;
import com.service.DarenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by honor on 2017/11/17.
 */
@Service
public class DarenServiceImpl implements DarenService {

    @Autowired
    DarenChannelDao darenChannelDao;
    @Autowired
    DarenDataDao darenDataDao;
    @Autowired
    DarenFansDao darenFansDao;
    @Autowired
    DarenProductionDao darenProductionDao;
    @Autowired
    DarenMerchantInfosDao darenMerchantInfosDao;
    @Autowired
    DarenWeiboDao darenWeiboDao;

    @Override
    public Integer addWeibo(String weiboData) {
        List weiboList = JSON.parseObject(weiboData,List.class);
        if(!weiboList.isEmpty()){
            for(int i=0;i<weiboList.size();i++){
                String weibo = weiboList.get(i).toString();
                Map weiboMap = JSON.parseObject(weibo,Map.class);
                String nick = weiboMap.get("nick").toString();
                if(nick == null || nick.length() == 0){
                    return 0;
                }
                DarenWeibo darenWeibo = darenWeiboDao.getByNick(nick);
                if(darenWeibo == null){
                    darenWeibo = new DarenWeibo();
                    darenWeibo.setNick(nick);
                    darenWeibo = setDarenWeibo(darenWeibo,weiboMap);
                    darenWeiboDao.insert(darenWeibo);
                }else{
                    darenWeibo = setDarenWeibo(darenWeibo,weiboMap);
                    darenWeiboDao.update(darenWeibo);
                }
            }
            return weiboList.size();
        }
        return 0;
    }

    private DarenWeibo setDarenWeibo(DarenWeibo darenWeibo,Map weiboMap){
        String url = weiboMap.get("url") == null ?"":weiboMap.get("url").toString();
        String guanzhu = weiboMap.get("guanzhu") == null ? "":weiboMap.get("guanzhu").toString();
        String follow = weiboMap.get("follow") == null ? "":weiboMap.get("follow").toString();
        String weibos = weiboMap.get("weibos") == null ? "":weiboMap.get("weibos").toString();
        String intro = weiboMap.get("intro") == null ?"":weiboMap.get("intro").toString();
        String tags = weiboMap.get("tags") == null ?"":weiboMap.get("tags").toString();
        String gender = weiboMap.get("gender") == null ?"":weiboMap.get("gender").toString();
        String local = weiboMap.get("local") == null ?"":weiboMap.get("local").toString();

        darenWeibo.setUrl(url);
        darenWeibo.setFollow(follow);
        darenWeibo.setGuanzhu(guanzhu);
        darenWeibo.setWeibos(weibos);
        darenWeibo.setIntro(intro);
        darenWeibo.setTags(tags);
        darenWeibo.setGender(gender);
        darenWeibo.setLocal(local);
        return darenWeibo;
    }

    @Override
    public Integer addData(DarenParam param) {

        Long userId = param.getUserId();
        DarenData darenData = darenDataDao.getByUserId(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if(darenData == null){
            darenData = new DarenData();
            darenData.setUserId(userId);
            darenData.setDarenNick(param.getDarenNick());
            darenData.setArea(param.getArea());
            darenData.setNick(param.getNick());
            darenData.setReadCount7(param.getReadCount7());
            darenData.setContentType30(param.getContentType30());

            String darenMissionData = param.getDarenMissionData();

            Map missionMap = JSON.parseObject(darenMissionData,Map.class);
            darenData.setAveFee(toInteger(missionMap,"avgFee"));
            darenData.setCompleteMission(toInteger(missionMap,"completeMission"));
            darenData.setCompleteRate(toInteger(missionMap,"completeRate"));
            darenData.setCooperateSellerCount(toInteger(missionMap,"cooperateSellerCount"));
            darenData.setFansCount(toInteger(missionMap,"fansCount"));
            darenData.setReceiveRate(toInteger(missionMap,"receiveRate"));
            darenData.setScore(toBigDecimal(missionMap,"score"));

            darenDataDao.insert(darenData);

            Integer darenDataId = darenData.getId();

            String merchantInfosStr = param.getMerchantInfos();
            if(!("undefined".equals(merchantInfosStr) || "[]".equals(merchantInfosStr))){
                List<Map> merchanList = JSON.parseObject(merchantInfosStr,List.class);
                for (Map merchanMap:merchanList
                     ) {
                    DarenMerchantInfos merchan = new DarenMerchantInfos();
                    merchan.setDaren_data_id(darenDataId);
                    merchan.setUrl(mapToString(merchanMap,"url"));
                    String applyDeadTimeStr =  mapToString(merchanMap,"applyDeadtime");
                    Date date = new Date(0L);
                    try {
                        date = sdf.parse(applyDeadTimeStr);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    merchan.setApplyDeadtime(date);
                    merchan.setFee(toInteger(merchanMap,"fee"));
                    merchan.setMerchanId(toInteger(merchanMap,"id"));
                    merchan.setSubject(mapToString(merchanMap,"subject"));
                    merchan.setType(toInteger(merchanMap,"type"));
                    darenMerchantInfosDao.insert(merchan);
                }
            }

            String channelAbilitys = param.getChannelAbilitys();
            if(!("undefined".equals(channelAbilitys) || "[]".equals(channelAbilitys))){
                List<Map> channelList = JSON.parseObject(channelAbilitys,List.class);

                for (Map channelMap:channelList
                        ) {
                    DarenChannel channel = new DarenChannel();
                    channel.setChannelName(mapToString(channelMap,"channelName"));
                    channel.setDaren_data_id(darenDataId);
                    darenChannelDao.insert(channel);
                }
            }


            String production = param.getProduction();
            if(!("undefined".equals(production) || "[]".equals(production))){
                List<Map> productionList = JSON.parseObject(production,List.class);
                for (Map productionMap:productionList
                        ) {
                    DarenProduction darenProduction = new DarenProduction();
                    darenProduction.setPic(mapToString(productionMap,"pic"));
                    darenProduction.setProductionQCode(mapToString(productionMap,"productionQCode"));
                    darenProduction.setReadNum(toInteger(productionMap,"readNum"));
                    darenProduction.setSummary(mapToString(productionMap,"summary"));
                    darenProduction.setTitle(mapToString(productionMap,"title"));
                    darenProduction.setUrl(mapToString(productionMap,"url"));
                    darenProduction.setDaren_data_id(darenDataId);
                    darenProductionDao.insert(darenProduction);
                }
            }

            String fansData = param.getFansData();
            if(!"[]".equals(fansData)){
                Map fansMap = JSON.parseObject(fansData,Map.class);

                String ageStr = fansMap.get("age").toString();
                if(!("undefined".equals(ageStr) || "[]".equals(ageStr))){
                    List<Map> ageList = JSON.parseObject(ageStr,List.class);
                    for (Map ageMap:ageList
                            ) {
                        DarenFans darenFans = new DarenFans();
                        darenFans.setTitle(mapToString(ageMap,"title"));
                        darenFans.setValue(toInteger(ageMap,"value"));
                        darenFans.setData_type(1);
                        darenFans.setDaren_data_id(darenDataId);
                        darenFansDao.insert(darenFans);
                    }
                }


                String areaStr = fansMap.get("area").toString();

                if(!("undefined".equals(areaStr) || "[]".equals(areaStr))){
                    List<Map> areaList = JSON.parseObject(areaStr,List.class);
                    for (Map ageMap:areaList
                            ) {
                        DarenFans darenFans = new DarenFans();
                        darenFans.setTitle(mapToString(ageMap,"title"));
                        darenFans.setValue(toInteger(ageMap,"value"));
                        darenFans.setData_type(2);
                        darenFans.setDaren_data_id(darenDataId);
                        darenFansDao.insert(darenFans);
                    }
                }

                String cateStr = fansMap.get("cate").toString();
                if(!("undefined".equals(cateStr) || "[]".equals(cateStr))){
                    List<Map> cateList = JSON.parseObject(cateStr,List.class);
                    for (Map ageMap:cateList
                            ) {
                        DarenFans darenFans = new DarenFans();
                        darenFans.setTitle(mapToString(ageMap,"title"));
                        darenFans.setValue(toInteger(ageMap,"value"));
                        darenFans.setData_type(3);
                        darenFans.setDaren_data_id(darenDataId);
                        darenFansDao.insert(darenFans);
                    }
                }

                String genderStr = fansMap.get("gender").toString();
                if(!("undefined".equals(genderStr) || "[]".equals(genderStr))){
                    List<Map> genderList = JSON.parseObject(genderStr,List.class);
                    for (Map ageMap:genderList
                            ) {
                        DarenFans darenFans = new DarenFans();
                        darenFans.setTitle(mapToString(ageMap,"title"));
                        darenFans.setValue(toInteger(ageMap,"value"));
                        darenFans.setData_type(4);
                        darenFans.setDaren_data_id(darenDataId);
                        darenFansDao.insert(darenFans);
                    }
                }

                String interestStr = fansMap.get("interest").toString();
                if(!("undefined".equals(interestStr) || "[]".equals(interestStr))){
                    List<Map> interestList = JSON.parseObject(interestStr,List.class);
                    for (Map ageMap:interestList
                            ) {
                        DarenFans darenFans = new DarenFans();
                        darenFans.setTitle(mapToString(ageMap,"title"));
                        darenFans.setValue(toInteger(ageMap,"value"));
                        darenFans.setData_type(5);
                        darenFans.setDaren_data_id(darenDataId);
                        darenFansDao.insert(darenFans);
                    }
                }

                String womenStr = fansMap.get("women").toString();
                if(!("undefined".equals(womenStr) || "[]".equals(womenStr))){
                    List<Map> womenList = JSON.parseObject(womenStr,List.class);
                    for (Map ageMap:womenList
                            ) {
                        DarenFans darenFans = new DarenFans();
                        darenFans.setTitle(mapToString(ageMap,"title"));
                        darenFans.setValue(toInteger(ageMap,"value"));
                        darenFans.setData_type(6);
                        darenFans.setDaren_data_id(darenDataId);
                        darenFansDao.insert(darenFans);
                    }
                }
            }





            return darenDataId;
        }else {
            return 0;
        }


    }


    private String mapToString(Map map,String key){
        return map.get(key).toString();
    }


    private Integer toInteger(Map map,String key){
        return Integer.parseInt(map.get(key).toString());
    }

    private BigDecimal toBigDecimal(Map map,String key){
        return new BigDecimal(map.get(key).toString());
    }
}
