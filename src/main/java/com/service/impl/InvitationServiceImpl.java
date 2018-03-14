package com.service.impl;

import com.common.BeanUtil;
import com.dao.*;
import com.model.*;
import com.service.InvitationService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by honor on 2017/12/11.
 */
@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationDao invitationDao;
    @Autowired
    private InvitationOldDao invitationOldDao;
    @Autowired
    private InvitationGoodsDao invitationGoodsDao;
    @Autowired
    private InvitationGoodsOldDao invitationGoodsOldDao;
    @Autowired
    private OABaowenUserDao oaBaowenUserDao;
    @Autowired
    private ContentTypeDao contentTypeDao;




    @Override
    public void addOldInvitation() {
        List<InvitationOld> oldList = invitationOldDao.getNoHandle();
        List<OABaoWenUser> userList = oaBaowenUserDao.getAll();
        Map<Integer,Integer> idMap = new HashMap<>();
        for (OABaoWenUser user:userList
             ) {
            idMap.put(user.getBaowenId(),user.getOaId());
        }
        for (InvitationOld old:oldList
             ) {
            Invitation invitation = new Invitation();
            BeanUtil.copyProperties(old,invitation);
            Integer sharerId = invitation.getSharerUid();
            if(idMap.containsKey(sharerId)){
                invitation.setSharerUid(idMap.get(sharerId));
            }else {
                invitation.setSharerUid(0);
            }
            invitation.setId(null);
            if(invitation.getChannelId() == null){
                invitation.setChannelId("");
            }
            if(invitation.getColumnId() == null){
                invitation.setColumnId("");
            }
            if(invitation.getContent() == null){
                invitation.setContent("");
            }
            if(invitation.getGmtCreate() == null){
                invitation.setGmtCreate(new Date(0));
            }
            if(invitation.getGmtModify() == null){
                invitation.setGmtModify(new Date(0));
            }
            if(invitation.getIsAudit() == null){
                invitation.setIsAudit((short)0);
            }
            if(invitation.getIsDel() == null){
                invitation.setIsDel((short)0);
            }
            if(invitation.getIsHot() == null){
                invitation.setIsHot((short)0);
            }
            if(invitation.getIsPreview() == null){
                invitation.setIsPreview((short)0);
            }
            if(invitation.getKeywords() == null){
                invitation.setKeywords("");
            }
            if(invitation.getLog() == null){
                invitation.setLog("");
            }
            if(invitation.getManageId() == null){
                invitation.setManageId(0);
            }
            if(invitation.getMessage() == null){
                invitation.setMessage("");
            }
            if(invitation.getPicUrl() == null){
                invitation.setPicUrl("");
            }
            if(invitation.getPlatformId() == null){
                invitation.setPlatformId("");
            }
            if(invitation.getPublishOvs() == null){
                invitation.setPublishOvs("");
            }
            if(invitation.getReadonly() == null){
                invitation.setReadonly((short)0);
            }
            if(invitation.getSharer() == null){
                invitation.setSharer("");
            }
            if(invitation.getSharerUid() == null){
                invitation.setSharerUid(0);
            }
            if(invitation.getShops() == null){
                invitation.setShops("");
            }
            if(invitation.getShops() == null){
                invitation.setShops("");
            }
            if(invitation.getStatus() == null){
                invitation.setStatus((short)0);
            }
            if(invitation.getPicUrl() == null){
                invitation.setPicUrl("");
            }
            if(invitation.getPlatformId() == null){
                invitation.setPlatformId("");
            }
            if(invitation.getTaskId() == null){
                invitation.setTaskId(0);
            }
            if(invitation.getTitle() == null){
                invitation.setTitle("");
            }
            invitationDao.insert(invitation);
            ContentType contentType = new ContentType();
            contentType.setType(1);
            contentType.setTypeId(invitation.getId());
            contentTypeDao.insert(contentType);
            Integer invitationId = invitation.getId();
            Integer oldId = old.getId();
            List<InvitationGoodsOld> oldGoodsList = invitationGoodsOldDao.getByInvitationOld(oldId);
            for (InvitationGoodsOld goodsOld:oldGoodsList
                 ) {
                InvitationGoods goods = new InvitationGoods();
                BeanUtil.copyProperties(goodsOld,goods);
                goods.setInvitationId(invitationId);
                goods.setId(null);
                if(goods.getDescription() == null){
                    goods.setDescription("");
                }
                if(goods.getIsShow() == null){
                    goods.setIsShow((short)0);
                }
                if(goods.getIsTmall() == null){
                    goods.setIsTmall((short)0);
                }
                if(goods.getItemId() == null){
                    goods.setItemId("");
                }
                if(goods.getItemPic() == null){
                    goods.setItemPic("");
                }
                if(goods.getItemTitle() == null){
                    goods.setItemTitle("");
                }
                if(goods.getNick() == null){
                    goods.setNick("");
                }
                if(goods.getOpenId() == null){
                    goods.setOpenId("");
                }
                if(goods.getSort() == null){
                    goods.setSort(0);
                }
                if(goods.getSource() == null){
                    goods.setSource((short)0);
                }
                invitationGoodsDao.insert(goods);
            }
            invitationOldDao.update(oldId);
        }

    }

    @Override
    public void addOAId() {
        Workbook wb = null;
        try {
            FileInputStream in = new FileInputStream("E:/Desktop/BAOWENOAUSER.xlsx");
            wb = new XSSFWorkbook(in);
        }catch (Exception e){
            e.printStackTrace();

        }

        Sheet sheet = wb.getSheetAt(0);
        int i = 0;
        while (true) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell oaCell = row.getCell(0);
                if(oaCell == null){
                    break;
                }
                Double oaStr = oaCell.getNumericCellValue();
                if(oaStr == null || oaStr == 0){
                    break;
                }
                Integer oaId = oaStr.intValue();
                Cell baowenCell =row.getCell(3);
                if(baowenCell != null){
                    Double baowenStr = baowenCell.getNumericCellValue();
                    if(baowenStr != null && oaStr != 0){
                        Integer baowenId = baowenStr.intValue();
                        OABaoWenUser user = new OABaoWenUser();
                        user.setBaowenId(baowenId);
                        user.setOaId(oaId);
                        oaBaowenUserDao.insert(user);
                    }
                }

            }
            i++;
        }
    }
}
