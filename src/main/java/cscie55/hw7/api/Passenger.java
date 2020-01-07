package cscie55.hw7.api;

public interface Passenger {

	int getDestination();

	void setDestination(int destinationFloor);

	int getCurrentFloor();

	void setCurrrentFloor(int currentFloor);

	void arriveOnFloor(int arrivalFloor);

}
