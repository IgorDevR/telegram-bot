package pro.sky.telegrambot.components.handlers.deleteTask;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import pro.sky.telegrambot.BotState.BotState;


import org.springframework.stereotype.Component;
import pro.sky.telegrambot.components.handlers.CheckInputDataAndTransferAndSendToService;
import pro.sky.telegrambot.components.handlers.InputMessageHandler;
import pro.sky.telegrambot.exceptions.TaskByIdNotFoundException;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Keyboards;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.service.NotificationTaskService;
import pro.sky.telegrambot.ReplyMessages;

/**
 * Обработчик удаления по id, взаимодействует с сервисом. Состояние меняется после нажаия на кнопку удалиить и после статуса DELETE_USER_CLICK_BUTTON
 */
@Component
@Slf4j
public class DeleteWaitingTaskHandlerImpl extends CheckInputDataAndTransferAndSendToService implements InputMessageHandler {


    private final ReplyMessages replyMessages;
    private final NotificationTaskService notificationTaskService;

    public DeleteWaitingTaskHandlerImpl(ReplyMessages replyMessages, NotificationTaskService notificationTaskService) {

        this.replyMessages = replyMessages;
        this.notificationTaskService = notificationTaskService;
    }

    @Value("${regExr.deleteId}")
    String regEx;

    @Override
    public SendMessage startHandler(Message message) {

        if (this.checkTaskMessageByRegEx(message, regEx)) {
            try {
                interactionWithDb(message, regEx);
            } catch (TaskByIdNotFoundException e) {
                log.error(Text.errorDeleteByIdText);
                return replyMessages.unknownAndErrorCommandMessage(message, Text.errorDeleteByIdText, Keyboards.KEYBOARD_MAIN_MENU);
            }
            return replyMessages.successfulCommand(message, Text.taskSuccessfullyDeleteText, Keyboards.KEYBOARD_MAIN_MENU);
        }

        return replyMessages.unknownAndErrorCommandMessage(message, Text.unknownReturnMainMenuText, Keyboards.KEYBOARD_MAIN_MENU);
    }


    @Override
    public BotState getHandlerName() {
        return BotState.DELETE_WAITING_TASK;
    }

    @Override
    protected void interactionWithDb(Message message, String regEx) throws TaskByIdNotFoundException {
        long id = Long.parseLong(message.text());
        notificationTaskService.deleteTaskById(id);

    }
}
