package com.sh.workson.chat.service;

import com.sh.workson.chat.dto.ChatLogCreateDto;
import com.sh.workson.chat.dto.ChatRoomCreateDto;
import com.sh.workson.chat.entity.ChatLog;
import com.sh.workson.chat.entity.ChatRoom;
import com.sh.workson.chat.repository.ChatLogRepository;
import com.sh.workson.chat.repository.ChatRoomRepository;
import com.sh.workson.employee.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatLogRepository chatLogRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper modelMapperStrict;


    public void createChatRoom(ChatRoomCreateDto chatRoomCreateDto) {
        chatRoomRepository.save(convertToChatRoom(chatRoomCreateDto));
    }

    private ChatRoom convertToChatRoom(ChatRoomCreateDto chatRoomCreateDto) {
        ChatRoom chatRoom = modelMapperStrict.map(chatRoomCreateDto, ChatRoom.class);
        Set<Employee> chatEmps = chatRoomCreateDto.getEmpIds().stream()
                .map(id -> Employee.builder().id(id).build())
                .collect(Collectors.toSet());
        chatRoom.setChatEmps(chatEmps);
        return chatRoom;
    }

    public void createChatLog(ChatLogCreateDto chatLogCreateDto) {
        chatLogRepository.save(convertToChatLog(chatLogCreateDto));
    }

    private ChatLog convertToChatLog(ChatLogCreateDto chatLogCreateDto) {
        return modelMapper.map(chatLogCreateDto, ChatLog.class);
    }
}
