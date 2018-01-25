package Vehicle;

import GUI.Display;
import Interfaces.IDisplay;
import Utils.globals;
import Interfaces.IModeController;
import NetworkManager.monitor.Monitor;
import Sensor.SensorData;

public class Vehicle {
    private int vehicleId;
    public float speed;
    public float distance;
    public globals.GPSDataModes gpsDataModes;
    private IDisplay display;
    public IModeController controller;
    boolean foundPlatoon = false;
    public SensorData sensor = new SensorData();


    public Vehicle(float speed, float distance, globals.GPSDataModes gpsDataModes) {
        this.speed = speed;
        this.distance = distance;
        this.gpsDataModes = gpsDataModes;
        this.display = new Display();

    }

    public Vehicle(int id,float speed, float distance) {
        this.vehicleId = id;
        this.speed = speed;
        this.distance = distance;
        this.display = new Display();
        float[] SensorDataStub = {30.0f,40.0f};
        sensor.setSensorData(SensorDataStub);
    }

    public void scanForPlatoons() throws InterruptedException {
         Monitor.getInstance().doScan(this);

    }

    public void leaveFromPlatoon() throws InterruptedException {
        Monitor.getInstance().leavePlatoon(this);
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDistance() {
        return distance;
    }

    public float getSpeed() {
        return speed;
    }

    public void alertDriver(String message, globals.MessageType type){
        display.showMessage(message, type);
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void monitorVariables() throws InterruptedException{
        float[] SensorData = sensor.getSensorData();
        if((SensorData[0] != speed) && (distance != SensorData[1])) {
            Monitor.getInstance().reportIssue(this);
        }
    }
}
