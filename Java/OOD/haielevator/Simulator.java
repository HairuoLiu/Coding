//package haielevator;

import java.util.*;

public class Simulator{
	// how many floors we' re simulating 
	private final int floors;
	// all user requests 
	private List<Queue<Integer>> requests;
	// all elevators in the system 
	private List <Elevator> elevators;
	//the current elevator scheduler we use 
	private Scheduler scheduler;

	//handle to start the simulation 
	public Simulator (int floors, int numElevators, Scheduler scheduler, List <Queue <Integer>> initialRequests, int elevatorCapacity){
		this.floors = floors;
		this.scheduler = scheduler;
		requests = requests;
		elevators = new ArrayList<>();
		for(int i = 0; i < numElevators; ++i){
			Elevator ele = new Elevator(elevatorCapacity, floors);
			elevators.add(ele);
		}
	}

	private void generateRequest(){

	} 

	private void elevatorOp(){
		for(Elevator e : elevators){
			if (e.isEmpty()){
				e.unload();
			}
			if (e.isFull()){
				int location = e.getLocation();
				e.load(requests.get(location - 1));
			}
			e.move();
		}
	}

	private void schedule(){
		scheduler.schedule(requests, elevators, floors);
	}

	public void simulate(int steps) throws InterruptedException{
		for(int i = 0; i < steps; i++){
			generateRequest();
			schedule();
			elevatorOp();
			Thread.sleep(10);
		}
	}

	// entry point of the simulation program 
	public static void main(String [] args)throws InterruptedException{
		SimpleScheduler scheduler = new SimpleScheduler();
		int floors = 10;
		int numElevators = 1;
		List <Queue<Integer>> initialRequests  = new ArrayList <Queue<Integer>> ();
		for(int i = 0; i < floors; i++){
			Queue <Integer> currQueue = new LinkedList <Integer>();
			Random generator = new Random();
			int req = generator.nextInt(10);
			currQueue.offer(req);
			initialRequests.add(currQueue);
		}
		Simulator mySimulator = new Simulator(floors,numElevators,scheduler,initialRequests,14);
		mySimulator.simulate(10);
	}

	private static class SimpleScheduler implements Scheduler {
		@Override
		public void schedule (List <Queue <Integer>> requests, List <Elevator> elevators, int floors) {
			for(Elevator e : elevators){
				if (e.getLocation() == 0 || e.getLocation() == floors){
					e.changeMovingDirection();
				}
			}
		}
	}
}