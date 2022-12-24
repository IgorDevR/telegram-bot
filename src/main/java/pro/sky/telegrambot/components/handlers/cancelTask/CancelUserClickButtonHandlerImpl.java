package pro.sky.telegrambot.components.handlers.cancelTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.ReplyMessages;
/**
 * Обработчик нажатия на кнопку отмена
 */
@Component
public class CancelUserClickButtonHandlerImpl implements InputMessageHandler {


    private final ReplyMessages replyMessages;

    public CancelUserClickButtonHandlerImpl( ReplyMessages replyMessages) {
        this.replyMessages = replyMessages;
    }

    @Override
    public SendMessage startHandler(Message message) {
        return replyMessages.successfulCommand(message, Text.cancelReturnMainMenuText, Keyboards.KEYBOARD_MAIN_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CANCEL_USER_CLICK_BUTTON;
    }

}
