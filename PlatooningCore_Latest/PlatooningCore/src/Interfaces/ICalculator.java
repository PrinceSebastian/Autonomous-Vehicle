package Interfaces;

import Utils.globals;

public interface ICalculator {
    void calculate(globals.GPSDataModes GPSData, float[] SensorData, int existingPlatoonSize);

}
