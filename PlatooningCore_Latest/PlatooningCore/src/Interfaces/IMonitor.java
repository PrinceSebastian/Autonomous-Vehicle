package Interfaces;

import Utils.globals;
import Vehicle.Vehicle;

public interface IMonitor {
     void doScan(Vehicle vehicle) throws InterruptedException;
     boolean adjustModeController(globals.PlatoonModes platoonState, Vehicle vehicle) throws InterruptedException;
}
