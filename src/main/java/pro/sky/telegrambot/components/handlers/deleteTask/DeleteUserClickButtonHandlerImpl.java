package pro.sky.telegrambot.components.handlers.deleteTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.ReplyMessages;

/**
 * Обработчик нажатия на кнопку удалить
 */
@Component
public class DeleteUserClickButtonHandlerImpl implements InputMessageHandler {

    private final ReplyMessages replyMessages;

    public DeleteUserClickButtonHandlerImpl(ReplyMessages replyMessages) {
        this.replyMessages = replyMessages;
    }

    @Override
    public SendMessage startHandler(Message message) {
        return replyMessages.successfulCommand(message, Text.deleteIdText, Keyboards.KEYBOARD_CANCEL);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.DELETE_USER_CLICK_BUTTON;
    }
}
