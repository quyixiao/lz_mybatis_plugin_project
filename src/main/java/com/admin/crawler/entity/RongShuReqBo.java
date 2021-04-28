package com.admin.crawler.entity;

import java.io.Serializable;

public class RongShuReqBo implements Serializable {
    private static final long serialVersionUID = -8106262310495544261L;
    private String channelId;
    private String sign;
    private String timestamp;
    private String request;


    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
