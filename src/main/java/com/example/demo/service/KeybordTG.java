package com.example.demo.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class KeybordTG {
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
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        //Добавляем одну кнопку с текстом "Просвяти" наш ряд
        keyboardRow.add(new KeyboardButton("Шутка на английском"));
        keyboardRow.add(new KeyboardButton("Погода сегодня"));
        keyboardRow1.add(new KeyboardButton("Валюта"));
        keyboardRow1.add(new KeyboardButton("Vknews"));

        keyboardRow2.add(new KeyboardButton("Сколько деняг у Андрея на бинансе?"));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
    public static ReplyKeyboardMarkup menu()
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
        keyboardRow.add(new KeyboardButton("USDT/RUB"));
        keyboardRow.add(new KeyboardButton("BTC/USDT"));
        keyboardRow.add(new KeyboardButton("BTC/RUB"));
        keyboardRow.add(new KeyboardButton("DOGE/RUB"));
        keyboardRow1.add(new KeyboardButton("Назад"));

        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}
