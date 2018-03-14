package com.model;

import java.util.Date;

/**
 * Created by honor on 2017/11/20.
 */
public class DarenMerchantInfos {
    private Integer id;
    private Date applyDeadtime;
    private Integer fee;
    private Integer merchanId;
    private String subject;
    private Integer type;
    private String url;
    private Integer daren_data_id;

    public Date getApplyDeadtime() {
        return applyDeadtime;
    }

    public void setApplyDeadtime(Date applyDeadtime) {
        this.applyDeadtime = applyDeadtime;
    }

    public Integer getDaren_data_id() {
        return daren_data_id;
    }

    public void setDaren_data_id(Integer daren_data_id) {
        this.daren_data_id = daren_data_id;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchanId() {
        return merchanId;
    }

    public void setMerchanId(Integer merchanId) {
        this.merchanId = merchanId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
