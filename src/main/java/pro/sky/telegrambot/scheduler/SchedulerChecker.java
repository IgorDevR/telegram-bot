package pro.sky.telegrambot.scheduler;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.records.NotificationTaskRecords;
import pro.sky.telegrambot.service.ReplyMessagesService;
import pro.sky.telegrambot.service.ScheduledTaskService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * проверка наличия уведомлейний, вызывается по таймеру из TelegramBotUpdatesListener
 */
@Component
public class SchedulerChecker {


    private final ReplyMessagesService replyMessagesService;
    private final ScheduledTaskService scheduledTaskService;


    public SchedulerChecker(ReplyMessagesService replyMessagesService, ScheduledTaskService scheduledTaskService) {        
        this.replyMessagesService = replyMessagesService;
        this.scheduledTaskService = scheduledTaskService;
    }
    /**  Запуск проверки на наличие уведомлений в заданное время и формирование этих уведомлейни в список сообщений для ответа   */

    public Collection<SendMessage> startCheckFormationTaskResult() {

        Collection<NotificationTaskRecords> notificationTaskRecords
                = scheduledTaskService.findAllByDataTimeSendMessage(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        Collection<SendMessage> sendMessages
                = notificationTaskRecords.stream().map(ntr -> new SendMessage(ntr.getChatId(), ntr.getTextMessage()))
                .collect(Collectors.toList());

        return sendMessages;
    }

}
