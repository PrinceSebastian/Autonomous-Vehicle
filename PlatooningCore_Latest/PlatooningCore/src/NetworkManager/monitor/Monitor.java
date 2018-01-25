package NetworkManager.monitor;

import GUI.Display;
import Utils.globals;
import NetworkManager.Calculator.NetworkVariables;
import Interfaces.IMonitor;
import Interfaces.ISensor;
import Platoon.PlatoonModeController.ModeController;
import Interfaces.IModeController;
import Platoon.Platoon;
import Sensor.SensorData;
import Vehicle.Vehicle;

import java.util.ArrayList;

public class Monitor implements IMonitor {

    public void leavePlatoon(Vehicle vehicle) throws InterruptedException {
       adjustModeController(globals.PlatoonModes.Shrink, vehicle);
    }

    enum PlatoonMode {
        LocalPlatoon,
        Solo
    }

    private Platoon platoon = null;

    private static Monitor ourInstance = new Monitor();

    private static NetworkVariables networkvariables = new NetworkVariables();

    private ISensor sensor = new SensorData();

    public static Monitor getInstance() {
        return ourInstance;
    }
    @Override
    public void doScan(Vehicle vehicle) throws InterruptedException {

        // check if vehicle has taken an exit -> shrink
        // if new request has come -> check max platoon size and validate request

        System.out.println("Monitor: Scanning for local platoons..");
        Thread.sleep(2000);

        if (platoon == null){

            platoon = Platoon.getInstance();
            platoon.setCurrentPlatoonSize(1);
            networkvariables.calculate(globals.GPSDataModes.Yellow, sensor.getSensorData(), platoon.getPlatoonSize());
            platoon.setPlatoonSafeDistance(networkvariables.getDistance());
            platoon.setPlatoonSpeed(networkvariables.getSpeed());
            platoon.setPlatoonSize(networkvariables.getPlatoonSize());
            vehicle.setDistance(platoon.getPlatoonSafeDistance());
            vehicle.setSpeed(platoon.getPlatoonSpeed());
            if(adjustModeController(globals.PlatoonModes.GSolo, vehicle)){
            sendDisplayNotification(PlatoonMode.Solo);
            platoon.insertVehicle(vehicle);
            Display.getInstance().showMessage("Calculated Platoon Speed : "+ platoon.getPlatoonSpeed(), globals.MessageType.Info);
            Display.getInstance().showMessage("Calculated Platoon Safe Distance : "+ platoon.getPlatoonSafeDistance(), globals.MessageType.Info);
            Display.getInstance().showMessage("Calculated Max Platoon Size : "+ platoon.getMaxPlatoonSize(), globals.MessageType.Info);
            }
        }
        else {

            if(platoon.getPlatoonSize() < platoon.getMaxPlatoonSize()) {
                vehicle.setDistance(platoon.getPlatoonSafeDistance());
                vehicle.setSpeed(platoon.getPlatoonSpeed());
                if(adjustModeController(globals.PlatoonModes.Expand, vehicle)){
                    sendDisplayNotification(PlatoonMode.LocalPlatoon);

                Display.getInstance().showMessage("Vehicle joined", globals.MessageType.Info);
                platoon.increasePlatoonSize();
                platoon.insertVehicle(vehicle);
                Display.getInstance().showMessage("Platoon size: " + platoon.getPlatoonSize(), globals.MessageType.Info);

                Display.getInstance().showMessage("Vehicle Speed set to : "+ vehicle.getSpeed(), globals.MessageType.Info);
                Display.getInstance().showMessage("Vehicle Safe Distance set to : "+ vehicle.getDistance(), globals.MessageType.Info);
                }
            }
            else{
                Display.getInstance().showMessage("Platoon full! Backing off..", globals.MessageType.Info);
            }
        }

    }
    @Override
    public boolean adjustModeController(globals.PlatoonModes platoonState, Vehicle vehicle) throws InterruptedException {
        IModeController controller = new ModeController(vehicle);
        controller.setPlatoonMode(platoonState);
        return true;
    }

    private void sendDisplayNotification(PlatoonMode platoonMode) {

        String message;
        switch (platoonMode) {
            case LocalPlatoon:
                message = "Found a local platoon..";
                break;

            case Solo:
                message = "Going Solo..";
                break;

            default:
                message = "No platoon!";
        }
        Display.getInstance().showMessage(message, globals.MessageType.Info);
    }

    public void reportIssue(Vehicle vehicle) throws InterruptedException {
        boolean isProblemFixed = troubleShoot(vehicle);
        if(isProblemFixed){
            Display.getInstance().showMessage("System restored for vehicle :" + vehicle.getVehicleId(), globals.MessageType.Info);
        }
        else {
            adjustModeController(globals.PlatoonModes.Failsafe, vehicle);
        }
    }

    boolean troubleShoot(Vehicle vehicle){
        Display.getInstance().showMessage("Unable to fix the problem with vehicle: " + vehicle.getVehicleId(), globals.MessageType.Warn);
        return false;
    }

    public ArrayList<Vehicle> getListOfVehicles(){
        if(platoon!=null)
            return platoon.getArrayOfVehicle();
        else
            return new ArrayList<Vehicle>();
    }

}