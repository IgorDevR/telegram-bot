package pro.sky.telegrambot.keyboardAndButtonsAndText;//package pro.sky.telegrambot.keyboardAndButtonsAndText;

import com.pengrad.telegrambot.model.request.KeyboardButton;

/**
 * кнопки / ряды кнопок
 */
public class Buttons extends Text {

    private static final KeyboardButton[] KEYBOARD_BUTTONS_ROW_SET_DELETE = new KeyboardButton[]{new KeyboardButton(setTaskText), new KeyboardButton(showAllText)};
    private static final KeyboardButton[] KEYBOARD_BUTTONS_ROW_SHOW_CANCEL = new KeyboardButton[]{new KeyboardButton(deleteText), new KeyboardButton(cancelText)};
    protected static final KeyboardButton[][] KEYBOARD_BUTTONS_SET_DELETE_SHOW_CANCEL = new KeyboardButton[][]{KEYBOARD_BUTTONS_ROW_SET_DELETE, KEYBOARD_BUTTONS_ROW_SHOW_CANCEL};


}
