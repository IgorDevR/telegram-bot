package pro.sky.telegrambot.components.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.BotState.BotState;
import pro.sky.telegrambot.keyboardAndButtonsAndText.Text;
import pro.sky.telegrambot.scheduler.SchedulerChecker;

import java.util.Collection;

/**
 * основной обработчик, выбирает кому дальше передать работу
 */

@Slf4j
@Component
public class MainHandler {

    private final Collection<InputMessageHandler> inputMessageHandlers;
    private final SchedulerChecker schedulerChecker;
    private BotState botState = BotState.UNKNOWN_COMMAND;

    public MainHandler(Collection<InputMessageHandler> inputMessageHandlers, SchedulerChecker schedulerChecker) {
        this.inputMessageHandlers = inputMessageHandlers;
        this.schedulerChecker = schedulerChecker;
    }

    /**  старт обработчика  */
    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;

        Message message = update.message();
        if (message != null && !message.text().isEmpty()) {
            log.info("HandleUpdate - New message from User: {}, chatId: {}, text: {}",
                    message.from().username(), message.from().id(), message.text());
            replyMessage = setBotStateAndSelectInputHandler(message);
        }
        return replyMessage;
    }
    /**  старт обработчика перегрузка */
    public Collection<SendMessage> handleUpdate() {
        return schedulerChecker.startCheckFormationTaskResult();
    }

    /** установка состояния бота  */
    private SendMessage setBotStateAndSelectInputHandler(Message message) {

        String inputMessage = message.text();

        switch (inputMessage) {

            case Text.startText:
                botState = BotState.START_USER_CLICK;
                break;
            case Text.setTaskText:
                botState = BotState.SET_USER_CLICK_BUTTON;
                break;
            case Text.showAllText:
                botState = BotState.SHOW_ALL_USER_CLICK_BUTTON;
                break;
            case Text.cancelText:
                botState = BotState.CANCEL_USER_CLICK_BUTTON;
                break;
            case Text.deleteText:
                botState = BotState.DELETE_USER_CLICK_BUTTON;
                break;
            default:
                botState = getAndChangeCurrentBotState(botState);
                break;
        }

        return selectInputHandler(message, botState);
    }

    /**
     * перебор обработчиков и выбор соответсвующего состоянию
     */
    private SendMessage selectInputHandler(Message message, BotState botState) {
        SendMessage replyMessage = null;

        for (InputMessageHandler botHandler : inputMessageHandlers) {

            if (botHandler.getHandlerName() == botState) {
                replyMessage = botHandler.startHandler(message);
                break;
            }
        }

        return replyMessage;
    }

    /**
     * переключение состояния бота, в зависимости от выбора поьзователя
     */
    public BotState getAndChangeCurrentBotState(BotState currentBotState) {

        switch (currentBotState) {
            case SET_USER_CLICK_BUTTON:
                return BotState.SET_WAITING_TASK;

            case DELETE_USER_CLICK_BUTTON:
                return BotState.DELETE_WAITING_TASK;

            case CANCEL_USER_CLICK_BUTTON:
                return BotState.UNKNOWN_COMMAND;
            default:
                return BotState.UNKNOWN_COMMAND;
        }
    }

}
