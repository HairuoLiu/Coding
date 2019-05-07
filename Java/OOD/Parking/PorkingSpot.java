import java.util.*;
//ParkingSpot
//  
//  ParkingSpot(VehicleSize size)
//  
//  state(check)
//  boolean emptyAndFit(Vehicle v)
//  getCurrentVehicle()
//  
//  action 
//  park()
//  leave() 
  
class ParkingSpot {
    private final VehicleSize size;
    private Vehicle currentVehicle;

    public ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    boolean emptyAndFit(Vehicle v) {
        return currentVehicle == null && v.getVehicleSize().getSize() <= size.getSize();
    }

    // record a vehicle is parked by updating currentVehicle field
    void park(Vehicle v) {
        currentVehicle = v;
    }

    void leave() {
        currentVehicle = null;
    }

    Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}
