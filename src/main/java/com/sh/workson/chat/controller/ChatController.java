package com.sh.workson.chat.controller;

import com.sh.workson.chat.dto.ChatLogCreateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/")
    @SendTo("/")
    public String subMessage(String message) {
        log.debug("message = {}", message);
        return message;
    }

    @MessageMapping("/")
    @SendTo("/")
    public ChatLogCreateDto subMessage(@DestinationVariable Long chatRoomId, ChatLogCreateDto chatLogCreateDto) {
        log.debug("chatLogCreateDto = {}", chatLogCreateDto);
        return chatLogCreateDto;
    }
}
