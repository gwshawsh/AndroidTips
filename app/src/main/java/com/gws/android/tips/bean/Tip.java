package com.gws.android.tips.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import java.util.UUID;

/**
 * Created by gws on 2017/5/2.
 */

@Entity
public class Tip {
    @Id
    private String id;
    private String title;
    private String content;
    private String creatTime = new Date().toString();
    private String updateTime;
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getCreatTime() {
        return this.creatTime;
    }
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Generated(hash = 1846016387)
    public Tip(String id, String title, String content, String creatTime,
            String updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }
    @Generated(hash = 208979692)
    public Tip() {
    }

    public String newId(){
        id = UUID.randomUUID().toString();
        return id;
    }
}
