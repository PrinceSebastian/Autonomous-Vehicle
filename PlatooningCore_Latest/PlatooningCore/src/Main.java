import NetworkManager.monitor.Monitor;
import Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean i=true;
        int j;
        int id = 0;
        ArrayList<Vehicle> VehicleList;
        Scanner scanner = new Scanner( System.in );

        while(i){
            System.out.println("Please select the options from below:");
            System.out.println("1. Join a Platoon");
            System.out.println("2. Exit from Platoon");
            System.out.println("3. Force Fail Safe");
            System.out.println("4. Exit from Program");
            System.out.println("\n");

            String input = scanner.nextLine();
            j= Integer.parseInt(input);
            switch(j){
                case 1:
                    Vehicle vehicle = new Vehicle(++id,40.0f,10.0f);
                    vehicle.scanForPlatoons();
                    System.out.println("\n");
                    break;
                case 2:
                    VehicleList = Monitor.getInstance().getListOfVehicles();

                    if(VehicleList.size() > 0) {
                        System.out.println("List of vehicle Ids");
                        for (Vehicle v :
                                VehicleList) {
                            System.out.println("" + v.getVehicleId());
                        }
                        System.out.println("Select a Vehicle id to exit");
                        input = scanner.nextLine();
                        for (Vehicle v :
                                VehicleList) {
                            if (v.getVehicleId() == Integer.parseInt(input)) {
                                v.leaveFromPlatoon();
                                break;
                            }
                        }

                        System.out.println("\nList of Vehicles in a platoon");
                        VehicleList = Monitor.getInstance().getListOfVehicles();
                        for (Vehicle v :
                                VehicleList) {
                            System.out.println("" + v.getVehicleId());
                        }

                    }
                    else{
                        System.out.println("Platoon Not Found. Please Join a platoon or Go Solo");
                    }
                    System.out.println("\n");
                    break;
                case 3:
                    VehicleList = Monitor.getInstance().getListOfVehicles();
                    if(VehicleList.size() > 0) {
                        System.out.println("List of vehicle Ids");
                        for (Vehicle v :
                                VehicleList) {
                            System.out.println("" + v.getVehicleId());
                        }
                        System.out.println("Select a Vehicle id to Implement failsafe");
                        input = scanner.nextLine();
                        for (Vehicle v :
                                VehicleList) {
                            if (v.getVehicleId() == Integer.parseInt(input)) {
                                v.monitorVariables();
                                break;
                            }
                        }

                        System.out.println("\nList of Vehicles in a platoon");
                        VehicleList = Monitor.getInstance().getListOfVehicles();
                        for (Vehicle v :
                                VehicleList) {
                            System.out.println("" + v.getVehicleId());
                        }
                    }
                    else{
                        System.out.println("Platoon Not Found. Please Join a platoon or Go Solo");
                    }
                    System.out.println("\n");
                    break;
                case 4:
                    i=false;
                    break;
                default:
                    System.out.println("You have entered the wrong option.Please try again!");
                    break;
            }
        }

        if(!i){
            System.out.println("Exited from Program");
            System.out.println("\n");
        }

    }
}
