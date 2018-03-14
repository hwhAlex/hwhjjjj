package com.model;

import java.math.BigDecimal;

/**
 * Created by honor on 2017/11/17.
 */
public class DarenData {
    private Integer id;
    private Long userId;
    private String darenNick;
    private Integer aveFee;
    private Integer completeMission;
    private Integer completeRate;
    private Integer cooperateSellerCount;
    private Integer fansCount;
    private Integer receiveRate;
    private BigDecimal score;
    private String area;
    private String nick;
    private String readCount7;
    private String contentType30;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAveFee() {
        return aveFee;
    }

    public void setAveFee(Integer aveFee) {
        this.aveFee = aveFee;
    }

    public Integer getCompleteMission() {
        return completeMission;
    }

    public void setCompleteMission(Integer completeMission) {
        this.completeMission = completeMission;
    }

    public Integer getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(Integer completeRate) {
        this.completeRate = completeRate;
    }

    public String getContentType30() {
        return contentType30;
    }

    public void setContentType30(String contentType30) {
        this.contentType30 = contentType30;
    }

    public Integer getCooperateSellerCount() {
        return cooperateSellerCount;
    }

    public void setCooperateSellerCount(Integer cooperateSellerCount) {
        this.cooperateSellerCount = cooperateSellerCount;
    }

    public String getDarenNick() {
        return darenNick;
    }

    public void setDarenNick(String darenNick) {
        this.darenNick = darenNick;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getReadCount7() {
        return readCount7;
    }

    public void setReadCount7(String readCount7) {
        this.readCount7 = readCount7;
    }

    public Integer getReceiveRate() {
        return receiveRate;
    }

    public void setReceiveRate(Integer receiveRate) {
        this.receiveRate = receiveRate;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
