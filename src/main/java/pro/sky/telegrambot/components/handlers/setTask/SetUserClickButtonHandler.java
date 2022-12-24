package pro.sky.telegrambot.components.handlers.setTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.ReplyMessages;

/**
 * Обработчик нажатия на кнопку добавить task
 */
@Component()
public class SetUserClickButtonHandler implements InputMessageHandler {
    private final ReplyMessages replyMessages;


    public SetUserClickButtonHandler(ReplyMessages replyMessages) {
        this.replyMessages = replyMessages;
    }

    @Override
    public SendMessage startHandler(Message message) {
        return replyMessages.successfulCommand(message, Text.replySetTaskText, Keyboards.KEYBOARD_CANCEL);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SET_USER_CLICK_BUTTON;
    }

}
