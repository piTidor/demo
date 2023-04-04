package com.example.demo.repo;

import com.example.demo.model.table.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TgRepo extends JpaRepository<TelegramUser, Long> {
    public TelegramUser findByChatId(Long id);

}
