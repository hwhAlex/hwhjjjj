package com.model;

/**
 * Created by honor on 2017/11/17.
 */
public class DarenProduction {
    private Integer id;
    private String pic;
    private String productionQCode;
    private Integer readNum;
    private String summary;
    private String title;
    private String url;
    private Integer daren_data_id;

    public Integer getDaren_data_id() {
        return daren_data_id;
    }

    public void setDaren_data_id(Integer daren_data_id) {
        this.daren_data_id = daren_data_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProductionQCode() {
        return productionQCode;
    }

    public void setProductionQCode(String productionQCode) {
        this.productionQCode = productionQCode;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
