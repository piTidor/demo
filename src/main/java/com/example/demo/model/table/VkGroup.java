package com.example.demo.model.table;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "group_cont")
@Data
public class VkGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id", columnDefinition = "MEDIUMTEXT")
    private String owner_id;
    @Column(name = "date")
    private Date date;
    @Column(name = "name")
    private String name;
    @Column(name = "setings")
    private String setings;
    @OneToMany(targetEntity = ContentPool.class, mappedBy = "group", fetch = FetchType.LAZY)
    private List<ContentPool> contentPools = new ArrayList<>();

    @ManyToOne(targetEntity = TelegramUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "telegramUser")
    private TelegramUser telegramUser;

    public VkGroup(String owner_id, Date date, List<ContentPool> contentPools, TelegramUser telegramUser) {
        this.owner_id = owner_id;
        this.date = date;
        this.contentPools = contentPools;
        this.telegramUser = telegramUser;
    }
    public VkGroup(String owner_id, Date date, TelegramUser telegramUser, String name) {
        this.owner_id = owner_id;
        this.date = date;
        this.name = name;
        this.telegramUser = telegramUser;
    }

    public VkGroup() {

    }

    @Override
    public String toString() {
        return "VkGroup{" +
                "id=" + id +
                ", owner_id='" + owner_id + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", setings='" + setings + '\'' +
                ", contentPools=" + contentPools +
                ", telegramUser=" + telegramUser +
                '}';
    }
}
