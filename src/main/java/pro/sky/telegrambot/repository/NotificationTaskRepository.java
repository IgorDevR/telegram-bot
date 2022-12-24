package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entity.NotificationTask;

import java.time.LocalDateTime;
import java.util.Collection;
/**
 * репозиторий для работы с бд
 */
public interface  NotificationTaskRepository extends JpaRepository <NotificationTask, Long> {

public Collection<NotificationTask> findAllByDataTimeSendMessage(LocalDateTime localDateTime);






}
