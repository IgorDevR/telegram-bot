package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
/**
 * сервис для формирования сообщений
 */
@Service
public class ReplyMessagesService {
    public SendMessage unknownAndErrorCommandMessage(Message message, String textReplyMessage, ReplyKeyboardMarkup keyboardMarkup) {
        long chatId = message.from().id();
        return new SendMessage(chatId, textReplyMessage).replyMarkup(keyboardMarkup);
    }

    public SendMessage successfulCommand(Message message, String textReplyMessage, ReplyKeyboardMarkup keyboardMarkup) {
        long chatId = message.from().id();
        return new SendMessage(chatId, textReplyMessage).replyMarkup(keyboardMarkup);

    }



}
