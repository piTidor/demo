package com.example.demo.model.table;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ContentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int media_id;
    private int owner_id;
    private int date;
    @ManyToOne(targetEntity = ContentPool.class, fetch = FetchType.LAZY)
    private ContentPool group;

}
