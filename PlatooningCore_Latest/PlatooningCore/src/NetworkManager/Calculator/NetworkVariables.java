package NetworkManager.Calculator;

import Interfaces.ICalculator;
import Utils.globals;

public class NetworkVariables implements ICalculator{

    private float speed;
    private float distance;
    private int platoonSize;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setPlatoonSize(int platoonSize) { this.platoonSize = platoonSize;    }

    public int getPlatoonSize() { return platoonSize; }

    @Override
    public void calculate(globals.GPSDataModes GPSData, float[] SensorData, int existingPlatoonSize) {

        float vehicleSpeed = SensorData[0];
        float vehicleDistance = SensorData[1];

        switch (GPSData){

            case Red:
                //if(vehicleSpeed > 10.0f || vehicleSpeed < 10.0f)
                    setSpeed(10.0f);
                //if(vehicleDistance > 10.0f)
                    setDistance(10.0f);
                if(existingPlatoonSize <= 4)
                    setPlatoonSize(4);
                break;

            case Yellow:
                //if(vehicleSpeed > 40.0f || vehicleSpeed < 40.0f)
                    setSpeed(40.0f);
                //if(vehicleDistance > 15.0f)
                    setDistance(15.0f);
                if(existingPlatoonSize <= 7)
                    setPlatoonSize(7);
                break;

            case Blue:
                //if(vehicleSpeed > 80.0f || vehicleSpeed < 80.0f)
                    setSpeed(80.0f);
                //if(vehicleDistance > 25.0f)
                    setDistance(25.0f);
                if(existingPlatoonSize <= 10)
                    setPlatoonSize(10);
                break;
        }
    }
}