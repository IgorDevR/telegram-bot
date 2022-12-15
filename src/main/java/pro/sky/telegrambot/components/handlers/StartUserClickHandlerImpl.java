package pro.sky.telegrambot.components.handlers;//package pro.sky.telegrambot.components.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.service.ReplyMessagesService;
/**
 * Обработчик при запуске бота или команды /start
 */
@Component
public class StartUserClickHandlerImpl implements InputMessageHandler {
    private final ReplyMessagesService replyMessagesService;

    public StartUserClickHandlerImpl(ReplyMessagesService replyMessagesService) {
        this.replyMessagesService = replyMessagesService;
    }

    @Override
    public SendMessage startHandler(Message message) {
        return replyMessagesService.successfulCommand(message, Text.startGreetingsText, Keyboards.KEYBOARD_MAIN_MENU).replyMarkup(Keyboards.KEYBOARD_MAIN_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START_USER_CLICK;
    }


}
