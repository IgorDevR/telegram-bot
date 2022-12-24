package pro.sky.telegrambot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Обработчик исключений
 */
@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(TaskByIdNotFoundException exception) {
        IncorrectData data = new IncorrectData(exception.getMessage());
        return new ResponseEntity<IncorrectData>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception) {
        IncorrectData data = new IncorrectData(exception.getMessage());
        return new ResponseEntity<IncorrectData>(data, HttpStatus.BAD_REQUEST);
    }
}