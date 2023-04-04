package com.example.demo.repo;

import com.example.demo.model.table.Message;
import com.example.demo.model.table.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessRepo extends JpaRepository<Message, Long> {
}
