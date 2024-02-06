package com.sh.workson.chat.repository;

import com.sh.workson.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatRoom, Long> {

}
