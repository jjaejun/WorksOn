package com.sh.workson.chat.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.chat.dto.ChatLogCreateDto;
import com.sh.workson.chat.dto.ChatLogReturnDto;
import com.sh.workson.chat.dto.ChatRoomCreateDto;
import com.sh.workson.chat.entity.ChatLog;
import com.sh.workson.chat.entity.ChatRoom;
import com.sh.workson.chat.service.ChatService;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/chatMain.do")
    public void chatList(@AuthenticationPrincipal EmployeeDetails employeeDetails, Model model) {
        Long id = employeeDetails.getEmployee().getId();
//        log.debug("id = {}", id);
        List<ChatRoom> chatRooms = chatService.findAll();
//        log.debug("chatRooms = {}", chatRooms);

        List<ChatRoom> myChatRooms = new ArrayList<>();
        chatRooms.forEach(chatRoom -> {
//            log.debug("chatRoom = {}", chatRoom);
            chatRoom. getChatEmps().forEach(employee -> {
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
    public ResponseEntity<?> chatRoom(@RequestParam("chatRoomId") Long chatRoomId) {
        log.debug("chatRoomId = {}", chatRoomId);
        List<ChatLog> chatLogs = chatService.findLogByRoomId(chatRoomId);
        List<ChatLogReturnDto> chatLogReturnDtos = new ArrayList<>();
        chatLogs.forEach((chatLog -> {
            chatLogReturnDtos.add(ChatLogReturnDto.builder()
                            .empId(chatLog.getEmployee().getId())
                            .empName(chatLog.getEmployee().getName())
                            .content(chatLog.getContent())
                            .createdAt(chatLog.getCreatedAt())
                    .build());
        }));
        log.debug("chatLogReturnDtos = {}", chatLogReturnDtos);

        return new ResponseEntity<>(chatLogReturnDtos, HttpStatus.OK);
    }


    @MessageMapping("/chatMain/{chatRoomId}")
    @SendTo("/sub/chatMain/{chatRoomId}")
    public ChatLogReturnDto subMessage(@DestinationVariable Long chatRoomId, ChatLogCreateDto chatLogCreateDto) {
        log.debug("chatRoomId = {}", chatRoomId);
        log.debug("chatLogCreateDto = {}", chatLogCreateDto);
        chatService.createChatLog(chatLogCreateDto);
        String name = employeeService.findNameByEmpId(chatLogCreateDto.getEmployeeId());
        log.debug("name = {}", name);
        ChatLogReturnDto chatLogReturnDto = ChatLogReturnDto.builder()
                .empId(chatLogCreateDto.getEmployeeId())
                .empName(name)
                .content(chatLogCreateDto.getContent())
                .build();
        log.debug("chatLogReturnDto = {}", chatLogReturnDto);
        return chatLogReturnDto;
    }

    @GetMapping("/createChatRoom.do")
    public void createChatRoom() {}

    @PostMapping("/createChatRoom.do")
    public String createChatRoom(@AuthenticationPrincipal EmployeeDetails employeeDetails, ChatRoomCreateDto chatRoomCreateDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Set<Long> containMeEmpIds = chatRoomCreateDto.getEmpIds();
        containMeEmpIds.add(employeeDetails.getEmployee().getId());
        chatRoomCreateDto.setEmpIds(containMeEmpIds);
        log.debug("chatRoomCreateDto = {}", chatRoomCreateDto);
        chatService.createChatRoom(chatRoomCreateDto);
        redirectAttributes.addFlashAttribute("msg", "채팅방 생성 완료!!😎");
        return "redirect:chatMain.do";
    }

    @PostMapping("/deleteChatRoom.do")
    public String deleteChatRoom(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        log.debug("chatRoomId = {}", id);
        chatService.deleteChatRoom(id);
        redirectAttributes.addFlashAttribute("msg", "채팅방 나가기 완료!!😎");
        return "redirect:chatMain.do";
    }
}
