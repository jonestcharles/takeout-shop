package cscie55.hw7.impl;


public class Building {

	/*
	The ID variable is static final. We will not have more than one building.
	 */
	public static final int ID = 1;

	/*
	The TOTAL_NUM_OF_FLOORS variable is static final. We will not add any more stories.
	 */
	public static final int TOTAL_NUM_OF_FLOORS= 7;

	public static int UNDEFINED_FLOOR = -1;

	private Elevator elevator;

	/**
	 * 
	 * 
	 */
	private static Floor[] floors = new Floor[TOTAL_NUM_OF_FLOORS];


	/**
	 * This method must create an Elevator, Floors
	 */
	public Building() {
		createFloors();
		elevator = new Elevator(floors);
	}

	public Elevator getElevator() {
		return elevator;
	}

	/**
	 * this method must create floor objects and add them to the appropriate index of the floors array
	 */
	private void createFloors(){
		for(int i = 0; i < TOTAL_NUM_OF_FLOORS; i++){
			floors[i] = new Floor(i);
		}
	}

	public Floor getFloor(int floorNumber) {
		return floors[floorNumber];
	}

	public Floor[] getFloors(){
		return floors;
	}


}
