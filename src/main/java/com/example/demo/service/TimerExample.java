package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
@Component
public class TimerExample {

    @Autowired
    NewCkass nk;
    public void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                nk.getPool();
            }
        };

        timer.schedule(task, 0, 1000 * 30 );
    }
}