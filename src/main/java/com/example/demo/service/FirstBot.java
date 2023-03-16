package com.example.demo.service;

import com.example.demo.apiService.FirstApi;
import com.example.demo.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component
public class FirstBot extends TelegramLongPollingBot {

    final BotConfig config ;

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
        String help = "Список команд: \n "  + "/start  - стартовая страница" + "\n" + "О нас - информация... угадай о ком.. \n" + "Шутка на английском - пришлёт тебе анекдот" ;
        String info = "Телеграм бот для показа погоды и выдачи анекдотов";
//        System.out.println(update.getMessage().getChat().getDescription());

        if (update.hasMessage() && update.getMessage().hasText()){
            String messageT = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String mail = update.getMessage().getText() + "\n" + update.getMessage().getChat().getFirstName();
            switch (messageT){
                case  "/start":
                    startCommandReceived(chatId , update.getMessage().getChat().getFirstName());
                    break;
                case "/help":
                    sendMessage(chatId, help);
                    break;
                case "О нас":
                    sendMessage(chatId, info);
                    break;
                case"Шутка на английском":
                    sendMessage(chatId, FirstApi.getAnekdot());
                    break;
                case "Погода сегодня":
                    sendMessage(chatId, FirstApi.getPogoda());
                    break;
                default: sendMessage(chatId, "я не понимаю, что ты пишешь, попробуй ввести команду /help ");
                System.out.println( String.valueOf(update.getMessage().getChatId()) + ":" + update.getMessage().getText());
                break;

            }
        } else {
            long chatId = update.getMessage().getChatId();
            sendMessage(chatId, "только буквы");
        }

    }
    private void startCommandReceived(long chatId, String name){
        String answer = "Привет," + name + "!"  ;
        sendMessage(chatId, answer);
    }
    private  void sendMessage(long chatId, String textToSend ){
        SendMessage message = new SendMessage( String.valueOf(chatId),textToSend);
        message.setReplyMarkup(initKeyboard());
//        SendMessage message1 = new SendMessage("459811485", mail);
        try {
            execute( message);
        } catch (TelegramApiException e) {

        }
//        try {
//            execute( message1);
//        } catch (TelegramApiException e) {
//
//        }
    }
    public static ReplyKeyboardMarkup initKeyboard()
    {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardRow1);
        //Добавляем одну кнопку с текстом "Просвяти" наш ряд
        keyboardRow.add(new KeyboardButton("Шутка на английском"));
        keyboardRow.add(new KeyboardButton("Погода сегодня"));
        keyboardRow1.add(new KeyboardButton("О нас"));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }



}
