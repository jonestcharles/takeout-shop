package cscie55.hw7.impl;

import cscie55.hw7.api.Passenger;
import cscie55.hw7.exception.NoSuchApartmentException;

import java.util.ArrayDeque;

public class Floor {

    private int id;
    private Apartment[] apartments = new Apartment[4];
    private int passengersWaiting = 0;
    private ArrayDeque<Passenger> residents = new ArrayDeque<>();
    private ArrayDeque<Passenger> upwardBound = new ArrayDeque<>();
    private ArrayDeque<Passenger> downwardBound = new ArrayDeque<>();


    /**
     * @param id the id of this Floor. Note that the Building has a max of 7 FLoors
     * @see Building, Building.TOTAL_NUM_OF_FLOORS
     */
    public Floor(int id) {
        this.id = id;
        //Create 4 Apartments for this floor (that is, each floor will have 4 Apartments)
        for (int i = 0; i < apartments.length; i++) {
            apartments[i] = new Apartment(i, id);
        }
    }

    /*
        There is no limit on the number of passengers who can wait
 */
    public void callElevator(Passenger p) {
        // implement operations that place the Passengers who want to get on the Elevator on this floor
        // use this method to decide if the passenger should be put into upwardBound or downwardBound ArrayDeque.
        if (p.getDestination() > id) {
            upwardBound.addLast(p);
        } else if (p.getDestination() < id) {
            downwardBound.addLast(p);
        }
    }

    public int getPassengersWaiting() {
        // implement a method that returns the number of people who have called the Elevator
        return passengersWaiting = upwardBound.size() + downwardBound.size();
    }

	/*
    The method below is optional. To get the # of passengers waiting, we can just sum the upward and downward bound lists
     */
    public void updatePassengersWaiting() {
        // implement a field that can hold the number of passengers who want to get on the Elevator on this floor
        // use this method to add to that number
        passengersWaiting = downwardBound.size() + upwardBound.size();
    }

    public Apartment getApartment(int apartmentNumber) throws NoSuchApartmentException {
        return apartments[apartmentNumber];
    }

    public ArrayDeque<Passenger> getPassengersGoingUp() {
        return upwardBound;
    }

    public ArrayDeque<Passenger> getDownwardBound() {
        return downwardBound;
    }

    public ArrayDeque<Passenger> getResidents() {
        return residents;
    }

}
