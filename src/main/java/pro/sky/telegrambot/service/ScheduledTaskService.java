package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.components.RecordMapper;
import pro.sky.telegrambot.records.NotificationTaskRecords;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
/**
 * сервис для работы с бд по таймеру
 */
@Service
public class ScheduledTaskService {

    private final NotificationTaskRepository notificationTaskRepository;
    private final RecordMapper recordMapper;

    public ScheduledTaskService(NotificationTaskRepository notificationTaskRepository, RecordMapper recordMapper) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.recordMapper = recordMapper;
    }

    public Collection<NotificationTaskRecords> findAllByDataTimeSendMessage(LocalDateTime localDateTime) {

        return notificationTaskRepository.findAllByDataTimeSendMessage(localDateTime)
                .stream()
                .map(recordMapper::toRecords)
                .collect(Collectors.toList());
    }

}
