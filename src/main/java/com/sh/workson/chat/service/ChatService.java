package com.sh.workson.chat.service;

import com.sh.workson.chat.dto.ChatRoomCreateDto;
import com.sh.workson.chat.entity.ChatRoom;
import com.sh.workson.chat.repository.ChatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void createChatRoom(ChatRoomCreateDto chatRoomCreateDto) {
        chatRepository.save(convertToChatRoom(chatRoomCreateDto));
    }

    private ChatRoom convertToChatRoom(ChatRoomCreateDto chatRoomCreateDto) {
        return null;
    }
}
