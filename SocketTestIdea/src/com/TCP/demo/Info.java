package com.TCP.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author lijie
 * @version 1.00
 * @Description: 消息实体
 * @date 2019-08-26 14:50
 */
public class Info implements Serializable {

    public Info() {
        this.id = UUID.randomUUID().toString();
    }

    private String id;

    private String fromWho;

    private String msg;

    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
