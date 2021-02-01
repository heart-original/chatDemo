package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ChatDTO {

    /** 用户id */
    private Long wxUserId;

    /** 接收人id */
    private Long receiver;

    /** 消息 */
    private String chatMessage;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date sendTime;

    /** 消息类别（0 文字 1 图片） */
    private String messageType;

    public Long getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(Long wxUserId) {
        this.wxUserId = wxUserId;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "ChatDTO{" +
                "wxUserId=" + wxUserId +
                ", receiver=" + receiver +
                ", chatMessage='" + chatMessage + '\'' +
                ", sendTime=" + sendTime +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
