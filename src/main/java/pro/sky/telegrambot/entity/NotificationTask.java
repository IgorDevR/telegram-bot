package pro.sky.telegrambot.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
 * сущность для работы с бд
 */
@Entity
@Table(name = "course_telegram_bot")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationTask {

    public NotificationTask() {
    }


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "text_message")
    private String textMessage;

    @Column(name = "data_time_send_message")
    private LocalDateTime dataTimeSendMessage;


}
