package Interfaces;

import Utils.globals;

public interface IModeController {
    void setPlatoonMode(globals.PlatoonModes mode) throws InterruptedException;
    void initiateExit() throws InterruptedException;
    void FailSafe() throws InterruptedException;
    void sendMessageToDisplay(String message, globals.MessageType type);
    void sendCommandsToECU(globals.ECUCommands command);
}
