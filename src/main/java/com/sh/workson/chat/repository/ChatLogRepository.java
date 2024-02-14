package com.sh.workson.chat.repository;

import com.sh.workson.chat.entity.ChatLog;
import com.sh.workson.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

    @Query("from ChatLog where chatRoomId = :id")
    List<ChatLog> findLogByRoomId(Long id);
}
