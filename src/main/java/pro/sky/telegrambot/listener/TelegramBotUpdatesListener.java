package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.components.handlers.MainHandler;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.service.ScheduledTaskService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final MainHandler mainHandler;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, MainHandler mainHandler) {
        this.telegramBot = telegramBot;
        this.mainHandler = mainHandler;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            logger.info("New message from User:{}, chatId: {},  with text: {}",
                    message.from().username(), message.from().id(), message.text());
//идем в основной обрабоччик
            SendMessage replyMessage = mainHandler.handleUpdate(update);
//отправляем готовое сообщение
            sendReplyMessage(replyMessage);

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendReplyMessage(SendMessage replyMessage) {

        SendResponse response = telegramBot.execute(replyMessage);

        if (!response.isOk()) {
            logger.error("Error sending response message, error code= " + response.errorCode()
                    + " descriptions= " + response.description());
        }
        logger.info("Response message sent successfully");
    }

    @Scheduled(cron = "${cron.scheduler}")
    private void startMonitoringScheduledTasks() {
        mainHandler.handleUpdate().stream().forEach(sendMessage -> sendReplyMessage(sendMessage));
    }


}
