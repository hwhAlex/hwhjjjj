package com.model;

import java.util.Date;

/**
 * Created by honor on 2017/12/11.
 */
public class InvitationOld {
    private Integer id;
    private String content;
    private String title;
    private String picUrl;
    private String sharer;
    private Integer sharerUid;
    private Short status;
    private String keywords;
    private Short isAudit;
    private String publishOvs;
    private Date gmtCreate;
    private Short isDel;
    private Integer manageId;
    private String message;
    private String platformId;
    private Short isPreview;
    private Short readonly;
    private Integer taskId;
    private String log;
    private Date gmtModify;
    private String shops;
    private String columnId;
    private Short thirdAudit;
    private String channelId;
    private Short isHot;
    private Short isHandle;
    /*private String publishUrl;*/

    public Short getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Short isHandle) {
        this.isHandle = isHandle;
    }

    public Short getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Short isAudit) {
        this.isAudit = isAudit;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public Short getIsHot() {
        return isHot;
    }

    public void setIsHot(Short isHot) {
        this.isHot = isHot;
    }

    public Short getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(Short isPreview) {
        this.isPreview = isPreview;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPublishOvs() {
        return publishOvs;
    }

    public void setPublishOvs(String publishOvs) {
        this.publishOvs = publishOvs;
    }

    public Short getReadonly() {
        return readonly;
    }

    public void setReadonly(Short readonly) {
        this.readonly = readonly;
    }



    public String getSharer() {
        return sharer;
    }

    public void setSharer(String sharer) {
        this.sharer = sharer;
    }

    public Integer getSharerUid() {
        return sharerUid;
    }

    public void setSharerUid(Integer sharerUid) {
        this.sharerUid = sharerUid;
    }

    public String getShops() {
        return shops;
    }

    public void setShops(String shops) {
        this.shops = shops;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Short getThirdAudit() {
        return thirdAudit;
    }

    public void setThirdAudit(Short thirdAudit) {
        this.thirdAudit = thirdAudit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
