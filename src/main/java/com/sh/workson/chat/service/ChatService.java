package com.sh.workson.chat.service;

import com.sh.workson.chat.dto.ChatLogCreateDto;
import com.sh.workson.chat.dto.ChatRoomCreateDto;
import com.sh.workson.chat.entity.ChatLog;
import com.sh.workson.chat.entity.ChatRoom;
import com.sh.workson.chat.repository.ChatLogRepository;
import com.sh.workson.chat.repository.ChatRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatLogRepository chatLogRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper modelMapperSet;

    public void createChatRoom(ChatRoomCreateDto chatRoomCreateDto) {
        chatRoomRepository.save(convertToChatRoom(chatRoomCreateDto));
    }

    private ChatRoom convertToChatRoom(ChatRoomCreateDto chatRoomCreateDto) {
        return modelMapperSet.map(chatRoomCreateDto, ChatRoom.class);
    }

    public void createChatLog(ChatLogCreateDto chatLogCreateDto) {
        chatLogRepository.save(convertToChatLog(chatLogCreateDto));
    }

    private ChatLog convertToChatLog(ChatLogCreateDto chatLogCreateDto) {
        return modelMapper.map(chatLogCreateDto, ChatLog.class);
    }
}
