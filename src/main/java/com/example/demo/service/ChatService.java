package com.example.demo.service;

import com.example.demo.dto.ChatDTO;

import java.util.List;

public interface ChatService {

    public int insertReChatMessage(ChatDTO dto);

    public List<ChatDTO> selectReChatMessageById();

}
