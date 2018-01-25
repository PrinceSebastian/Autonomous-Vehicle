package Interfaces;

import Utils.globals;
import Vehicle.Vehicle;

public interface IEControlUnit {
    void actuate(globals.ECUCommands command, Vehicle vehicle);
    void increaseSpeed(float speed);
    void decreaseSpeed(float speed);
    void increaseDistance(float distance);
    void decreaseDistance(float distance);
    void applyBreak(globals.BreakModes breakModes);
}