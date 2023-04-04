package com.example.demo.service;

import com.example.demo.apiService.FirstApi;
import com.example.demo.config.BotConfig;
import com.example.demo.model.table.ContentPool;
import com.example.demo.model.table.Message;
import com.example.demo.model.table.TelegramUser;

import com.example.demo.model.table.VkGroup;
import com.example.demo.model.vk.VkNewsfeedParser;
import com.example.demo.repo.ContentpoolRepo;
import com.example.demo.repo.MessRepo;
import com.example.demo.repo.TgRepo;
import com.example.demo.repo.VkGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component

public class FirstBot extends TelegramLongPollingBot {

    final BotConfig config ;


    @Autowired
    private MessRepo messRepo;

    @Autowired
    private TgRepo tgRepo;

    @Autowired
    private VkGroupRepo vkGroupRepo;

    @Autowired
    private ContentpoolRepo contentpoolRepo;




    public FirstBot (BotConfig config){
        this.config = config;
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken() {
        return config.getToken();
    }



    @Override
    public void onUpdateReceived(Update update) {
        String help = "Привет" + update.getMessage().getChat().getFirstName() + ", введи старт и получи интерактивное меню в поллев ввода" ;
        String info = "Телеграм бот для показа погоды и выдачи анекдотов";

        String count = "15";
//        System.out.println(update.getMessage().getChat().getDescription());
        TelegramUser user1 = new TelegramUser(update.getMessage().getChatId(),update.getMessage().getChat().getFirstName(),update.getMessage().getChat().getLastName());
        TelegramUser user =  tgRepo.findByChatId(update.getMessage().getChatId());
        if (update.hasMessage() && update.getMessage().hasText()){

            if ( user  == null ){
                tgRepo.save(user1);
                user = user1;
            }
            String s = user.getVKtoken();
            String messageT = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            Message mail = new Message(update.getMessage().getText(), false, new Date(), user );
            messRepo.save(mail);
            Long id = update.getMessage().getChatId();
            if (messageT.split("=")[0].equals("https://oauth.vk.com/blank.html#code") ){
                String code = update.getMessage().getText().split("https://oauth.vk.com/blank.html#code=")[1];
                user.setVKtoken(FirstApi.getVkToken(code));
                tgRepo.save(user);
                sendMessage(chatId, "Новости Вк привязаны успешно !!!", user);}
            else if (messageT.split("=")[0].equals("owner_id")) {
                String code = update.getMessage().getText().split("owner_id=")[1];
                VkGroup vkGroup = new VkGroup(code, new Date(), user, "Russ");
                vkGroupRepo.save(vkGroup);


            } else {
                switch (messageT) {
                    case "/start":
                    case "Назад":
                    case "/help":
                        sendMessage(chatId, help, user);
                        break;
                    case "Погода сегодня":
                        sendMessage(chatId, FirstApi.getPogoda(id), user);
                        break;
                    case "VkPost":
//                    String g = "{\n" +
//
//                            "\"protocol\":\"HTTP\" \n" +
//                            "\"URL\":\"https://sun3-21.userapi.com/impg/m1SW7iHXrvKALpBrD1kg0_rcJ7olEpXVst6rkQ/LJt1yIi87tI.jpg?size=613x1080&quality=96&sign=5da309e5255a7054b1d813260a32bd36&c_uniq_tag=C127bICq6a4kUPHnH2NTqKL_JaWJ1uKLjxaCWaTvfTg&type=album\" \n" +
//
//                            "}" ;
//                    String sx = "hello";
                    System.out.println(VkNewsfeedParser.postRuss());

                    break;

                    case "Vknews":
                        if (user.getVKtoken() == null) {
                            sendMessage(chatId, "Пожалуйста перейдите по ссылке, разрешите доступ к стене и друзьям, и отправте полученную ссылку без дополнительных символов" +
                                    "\n https://oauth.vk.com/oauth/authorize?client_id=51589630&redirect_uri=https://oauth.vk.com/blank.html&display=page&scope=friends,wall,offline&response_type=code&v=5.77", user);
                            break;
                        }
                        String[] answer = VkNewsfeedParser.pars(s, count, user.getStartFrom());
                        int lg = answer.length;
                        if (lg != 3) {
                            user.setStartFrom(answer[lg - 1]);
                            tgRepo.save(user);
                            sendPhotoLong(chatId, answer[lg - 2], answer);
                        } else {
                            user.setStartFrom(answer[2]);
                            tgRepo.save(user);
                            sendPhoto(chatId, answer[1], answer);
                        }


                        break;
                    case "Vk":
                        VkGroup rys = vkGroupRepo.findByName("Russ");

//                        String[] sav =  VkNewsfeedParser.getWall(rys.getOwner_id());
//                        for (int i =0; i< sav.length; i++){
//                            ContentPool cp = new ContentPool(sav[i], new Date(), rys);
//                            System.out.println(cp.getMedia());
//                            contentpoolRepo.save(cp);
//                        }

                        break;
                    case "Сколько деняг у Андрея на бинансе?":
                        sendMessage(chatId, FirstApi.getBalance(), user);
                        break;
                    case "Валюта":
                        sendValut(chatId, "Выберете валютную пару");

                        break;

                    case "USDT/RUB":
                        sendMessage(chatId, FirstApi.getBTCUSDT("USDTRUB"), user);
                        break;
                    case "BTC/USDT":
                        sendMessage(chatId, FirstApi.getBTCUSDT("BTCUSDT"), user);
                        break;
                    case "BTC/RUB":
                        sendMessage(chatId, FirstApi.getBTCUSDT("BTCRUB"), user);
                        break;
                    case "DOGE/RUB":
                        sendMessage(chatId, FirstApi.getBTCUSDT("DOGERUB"), user);
                        break;
                    default:
                        sendMessage(chatId, "я не понимаю, что ты пишешь, попробуй ввести команду /help ", user);
                        System.out.println(String.valueOf(update.getMessage().getChatId()) + ":" + update.getMessage().getText() + update.getMessage().getChat().getFirstName());
                        break;

                }
            }
        } else {
//            String mail = update.getMessage().getText() + "\n" + update.getMessage().getChat().getFirstName();
            long chatId = update.getMessage().getChatId();
            sendMessage(chatId, "только буквы", user);
        }

    }

    private  void sendMessage(long chatId, String textToSend, TelegramUser user){
        SendMessage message = new SendMessage( String.valueOf(chatId),textToSend);
        Message mes  = new Message(textToSend, true, new Date(), user);
        messRepo.save(mes);
        message.setReplyMarkup(KeybordTG.initKeyboard());


        try {
            execute( message);
        } catch (TelegramApiException e) {

        }

    }
    private  void sendPhotoLong(long chatId, String textToSend, String[] url){

        List<InputMedia> lm = new ArrayList<>();
    for (int i = 0; i < url.length - 2; i++) {
        InputFile file = new InputFile(url[i]);
//        SendPhoto photo = new SendPhoto(String.valueOf(chatId), file);
        InputMedia im = new InputMediaPhoto(url[i]);
        lm.add(im);
//        photo.setCaption(textToSend);
        System.out.println(url[i]);
        System.out.println("photo");
    }



        SendMediaGroup sm = new SendMediaGroup(String.valueOf(chatId), lm);

//        sm.setReplyMarkup(initKeyboard());

        try {
            execute(sm);
        } catch (TelegramApiException e) {

        }
    }

    private  void sendPhoto(long chatId, String textToSend, String[] url){

        InputFile file = new InputFile(url[0]);
        SendPhoto photo = new SendPhoto(String.valueOf(chatId), file);
            InputMedia im = new InputMediaPhoto(url[0]);
        photo.setCaption(textToSend);
            System.out.println(url[0]);
            System.out.println("photo");
        photo.setReplyMarkup(KeybordTG.initKeyboard());
        try {
            execute(photo);
        } catch (TelegramApiException e) {

        }
    }


    private  void sendVideo(long chatId, String textToSend, String url){


        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(chatId);
        sendVideo.setVideo(new InputFile(url));
        sendVideo.setCaption(textToSend);
        System.out.println("video");
        sendVideo.setReplyMarkup(KeybordTG.initKeyboard());



        try {
            execute( sendVideo );
        } catch (TelegramApiException e) {

        }

    }
    private  void sendValut(long chatId, String textToSend ){
        SendMessage message = new SendMessage( String.valueOf(chatId),textToSend);
        message.setReplyMarkup(KeybordTG.menu());

        try {
            execute( message);
        } catch (TelegramApiException e) {

        }

    }




}
