package com.param;

import java.io.Serializable;

/**
 * Created by honor on 2017/9/21.
 */
public class InvitationDocParam implements Serializable {
    private String title;
    private String content;
    private String id;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
