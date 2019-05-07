//VehicleSize
//  
//Vehicle
//  Car extends Vehicle
//  Truck extends Vehicle

public abstract class Vehicle {
  private final String plate; 
    public abstract VehicleSize getVehicleSize();
}

public class Car extends Vehicle {
    @Override
    public VehicleSize getVehicleSize() {
        return VehicleSize.Compact;
    }
}

public class Truck extends Vehicle {
    @Override
    public VehicleSize getVehicleSize() {
        return VehicleSize.Large;
    }
}

public enum VehicleSize{
    Compact(1),
    Large(2);

    public final int size;

    VehicleSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
