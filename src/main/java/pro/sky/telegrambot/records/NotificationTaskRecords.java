package pro.sky.telegrambot.records;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Класс используемы в боте, аналогичен классу в бд
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationTaskRecords {

    private Long id;
    private Long chatId;
    private String textMessage;
    private LocalDateTime dataTimeSendMessage;

}


