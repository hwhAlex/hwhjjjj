package com.model;

/**
 * Created by honor on 2017/11/17.
 */
public class DarenFans {
    private Integer id;
    private String title;
    private Integer value;
    private Integer data_type;
    private Integer daren_data_id;

    public Integer getDaren_data_id() {
        return daren_data_id;
    }

    public void setDaren_data_id(Integer daren_data_id) {
        this.daren_data_id = daren_data_id;
    }

    public Integer getData_type() {
        return data_type;
    }

    public void setData_type(Integer data_type) {
        this.data_type = data_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
