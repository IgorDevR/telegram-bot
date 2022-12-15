package pro.sky.telegrambot.components.handlers.deleteTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.service.ReplyMessagesService;

/**
 * Обработчик нажатия на кнопку удалить
 */
@Component
public class DeleteUserClickButtonHandlerImpl implements InputMessageHandler {

    private final ReplyMessagesService replyMessagesService;

    public DeleteUserClickButtonHandlerImpl(ReplyMessagesService replyMessagesService) {
        this.replyMessagesService = replyMessagesService;
    }

    @Override
    public SendMessage startHandler(Message message) {
        return replyMessagesService.successfulCommand(message, Text.deleteIdText, Keyboards.KEYBOARD_CANCEL);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.DELETE_USER_CLICK_BUTTON;
    }
}
