package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.ChatDTO;
import com.example.demo.service.ChatService;
import com.example.demo.util.ServerEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{name}",encoders = {ServerEncoder.class})
@RestController
public class WebSocketServer {

    /*此处天坑，不能直接注入 应该为此种写法 否则调用其中方法会报错*/
    private static ChatService chatService;

    @Autowired
    public void setChatService(ChatService chatService){
        WebSocketServer.chatService = chatService;
    }

    //存储客户端的连接对象,每个客户端连接都会产生一个连接对象
    private static ConcurrentHashMap<String,WebSocketServer> map=new ConcurrentHashMap();
    //每个连接都会有自己的会话
    private Session session;
    private String name;
    @OnOpen
    public void open(@PathParam("name") String name, Session session) throws Exception{
        map.put(name,this);
        System.out.println(name+"连接服务器成功");
        System.out.println("客户端连接个数:"+getConnetNum());

        this.session=session;
        this.name=name;
        /*System.out.println("----"+chatService.selectReChatMessageById().size());
        send(chatService.selectReChatMessageById());*/
    }
    @OnClose
    public void close(){
        map.remove(name);
        System.out.println(name+"断开了服务器连接");
    }
    @OnError
    public void error(Throwable error){
        error.printStackTrace();
        System.out.println(name+"出现了异常");
    }
    @OnMessage(maxMessageSize = 1024000)
    public void getMessage(String message) throws IOException,Exception {
        System.out.println("收到"+name+":"+message);
        System.out.println("客户端连接个数:"+getConnetNum());

        ChatDTO dto ;
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        dto = objectMapper.readValue(message, ChatDTO.class);
        dto.setSendTime(new Date());
        jsonObject.put("nickName",name+":");
        jsonObject.put("messageType",dto.getMessageType());
        jsonObject.put("message",dto.getChatMessage());

        Set<Map.Entry<String, WebSocketServer>> entries = map.entrySet();
        for (Map.Entry<String, WebSocketServer> entry : entries) {
            if(!entry.getKey().equals(name)){//将消息转发到其他非自身客户端
                //entry.getValue().send(message);
                /*chatService.insertReChatMessage(dto);*/
                entry.getValue().send(jsonObject.toJSONString());
            }
        }
    }

    public void send(String message) throws IOException,Exception {
        if(session.isOpen()){
            session.getBasicRemote().sendText(message);
        }
    }

    /*public void send(List list) throws IOException,Exception {
        if(session.isOpen()){
            session.getBasicRemote().sendObject(list);
        }
    }*/

    public int  getConnetNum(){
        return map.size();
    }

}