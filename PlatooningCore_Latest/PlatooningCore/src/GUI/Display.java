package GUI;

import Interfaces.IDisplay;
import Utils.globals;

public class Display implements IDisplay {
    private static Display ourInstance = new Display();

    public static Display getInstance() {
        return ourInstance;
    }

    @Override
    public void showMessage(String message, globals.MessageType type) {
        System.out.println( type.toString() + " - Display Unit : " + message);
    }
}
