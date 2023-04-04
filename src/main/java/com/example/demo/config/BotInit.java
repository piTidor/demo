package com.example.demo.config;

import com.example.demo.service.FirstBot;
import com.example.demo.service.NewCkass;
import com.example.demo.service.TimerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class BotInit {
    @Autowired
    NewCkass nk;
    @Autowired
    FirstBot bot;


    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
//
//        Timer timer = new Timer();
//
//        TimerTask task = new TimerTask() {
//            public void run() {
                nk.getPool();
                nk.edInPool();
//            }
//        };
//
//        timer.schedule(task, 0, 1000 * 30 );



//
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            System.out.println("Еблан хуйню пишет");
        }
    }
}
