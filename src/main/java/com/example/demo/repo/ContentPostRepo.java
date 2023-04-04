package com.example.demo.repo;

import com.example.demo.model.table.ContentPool;
import com.example.demo.model.table.ContentPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentPostRepo  extends JpaRepository<ContentPost, Long> {
}
