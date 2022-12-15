package pro.sky.telegrambot.exceptions;

/**
 * Исключение TaskByIdNotFoundException
 */
public class TaskSetErrorException extends RuntimeException{

//    public NotFoundException(String message) {
//        super(message);
//    }

    public TaskSetErrorException() {
        super("Task format error");
    }
}
