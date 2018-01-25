package Interfaces;

import Utils.globals;

import java.util.ArrayList;

public interface ISensor {
    float[] getSensorData();
    ArrayList<Double> getLocation();
    void setLocation(ArrayList<Double> location);
    globals.GPSDataModes getTrafficData();
    void setSensorData(float[] sensorData);
    void setTrafficData(globals.GPSDataModes trafficData);
}
