package com.example.demo.repo;

import com.example.demo.model.table.VkGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface VkGroupRepo extends JpaRepository<VkGroup, Long> {
    public VkGroup findByName(String name);

}
