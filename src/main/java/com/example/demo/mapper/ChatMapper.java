package com.example.demo.mapper;

import com.example.demo.dto.ChatDTO;

import java.util.List;

public interface ChatMapper {

    public int insertReChatMessage(ChatDTO dto);

    public List<ChatDTO> selectReChatMessageById();

}
