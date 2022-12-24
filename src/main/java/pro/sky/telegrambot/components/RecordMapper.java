package pro.sky.telegrambot.components;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.records.NotificationTaskRecords;
/**
 * Перегов из сущности в класс и обратное
 */
@Component
public class RecordMapper {

    public NotificationTaskRecords toRecords(NotificationTask notificationTask) {
        NotificationTaskRecords notificationTaskRecords = new NotificationTaskRecords();
        notificationTaskRecords.setId(notificationTask.getId());
        notificationTaskRecords.setChatId(notificationTask.getChatId());
        notificationTaskRecords.setTextMessage(formResponseTextMessage(notificationTask));
        notificationTaskRecords.setDataTimeSendMessage(notificationTask.getDataTimeSendMessage());
        return notificationTaskRecords;
    }

    public NotificationTask toEntity(NotificationTaskRecords notificationTaskRecords) {
        NotificationTask notificationTask = new NotificationTask();
        notificationTask.setChatId(notificationTaskRecords.getChatId());
        notificationTask.setTextMessage(notificationTaskRecords.getTextMessage());
        notificationTask.setDataTimeSendMessage(notificationTaskRecords.getDataTimeSendMessage());
        return notificationTask;
    }
    /**  формироаание нужного собщения, которое будет выводится в уведомлении по таймеру  */
    private String formResponseTextMessage(NotificationTask notificationTask) {

        return new StringBuilder()
                .append("Task Id: ")
                .append(notificationTask.getId())
                .append("\n")
                .append("Уведомление: ")
                .append(notificationTask.getTextMessage())
                .append("\n")
                .append("Установленое время: ")
                .append(notificationTask.getDataTimeSendMessage())
                .append("\n")
                .append("-----------------------------------")
                .toString();
    }

}
