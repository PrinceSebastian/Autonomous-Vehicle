package Platoon;


import Vehicle.Vehicle;

import java.util.ArrayList;

public class Platoon  {
    private float platoonSpeed;
    private  float platoonSafeDistance;
    private int currentPlatoonSize;
    private int maxPlatoonSize;
    private ArrayList<Vehicle> arrayOfVehicle = new ArrayList<>();

    private static Platoon ourInstance = new Platoon(); // singleton

    public float getPlatoonSpeed() {
        return platoonSpeed;
    }

    public void setPlatoonSpeed(float platoonSpeed) {
        this.platoonSpeed = platoonSpeed;
    }

    public float getPlatoonSafeDistance() {
        return platoonSafeDistance;
    }

    public void setPlatoonSafeDistance(float platoonSafeDistance) {
        this.platoonSafeDistance = platoonSafeDistance;
    }

    public static Platoon getInstance() {
        return ourInstance;
    }

    public void increasePlatoonSize(){
        currentPlatoonSize++;
    }

    public void decreasePlatoonSize(){
        currentPlatoonSize--;
    }

    public int getPlatoonSize() {
        return currentPlatoonSize;
    }

    public void setCurrentPlatoonSize(int platoonSize) {
        this.currentPlatoonSize = platoonSize;
    }

    public void setPlatoonSize(int platoonSize) {
        this.maxPlatoonSize = platoonSize;
    }

    public int getMaxPlatoonSize(){
        return maxPlatoonSize;
    }

    public void insertVehicle(Vehicle v){
        arrayOfVehicle.add(v);
    }

    public void removeVehicles(Vehicle vehicle){
        arrayOfVehicle.remove(vehicle);
    }

    public ArrayList<Vehicle> getArrayOfVehicle() {
        return arrayOfVehicle;
    }
}
