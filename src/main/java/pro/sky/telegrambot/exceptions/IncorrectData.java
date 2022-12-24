package pro.sky.telegrambot.exceptions;

/**
 * Класс для обработчика исключений
 */
public class IncorrectData {

    private String info;

    public IncorrectData(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
