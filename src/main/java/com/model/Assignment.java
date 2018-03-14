package com.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by honor on 2017/11/21.
 */
public class Assignment {
    private Integer id;
    private String assignmentType;
    private Integer assignmentId;
    private Integer commission;
    private String assignmentTitle;
    private String assignmentContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueTime;
    private Integer dueTimeStamp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
    private Integer releaseTimeStamp;
    private String shopName;
    private String shopPIN;
    private String shopPhone;
    private String shopUrl;
    private Integer packagePriceMax;
    private Integer packagePriceMin;

    public String getAssignmentContent() {
        return assignmentContent;
    }

    public void setAssignmentContent(String assignmentContent) {
        this.assignmentContent = assignmentContent;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getDueTimeStamp() {
        return dueTimeStamp;
    }

    public void setDueTimeStamp(Integer dueTimeStamp) {
        this.dueTimeStamp = dueTimeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getReleaseTimeStamp() {
        return releaseTimeStamp;
    }

    public void setReleaseTimeStamp(Integer releaseTimeStamp) {
        this.releaseTimeStamp = releaseTimeStamp;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopPIN() {
        return shopPIN;
    }

    public void setShopPIN(String shopPIN) {
        this.shopPIN = shopPIN;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public Integer getPackagePriceMax() {
        return packagePriceMax;
    }

    public void setPackagePriceMax(Integer packagePriceMax) {
        this.packagePriceMax = packagePriceMax;
    }

    public Integer getPackagePriceMin() {
        return packagePriceMin;
    }

    public void setPackagePriceMin(Integer packagePriceMin) {
        this.packagePriceMin = packagePriceMin;
    }
}
