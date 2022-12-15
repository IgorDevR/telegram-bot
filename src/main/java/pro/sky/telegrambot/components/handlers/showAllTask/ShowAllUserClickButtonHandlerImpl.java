package pro.sky.telegrambot.components.handlers.showAllTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.records.NotificationTaskRecords;
import pro.sky.telegrambot.service.NotificationTaskService;
import pro.sky.telegrambot.service.ReplyMessagesService;

import java.util.Collection;
/**
 * Обработчик нажатия на кнопку показать все таски
 */
@Component
public class ShowAllUserClickButtonHandlerImpl implements InputMessageHandler {

    private final NotificationTaskService notificationTaskService;
    private final ReplyMessagesService replyMessagesService;

    public ShowAllUserClickButtonHandlerImpl(NotificationTaskService notificationTaskService, ReplyMessagesService replyMessagesService) {
        this.notificationTaskService = notificationTaskService;
        this.replyMessagesService = replyMessagesService;
    }


    @Override
    public SendMessage startHandler(Message message) {
        Collection<NotificationTaskRecords> ntr = notificationTaskService.findAllTask();

        StringBuilder sb = new StringBuilder();
        ntr.stream()
                .forEachOrdered(n -> sb
                        .append(n.getTextMessage()).append("\n"));
        return replyMessagesService.successfulCommand(message, sb.toString(), Keyboards.KEYBOARD_MAIN_MENU);

    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_ALL_USER_CLICK_BUTTON;
    }

}
