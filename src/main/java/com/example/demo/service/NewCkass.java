package com.example.demo.service;

import com.example.demo.model.table.ContentPool;
import com.example.demo.model.table.ContentPost;
import com.example.demo.model.table.VkGroup;
import com.example.demo.model.vk.VkNewsfeedParser;
import com.example.demo.repo.ContentpoolRepo;
import com.example.demo.repo.VkGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class NewCkass {
    @Autowired
    private VkGroupRepo vkGroupRepo;

    @Autowired
    private ContentpoolRepo contentpoolRepo;

    public List<ContentPool> getPool(){
        VkGroup vk = vkGroupRepo.findByName("Russ");


       List<ContentPool> cp = contentpoolRepo.findAllByGroup(vk);


        for (int i =0; i<cp.size(); i++){
            String answer1 = cp.get(i).getMedia();
            System.out.println(answer1);
        }
        return cp;

    }
    public void postPool(List<ContentPool> cp){


    }
    public void edInPool() {
        VkGroup vk = vkGroupRepo.findByName("Russ");
        String[] gh = vk.getOwner_id().split(",");
        ContentPost[] arr = VkNewsfeedParser.getWall(gh[0]);
        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != null){
                System.out.print(arr[i].getType()+" ");
        }
    }

}
