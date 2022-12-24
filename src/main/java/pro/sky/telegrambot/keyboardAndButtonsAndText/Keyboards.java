package pro.sky.telegrambot.keyboardAndButtonsAndText;

import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

/**
 * Клавиатуры
 */
public class Keyboards extends Buttons {

    /**
     * главное меню, 4 кнопки
     */
    public static final ReplyKeyboardMarkup KEYBOARD_MAIN_MENU = new ReplyKeyboardMarkup(KEYBOARD_BUTTONS_SET_DELETE_SHOW_CANCEL).resizeKeyboard(true);
    /**
     * кнопка отмена
     */
    public static final ReplyKeyboardMarkup KEYBOARD_CANCEL = new ReplyKeyboardMarkup(new KeyboardButton(cancelText)).resizeKeyboard(true);
    /**
     * кнопка удалить
     */
    public static final ReplyKeyboardMarkup KEYBOARD_DELETE = new ReplyKeyboardMarkup(new KeyboardButton(deleteText)).resizeKeyboard(true);

}
