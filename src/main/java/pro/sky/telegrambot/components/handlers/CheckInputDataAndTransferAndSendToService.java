package pro.sky.telegrambot.components.handlers;

import com.pengrad.telegrambot.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Базовый класс родитель для обработчиков, которы взаимодействуют с пользователем и бд
 */
@Slf4j
public abstract class CheckInputDataAndTransferAndSendToService {
    /**
     * Проверить сообщение на соответвие регулрному выражению
     */
    public boolean checkTaskMessageByRegEx(Message message, String regEx) {

        if (getMather(message, regEx) == null) {
            log.info("false mather");
            return false;
        }
        return true;
    }

    protected Matcher getMather(Message message, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(message.text());
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

    /**
     * родительский абстрактный метод взаимодействия с бд
     */
    protected abstract void interactionWithDb(Message message, String regEx) throws Exception;

}
