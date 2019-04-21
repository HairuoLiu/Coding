//package haielevator;

import java.util.*;

public class Elevator{

	public static final int DEFAULT_ELEVATOR_MAX_CAPACITY = 14;
	public static final int DEFAULT_ELEVATOR_MAX_FLOOR = 10;
	public static final int DEFAULT_INITIAL_LOCATION = 1;

	// maximum capacity of this elevator 
	private final int maxCapacity;
	// how many floors in this simulation 
	private final int maxFloor;
	//current load of the elevator
	private int load;
	//current location of the elevator 
	private int location;
	//current direction of the elevator 
	private boolean isGoingUp;
	//requests 
	private int[] requests;

	public Elevator(int maxCapacity, int maxFloor){
		this.maxCapacity = maxCapacity;
		this.maxFloor = maxFloor;
		load = 0;
		location = DEFAULT_INITIAL_LOCATION;
		isGoingUp = true;
		requests = new int[maxFloor];
	}

	// returns true iff the elevator's load is 0, otherwise false
	public boolean isEmpty(){
		return load == 0;
	}
	
	// returns true iff load == max, otherwise false 
	public boolean isFull(){
		return load >= maxCapacity;
	}

	//returns the current location of the elevator 
	public int getLocation(){
		return location;
	}

	//change the elevator's location by one floor according to the direction, return new location 
	public int move(){
		if(isGoingUp){
			return (location + 1 <= maxFloor) ? ++location : location;
		}else{
			return (location - 1 >=0) ? --location : location;
		}
	}

	//reverse direction
	public boolean changeMovingDirection(){
		isGoingUp = !isGoingUp;
		return isGoingUp;
	}

	//if the elevator has enough capacity, load all requests in the given queue 
	public int load(Queue<Integer> currQueue) {
		int numNewLoad = currQueue.size();
		if(load + numNewLoad > maxCapacity){
			return 0;
		}
		for(int requestedFloor : currQueue){
			requests[requestedFloor - 1] += 1;
		}
		load += numNewLoad;
		currQueue.clear();
		return numNewLoad;
	}

	//unload all requests on the floor the elevator is currently on 
	public int unload(){
		int numRequests = requests[location - 1];
		if(numRequests > 0){
			load -= numRequests;
			requests[location - 1] = 0;
			return numRequests;
		}
		return 0;		
	}

}