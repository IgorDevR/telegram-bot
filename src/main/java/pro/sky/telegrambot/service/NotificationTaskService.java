package pro.sky.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.components.RecordMapper;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.exceptions.TaskByIdNotFoundException;
import pro.sky.telegrambot.records.NotificationTaskRecords;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * сервис для чтения и записи с бд
 */
@Service
@Slf4j
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;
    private final RecordMapper recordMapper;

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository, RecordMapper recordMapper) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.recordMapper = recordMapper;
    }

    public void createTaskDb(long chatId, String textTask, LocalDateTime dataTime) {

        NotificationTaskRecords notificationTaskRecords = new NotificationTaskRecords();
        notificationTaskRecords.setChatId(chatId);
        notificationTaskRecords.setTextMessage(textTask);
        notificationTaskRecords.setDataTimeSendMessage(dataTime);

        notificationTaskRepository.save(recordMapper.toEntity(notificationTaskRecords));

    }

    public Collection<NotificationTaskRecords> findAllTask() {
        return notificationTaskRepository.findAll()
                .stream()
                .map(recordMapper::toRecords)
                .collect(Collectors.toList());

    }

    public NotificationTaskRecords findById(long id) throws TaskByIdNotFoundException {

        return recordMapper.toRecords(notificationTaskRepository.findById(id).orElseThrow(() -> new TaskByIdNotFoundException(id)));

    }

    public NotificationTaskRecords deleteTaskById(long id) throws TaskByIdNotFoundException {
        NotificationTaskRecords notificationTaskRecords = findById(id);
        if (notificationTaskRecords != null) {
            notificationTaskRepository.deleteById(id);
        }
        return notificationTaskRecords;

    }


}