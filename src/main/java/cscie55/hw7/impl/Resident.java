package cscie55.hw7.impl;

import cscie55.hw7.api.Passenger;
import cscie55.hw7.api.Person;
import cscie55.hw7.exception.KeyDoesNotFitException;
import cscie55.hw7.exception.TooManyResidentsException;

public class Resident extends Person implements Passenger {

    private int destinationFloor;
    private int currentFloor;
    private final int doorKey; // this value is derived from hashCode() of address

    /**
     * This class' constructor requires parameters that are passed to the super class.
     * Also, the hashCode from the address object is set in the resident as a 'doorKey'
     *
     * @param firstName
     * @param lastName
     * @param address
     */
    public Resident(String firstName, String lastName, Address address) {
        super(firstName, lastName, address);
        currentFloor = 1;
        destinationFloor = Building.UNDEFINED_FLOOR;
        doorKey = address.hashCode();
    }

    /**
     * @param apartment - the apt to enter
     * @param key - the key code that will be entered
     * @throws KeyDoesNotFitException
     */
    public void enterApartment(Apartment apartment, int key) throws KeyDoesNotFitException {
            if (apartment.getKey() == getDoorKey()) {
                try {
                    apartment.addResident(this);
                    this.setCurrrentFloor(apartment.getFloorId());
                } catch (TooManyResidentsException tmr) {
                    // log error
                }
            } else {
                throw new KeyDoesNotFitException("You don't have permission to enter this apartment");
            }
    }

    /**
     * returns an int indicating the floor to which the Resident [as Passenger] wants to reach.
     *
     * @see Passenger#getDestination()
     */
    public int getDestination() {
        return destinationFloor;
    }

    /**
     * @see Passenger#setDestination(int)
     */
    public void setDestination(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public void setCurrrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public void arriveOnFloor(int arrivalFloor) {
        //set the state properties of the resident to reflect their arrival
        this.setDestination(Building.UNDEFINED_FLOOR);
        this.setCurrrentFloor(arrivalFloor);
    }

    public int getDoorKey() {
        return this.doorKey;
    }

}
