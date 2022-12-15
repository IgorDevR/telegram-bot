package pro.sky.telegrambot.components.handlers.setTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.service.ReplyMessagesService;

/**
 * Обработчик нажатия на кнопку добавить task
 */
@Component()
public class SetUserClickButtonHandler implements InputMessageHandler {
    private final ReplyMessagesService replyMessagesService;


    public SetUserClickButtonHandler(ReplyMessagesService replyMessagesService) {
        this.replyMessagesService = replyMessagesService;
    }

    @Override
    public SendMessage startHandler(Message message) {
        return replyMessagesService.successfulCommand(message, Text.replySetTaskText, Keyboards.KEYBOARD_CANCEL);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SET_USER_CLICK_BUTTON;
    }

}
