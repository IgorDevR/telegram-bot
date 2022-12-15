package pro.sky.telegrambot.components.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telegrambot.BotState.BotState;
/**  обработка входящих сообшений  */
public interface InputMessageHandler {

    /**  запуск обработчика  */
    SendMessage startHandler(Message message);
    /**  получить имя равное состоянию, в последующем для выбора нужного обработчика в цикле  */
    BotState getHandlerName();


}
