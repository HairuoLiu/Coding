//package haielevator;

import java.util.*;

public interface Scheduler{
	// given the state of the system, update elevator states 
	void schedule(List<Queue<Integer>> requests, List<Elevator> elevators, int floors);
}
