package pro.sky.telegrambot.keyboardAndButtonsAndText;//package pro.sky.telegrambot.keyboardAndButtonsAndText;
/**
 * весь текст используемый в боте
 */
public abstract class Text {

    public static final String unknownMessageText = "Unknown command";
    public static final String returnMainMenuText = "Return to main menu";
    public static final String startText = "/start";
    public static final String showAllText = "Show All";
    public static final String setTaskText = "Set Task";
    public static final String cancelText = "Cancel";
    public static final String cancelReturnMainMenuText = "Cancel. Return to main menu";
    public static final String deleteText = "Delete Task";


    public static final String unknownReturnMainMenuText = "Unknown command. Return to main menu";
    public static final String deleteIdText = "Enter the task Id and click send message to delete.";
    public static final String taskSuccessfullyDeleteText = "Task with id was successfully deleted.";
    public static final String startGreetingsText = new StringBuilder("Hello, the bot sends a notification with the specified message at the set time.")
            .append("\n")
            .append("To create a new notification, click ")
            .append(setTaskText)
            .append(".\n")
            .append("To view all tasks, click Show All.\n")
            .append("To delete, click Delete Task.\n")
            .append("To cancel the operation, click Cancel.")
            .toString();

    public static final String replySetTaskText = new StringBuilder("Enter the text and trigger time of the task in the format - ")
            .append("\"01.01.2022 20:00 task text\"")
            .append("and click the send message button. Or click the button ")
            .append(cancelText)
            .append(".")
            .toString();

    public static final String errorDeleteByIdText = "Error delete by id. Id is not correct.";
    public static final String errorSetTaskFormatText = "Error set task format. Retry again";
    public static final String taskSetSuccessfulText = "Task set successfully.";
    public static final String successfulOperationText = "Successful operation.";

    public static final String startTextDescription = "Start bot";
    public static final String setTaskTextDescription = "Set a new task";
    public static final String cancelTextDescription = "Cancel current action";
    public static final String showAllTextDescription = "Show all tasks";
}
