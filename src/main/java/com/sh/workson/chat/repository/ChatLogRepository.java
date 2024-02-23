package com.sh.workson.chat.repository;

import com.sh.workson.chat.entity.ChatLog;
import com.sh.workson.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

    @Query("from ChatLog where chatRoomId = :id order by createdAt")
    List<ChatLog> findLogByRoomId(@Param("id")Long id);
}
