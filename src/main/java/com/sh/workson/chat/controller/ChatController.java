package com.sh.workson.chat.controller;

import com.sh.workson.chat.dto.ChatLogCreateDto;
import com.sh.workson.chat.entity.ChatRoom;
import com.sh.workson.chat.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chatList.do")
    public void chatList(Long id, Model model) {
        List<ChatRoom> chatRooms = chatService.findByEmpId(id);
        log.debug("chatRooms = {}", chatRooms);
        model.addAttribute("chatroom", chatRooms);
    }

//    @MessageMapping("/")
//    @SendTo("/")
    public String subMessage(String message) {
        log.debug("message = {}", message);
        return message;
    }

//    @MessageMapping("/")
//    @SendTo("/")
    public ChatLogCreateDto subMessage(@DestinationVariable Long chatRoomId, ChatLogCreateDto chatLogCreateDto) {
        log.debug("chatLogCreateDto = {}", chatLogCreateDto);
        return chatLogCreateDto;
    }
}
