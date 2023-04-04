package com.example.demo.model.table;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import java.util.Date;
@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mesText", columnDefinition = "MEDIUMTEXT")
    private String mesText;
    private boolean forBot;
    private Date date;
    @ManyToOne(targetEntity = TelegramUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "telegramUser")
    private TelegramUser telegramUser;


    public Message(Long id, String mesText, boolean forBot, Date date, TelegramUser telegramUser) {
        this.id = id;
        this.mesText = mesText;
        this.forBot = forBot;
        this.date = date;
        this.telegramUser = telegramUser;
    }

    public Message(String mesText, boolean forBot, Date date, TelegramUser telegramUser) {
        this.mesText = mesText;
        this.forBot = forBot;
        this.date = date;
        this.telegramUser = telegramUser;
    }

    public Message() {
    }
}
