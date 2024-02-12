package com.sh.workson.chat.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.chat.dto.ChatLogCreateDto;
import com.sh.workson.chat.entity.ChatRoom;
import com.sh.workson.chat.service.ChatService;
import com.sh.workson.employee.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chatMain.do")
    public void chatList(@AuthenticationPrincipal EmployeeDetails employeeDetails, Model model) {
        Long id = employeeDetails.getEmployee().getId();
//        log.debug("id = {}", id);
        List<ChatRoom> chatRooms = chatService.findAll();
//        log.debug("chatRooms = {}", chatRooms);

        List<ChatRoom> myChatRooms = new ArrayList<>();
        chatRooms.forEach(chatRoom -> {
//            log.debug("chatRoom = {}", chatRoom);
            chatRoom.getChatEmps().forEach(employee -> {
//                log.debug("employee = {}", employee);
                if (employee.getId().equals(id)) {
                    myChatRooms.add(chatRoom);
                }
            });
        });
//        log.debug("myChatRooms = {}", myChatRooms);
        model.addAttribute("myChatRooms", myChatRooms);
    }

    @GetMapping("/chatRoom.do")
    public void chatRoom(@RequestParam("id") Long id , Model model) {
//        log.debug("id = {}", id);
        model.addAttribute("chatRoomId", id);
    }


    @MessageMapping("/chatRoom/{chatRoomId}")
    @SendTo("/sub/chatRoom/{chatRoomId}")
    public ChatLogCreateDto subMessage(@DestinationVariable String chatRoomId, ChatLogCreateDto chatLogCreateDto) {

        log.debug("chatLogCreateDto = {}", chatLogCreateDto);
//        chatService.createChatLog(chatLogCreateDto);
        return chatLogCreateDto;
    }
}
