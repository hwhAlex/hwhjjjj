package com.model;

/**
 * Created by honor on 2017/11/6.
 */
public class Spider20171106 {
    private Integer id;
    private String darenName;
    private Long darenId;
    private Long contentId;
    private String recruitTitle;
    private String recruitId;
    private String contentUrl;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Long getDarenId() {
        return darenId;
    }

    public void setDarenId(Long darenId) {
        this.darenId = darenId;
    }

    public String getDarenName() {
        return darenName;
    }

    public void setDarenName(String darenName) {
        this.darenName = darenName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(String recruitId) {
        this.recruitId = recruitId;
    }

    public String getRecruitTitle() {
        return recruitTitle;
    }

    public void setRecruitTitle(String recruitTitle) {
        this.recruitTitle = recruitTitle;
    }
}
