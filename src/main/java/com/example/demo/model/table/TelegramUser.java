package com.example.demo.model.table;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long id;
@Column(name = "chatId")
    private Long chatId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "startFrom")
    private String startFrom;
    @Column(name = "vk_token", columnDefinition = "MEDIUMTEXT")
    private String VKtoken;
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "telegramUser")

    private List<Message> message = new ArrayList<>();

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "telegramUser")

    private List<VkGroup> vkGroups = new ArrayList<>();


    public TelegramUser() {
    }

    public TelegramUser(Long chatId, String firstName, String lastName) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public TelegramUser(Long id, Long chatId, String firstName, String lastName, List<Message> message) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
    }

    public TelegramUser(Long chatId, String firstName, String lastName, List<Message> message) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
    }
}
