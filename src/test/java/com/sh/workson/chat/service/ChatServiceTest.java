package com.sh.workson.chat.service;

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

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @DisplayName("채팅방 생성")
    @Test
    void test1() {
        // given
        Set<Long> longs = new LinkedHashSet<>();
        longs.add((long) 1);
        longs.add((long) 51);
        longs.add((long) 101);
        longs.add((long) 151);
        longs.add((long) 201);

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
}