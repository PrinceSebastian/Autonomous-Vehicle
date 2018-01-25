package ECU;

import GUI.Display;
import Utils.globals;
import Vehicle.Vehicle;
import Interfaces.*;


public class EControlUnit implements IEControlUnit{

    public void actuate(globals.ECUCommands command, Vehicle vehicle){
        switch (command) {
            case ApplyBreakToStop:
                applyBreak(globals.BreakModes.HardBreak);
                break;
            case DescreaseSpeed:
                decreaseSpeed(vehicle.getSpeed());
                applyBreak(globals.BreakModes.SoftBreak);
                break;
            case IncreaseSpeed:
                increaseSpeed(vehicle.getSpeed());
                applyBreak(globals.BreakModes.NoBreak);
                break;
            case Increasedistance:
                increaseDistance(vehicle.getDistance());
                applyBreak(globals.BreakModes.SoftBreak);
                break;
            case Descreasedistance:
                decreaseDistance(vehicle.getDistance());
                applyBreak(globals.BreakModes.NoBreak);
                break;
            case StartAlarm:
                break;
            case StartLeftIndicator:
                break;
            case StartRightIndicator:
                break;
            case TurnOffAllIndicators:
                break;
            case TurnOnParkingLight:
                break;
            case TurnOffParkingLight:
                break;
        }
    }

    public void increaseSpeed(float speed){
        Display.getInstance().showMessage("Increase speed to:"+speed, globals.MessageType.Info);
    }

    public void decreaseSpeed(float speed){
        Display.getInstance().showMessage("Decrease speed to:"+speed, globals.MessageType.Info);
    }

    public void increaseDistance(float distance){
        Display.getInstance().showMessage("Increase distance to:"+distance, globals.MessageType.Info);
    }

    public void decreaseDistance(float distance){
        Display.getInstance().showMessage("Decrease distance to:"+distance, globals.MessageType.Info);
    }

    public void applyBreak(globals.BreakModes breakModes){
        switch(breakModes){
            case NoBreak:
                Display.getInstance().showMessage("Not applying breaks", globals.MessageType.Info);
                break;
            case SoftBreak:
                Display.getInstance().showMessage("Applying break till target speed achieved", globals.MessageType.Info);
                break;
            case HardBreak:
                Display.getInstance().showMessage("Applying break till hard stop of vehicle", globals.MessageType.Info);
                break;
        }
    }
}
