package com.ruoyi.common.websocket.domain;

import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.util.StringUtils;

/**
 * @author suahe
 * @description websocket消息
 * @date 2020/12/4 9:50
 */
public class Message {

    //消息类型
    private String msgType;
    //接收用户Id
    private String toUserId;
    //消息内容
    private String content;
    //当前系统时间
    private String curTime;
    //指定发送订阅地址
    private String topic;
    //是否广播消息
    private boolean isToAll = false;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurTime() {
        if(StringUtils.isEmpty(curTime)){
            curTime = DateUtils.getTime();
        }
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isToAll() {
        return isToAll;
    }

    public void setToAll(boolean toAll) {
        isToAll = toAll;
    }
}
