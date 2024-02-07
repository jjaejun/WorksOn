package com.sh.workson.chat.service;

import com.sh.workson.chat.dto.ChatLogCreateDto;
import com.sh.workson.chat.dto.ChatRoomCreateDto;
import com.sh.workson.chat.entity.ChatRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class ChatServiceTest {

    @Autowired
    ChatService chatService;

    @DisplayName("채팅방 생성")
    @Test
    void test1() {
        // given
        Set<Long> longs = new LinkedHashSet<>();
        longs.add(1L);
        longs.add(51L);
        longs.add(101L);
        longs.add(151L);
        longs.add(201L);

        ChatRoomCreateDto chatRoomCreateDto = ChatRoomCreateDto.builder()
                .name("ESC")
                .empId(longs)
                .build();
        System.out.println("chatRoomCreateDto = " + chatRoomCreateDto);

        // when
        chatService.createChatRoom(chatRoomCreateDto);

        // then
        assertThat(chatRoomCreateDto).isNotNull();
    }

    @DisplayName("채팅 로그 저장")
    @Test
    void test2() {
        // given
        ChatLogCreateDto chatLogCreateDto = ChatLogCreateDto.builder()
                .content("너무 어려워요")
                .empId(1L)
                .chatRoomId(51L)
                .build();
        System.out.println("chatLogCreateDto = " + chatLogCreateDto);

        // when
        chatService.createChatLog(chatLogCreateDto);

        // then
        assertThat(chatLogCreateDto).isNotNull();
    }
}