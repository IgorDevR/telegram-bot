package pro.sky.telegrambot.components.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.ReplyMessages;
/**
 * Обработчик неизвестной команды команды, когда другие команды не определеныы.
 */
@Component
public class UnknownCommandHandlerImpl implements InputMessageHandler {
    private final ReplyMessages replyMessages;

    public UnknownCommandHandlerImpl(ReplyMessages replyMessages) {
        this.replyMessages = replyMessages;
    }

    @Override
    public SendMessage startHandler(Message message) {

     return  replyMessages.unknownAndErrorCommandMessage(message, Text.unknownReturnMainMenuText, Keyboards.KEYBOARD_MAIN_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.UNKNOWN_COMMAND;
    }
}
