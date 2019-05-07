import java.util.*;
//Level
//
//  Level(int numOfSpots) 
//  
//  State
//  boolean hasSpot(Vehicle v)
//  Action
//  boolean park(Vehicle v)
//  boolean leave(Vehicle v) 

class Level {
    private final List<ParkingSpot> spots;

    Level(int numOfSpots) {
        List<ParkingSpot> list = new ArrayList<>();

        // example of signing sizes to spots
        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i < numOfSpots; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }

        // returns a list that is read-only, because someone may try to change list by mistake
        // final means reference can not change, but may still change the data inside
        spots = Collections.unmodifiableList(list);
    }

    boolean hasSpot(Vehicle v) {
        for(ParkingSpot s : spots) {
            if (s.emptyAndFit(v)) {
                return true;
            }
        }
        return false;
    }

    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if(s.emptyAndFit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }

    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getCurrentVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }
}
