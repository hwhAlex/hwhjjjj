package com.model;

/**
 * Created by honor on 2017/11/17.
 */
public class DarenChannel {
    private Integer id;
    private String channelName;
    private Integer daren_data_id;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

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
}
