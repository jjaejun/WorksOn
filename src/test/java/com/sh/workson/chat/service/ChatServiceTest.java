package com.sh.workson.chat.service;

import com.sh.workson.chat.dto.ChatLogCreateDto;
import com.sh.workson.chat.dto.ChatRoomCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        longs.add(852L);
        longs.add(952L);

        ChatRoomCreateDto chatRoomCreateDto = ChatRoomCreateDto.builder()
                .name("테스트트트트")
                .empIds(longs)
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
                .employeeId(1L)
                .chatRoomId(51L)
                .build();
        System.out.println("chatLogCreateDto = " + chatLogCreateDto);

        // when
        chatService.createChatLog(chatLogCreateDto);

        // then
        assertThat(chatLogCreateDto).isNotNull();
    }
}