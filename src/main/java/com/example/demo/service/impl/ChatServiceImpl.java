package com.example.demo.service.impl;

import com.example.demo.dto.ChatDTO;
import com.example.demo.mapper.ChatMapper;
import com.example.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public int insertReChatMessage(ChatDTO dto) {
        return chatMapper.insertReChatMessage(dto);
    }

    @Override
    public List<ChatDTO> selectReChatMessageById() {
        return chatMapper.selectReChatMessageById();
    }
}
