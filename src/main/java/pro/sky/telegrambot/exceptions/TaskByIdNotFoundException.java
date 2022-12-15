package pro.sky.telegrambot.exceptions;

/**
 * Исключение TaskByIdNotFoundException
 */
public class TaskByIdNotFoundException extends RuntimeException{

//    public NotFoundException(String message) {
//        super(message);
//    }

    public TaskByIdNotFoundException() {
        super("Task with this id not found");
    }
    public TaskByIdNotFoundException(long id) {
        super("Task with this id " + id + " not found");
    }
}
