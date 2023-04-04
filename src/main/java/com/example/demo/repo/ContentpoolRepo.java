package com.example.demo.repo;

import com.example.demo.model.table.ContentPool;
import com.example.demo.model.table.VkGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContentpoolRepo extends JpaRepository<ContentPool, Long> {
    public List<ContentPool> findAllByGroup(VkGroup vkGroup);


}
