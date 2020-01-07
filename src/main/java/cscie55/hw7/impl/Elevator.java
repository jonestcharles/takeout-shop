package cscie55.hw7.impl;

import cscie55.hw7.api.Passenger;
import cscie55.hw7.exception.ElevatorFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Elevator {

	private int currentFloor = 1;
	private Floor[] floors;
	public final static int CAPACITY = 10;
	private enum DIRECTION {UP,DOWN};
	private DIRECTION direction=DIRECTION.UP;
	private List<List<Passenger>> passengers = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger(Elevator.class.getName());

	public Elevator(Floor[] floors){
		currentFloor = 1;;
		this.floors = floors;
		int n = 0;
		while (n < Building.TOTAL_NUM_OF_FLOORS){
			this.passengers.add(new ArrayList<Passenger>());
			n++;
		}
	}

		/**
		 * moves the elevator
		 */
		public void move() {
			switch(direction) {
				case UP:
					if(currentFloor < Building.TOTAL_NUM_OF_FLOORS) {
						currentFloor++;
					}
					if (currentFloor == Building.TOTAL_NUM_OF_FLOORS){
					direction = DIRECTION.DOWN;
					currentFloor--;
				}
					break;
				case DOWN:
					if(currentFloor > 1){
						currentFloor--;
					}
					else {
						direction = DIRECTION.UP;
					}
			}
			if(passengers.get(currentFloor).size() > 0) {
				unloadPassengers();
			}

				int passengersGoingUp = floors[currentFloor-1].getPassengersGoingUp().size();
				int passengersGoingDown = floors[currentFloor-1].getDownwardBound().size();
				if (direction == DIRECTION.UP && passengersGoingUp > 0) {
					ArrayDeque<Passenger> goingUp = floors[currentFloor-1].getPassengersGoingUp();
					try {
					while (passengersGoingUp > 0) {
							boardPassenger(goingUp.getFirst());
							goingUp.removeFirst();
							passengersGoingUp--;
						}
					}
					catch (ElevatorFullException efe) {
                        LOGGER.error(efe.getMessage());
					}
				}
				else if(direction == DIRECTION.DOWN && passengersGoingDown > 0){
					ArrayDeque<Passenger> goingDown = floors[currentFloor-1].getDownwardBound();
					while(passengersGoingDown > 0){
						try {
							boardPassenger(goingDown.getFirst());
							goingDown.removeFirst();// if it succeeds
							passengersGoingDown--;
						}
						catch (ElevatorFullException efe) {
                            LOGGER.error(efe.getMessage());
						}
					}
				}

		}

	public void boardPassenger(Passenger passenger) throws ElevatorFullException {
		// get destination of passenger and use it to add to passengers traveling to that floor
		// throw Exception if > 10 try to board
		if(getNumberPassengers() < CAPACITY) {
			int destination = passenger.getDestination();
			passengers.get(destination).add(passenger);
			passenger.setCurrrentFloor(Building.UNDEFINED_FLOOR);
		}
		else{
            LOGGER.error("ElevatorFullException");
			throw new ElevatorFullException("Maximum reached! Please wait.");
		}
	}

	private void unloadPassengers() {
		// remove passengers destined for the floor at this stop. Add Residents to the residents collection.
		if(currentFloor < Building.TOTAL_NUM_OF_FLOORS) {
			ArrayDeque residents = floors[currentFloor].getResidents();
			for(Passenger p : passengers.get(currentFloor)){
				p.arriveOnFloor(currentFloor);
				residents.add((Resident)p);
			}
			passengers.get(currentFloor).clear();
		}
	}

	public int getCurrentFloor() {
			int revised = currentFloor;
		return revised;
	}

	private void setCurrentFloor(int floorNum) {
		// Optional. might be handy. Delete if you don't want it
		currentFloor = floorNum;
	}

	public String toString() {
		return "Floor "+currentFloor +": "+ getNumberPassengers()+" passengers";
	}

	public int getNumberPassengers() {
		//store number [or sum up number] of passengers and return
		int onboard = 0;
		for(int i = 0; i < passengers.size(); i++){
			onboard += passengers.get(i).size();
		}
		return onboard;
	}

}
