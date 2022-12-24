package pro.sky.telegrambot.components.handlers.setTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.components.handlers.CheckInputDataAndTransferAndSendToService;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.service.NotificationTaskService;
import pro.sky.telegrambot.ReplyMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

/**
 * Обработчик новой задачи, взаимодействует с сервисом. Состояние меняется после нажаия на кнопку добавить уведомление и после статуса SET_USER_CLICK_BUTTON
 */
@Component
@Slf4j
public class SetWaitingTaskImpl extends CheckInputDataAndTransferAndSendToService implements InputMessageHandler {
    ;
    private final ReplyMessages replyMessages;
    private final NotificationTaskService notificationTaskService;

    @Value("${regExr.setTask}")
    String regEx;

    public SetWaitingTaskImpl(ReplyMessages replyMessages, NotificationTaskService notificationTaskService) {

        this.replyMessages = replyMessages;
        this.notificationTaskService = notificationTaskService;
    }

    @Override
    public SendMessage startHandler(Message message) {

        if (this.checkTaskMessageByRegEx(message, regEx)) {
            try {
                interactionWithDb(message, regEx);
            } catch (RuntimeException e) {
                return replyMessages.unknownAndErrorCommandMessage(message, Text.errorSetTaskFormatText, Keyboards.KEYBOARD_MAIN_MENU);
            }
            return replyMessages.successfulCommand(message, Text.taskSetSuccessfulText, Keyboards.KEYBOARD_MAIN_MENU);
        }
        return replyMessages.unknownAndErrorCommandMessage(message, Text.unknownReturnMainMenuText, Keyboards.KEYBOARD_MAIN_MENU);
    }


    @Override
    public BotState getHandlerName() {
        return BotState.SET_WAITING_TASK;
    }

    @Override
    protected void interactionWithDb(Message message, String regEx) throws RuntimeException {

        Matcher matcher = this.getMather(message, regEx);

        String dateTime = matcher.group(1);
        String textTask = matcher.group(3);

        LocalDateTime dataTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        Long chatId = message.from().id();

        notificationTaskService.createTaskDb(chatId, textTask, dataTime);

    }
}
