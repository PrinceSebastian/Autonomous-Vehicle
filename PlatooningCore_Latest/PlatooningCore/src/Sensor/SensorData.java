package Sensor;

import Utils.globals;
import Interfaces.ISensor;

import java.util.ArrayList;

public class SensorData implements ISensor {
    private globals.GPSDataModes TrafficData;
    private ArrayList<Double> Location;
    private float[] sensorData;

    public SensorData() {
        this.sensorData = new float[] {20.0f,30.0f};
    }

    public ArrayList<Double> getLocation() {
        return Location;
    }

    public void setLocation(ArrayList<Double> location) {
        Location = location;
    }

    public float[] getSensorData() {
        return sensorData;
    }

    public globals.GPSDataModes getTrafficData() {
        return TrafficData;
    }

    public void setSensorData(float[] sensorData) { this.sensorData = sensorData; }

    public void setTrafficData(globals.GPSDataModes trafficData) {
        TrafficData = trafficData;
    }


    //GPS data,
}
