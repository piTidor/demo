package com.example.demo.model.table;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ContentPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "media", columnDefinition = "MEDIUMTEXT")
    private String media;

    private int date;

    @OneToMany(targetEntity = ContentPost.class, mappedBy = "group", fetch = FetchType.LAZY)
    private List<ContentPost> contentPosts = new ArrayList<>();
    @ManyToOne(targetEntity = VkGroup.class, fetch = FetchType.LAZY)
    private VkGroup group;

    public ContentPool() {
    }

    public ContentPool(String media, int date, VkGroup group) {
        this.media = media;
        this.date = date;
        this.group = group;
    }

    public ContentPool(int date, VkGroup group) {
        this.date = date;
        this.group = group;
    }

    @Override
    public String toString() {
        return "ContentPool{" +
                "id=" + id +
                ", media='" + media + '\'' +
                ", date=" + date +
                ", contentPosts=" + contentPosts +
                ", group=" + group +
                '}';
    }
}
