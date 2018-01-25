package Platoon.PlatoonModeController;
import ECU.EControlUnit;
import GUI.Display;
import Utils.globals;
import Interfaces.IModeController;
import Platoon.Platoon;
import Vehicle.Vehicle;

public class ModeController implements IModeController {

    private Platoon platoon = Platoon.getInstance();

    private Vehicle vehicle = null;
    private EControlUnit ecu = new EControlUnit();

    public ModeController(Vehicle vehicle)
    {
        this.vehicle = vehicle;
        System.out.println("Mode Controller Started for Vehicle: " + vehicle.getVehicleId());
    }

    @Override
    public void setPlatoonMode(globals.PlatoonModes mode) throws InterruptedException {
        switch (mode){

            case Expand:
                ActivateExpandMode();
                break;
            case Shrink:
                ActivateShrinkMode();
                break;
            case Disengage:
                ActivateDisengageMode();
                break;
            case GSolo:
                ActivateSoloMode();
                break;
            case Failsafe:
                ActivateFailsafeMode();
                break;
        }
        Display.getInstance().showMessage("ModeController: setting platoon mode to " + mode, globals.MessageType.Info);
    }


    private void ActivateSoloMode() {

        Display.getInstance().showMessage("ModeController: Platoon variables adjusted for Solo mode" , globals.MessageType.Info);
    }

    private void ActivateExpandMode(){
        Display.getInstance().showMessage("ModeController: Platoon variables adjusted for platoon expansion" , globals.MessageType.Info);
    }

    private void ActivateShrinkMode() throws InterruptedException {
        initiateExit();
        Display.getInstance().showMessage("ModeController: Platoon variables adjusted for Shrinking" , globals.MessageType.Info);
    }

    private void ActivateFailsafeMode() throws InterruptedException {

        Display.getInstance().showMessage("ModeController: Platoon variables adjusted for Failsafe mode" , globals.MessageType.Info);
        FailSafe();
    }

    private void ActivateDisengageMode(){
        Display.getInstance().showMessage("ModeController: Platoon variables adjusted for Disengaging the platoon" , globals.MessageType.Info);
    }

    @Override
    public void sendMessageToDisplay(String message, globals.MessageType type) {
        Display.getInstance().showMessage(message, globals.MessageType.Info);
    }

    @Override
    public void sendCommandsToECU(globals.ECUCommands command) {
        ecu.actuate(command, vehicle);
    }

    @Override
    public void initiateExit() throws InterruptedException{

        Display.getInstance().showMessage("\nModeController: Leaving from Platoon..", globals.MessageType.Info);
        if(platoon != null && platoon.getPlatoonSize() > 0){
            platoon.decreasePlatoonSize();
            platoon.removeVehicles(vehicle);
            Display.getInstance().showMessage("Current Platoon size: " + platoon.getPlatoonSize(), globals.MessageType.Info);
            Display.getInstance().showMessage("Vehicle Exited", globals.MessageType.Info);

            if (platoon.getPlatoonSize() == 0)
                platoon = null;
        }
    }

    @Override
    public void FailSafe() throws InterruptedException{
        Display.getInstance().showMessage("Current Platoon Speed: " + platoon.getPlatoonSpeed(), globals.MessageType.Info);
        Display.getInstance().showMessage("\nMonitor: Failsafe mechanism initiated" + platoon.getPlatoonSpeed(), globals.MessageType.Warn);
        platoon.setPlatoonSpeed(platoon.getPlatoonSpeed()/2);
        Display.getInstance().showMessage("Platoon Speed Reduced to: " + platoon.getPlatoonSpeed(), globals.MessageType.Info);
        Display.getInstance().showMessage("Faulty Vehicle Exiting..", globals.MessageType.Warn);
        initiateExit();

    }

}
