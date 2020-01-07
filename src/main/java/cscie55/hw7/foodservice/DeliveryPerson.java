package cscie55.hw7.foodservice;

import cscie55.hw7.api.Passenger;
import cscie55.hw7.api.Person;
import cscie55.hw7.api.Shop;
import cscie55.hw7.impl.Address;
import cscie55.hw7.impl.Apartment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// add fields to DeliveryPerson class similar to Resident class, as in HW 3.

public class DeliveryPerson extends Person implements Passenger, Runnable{
    private Map<Integer, List<FoodOrder>> ordersReady = new HashMap<>();
    private Shop employer;

    public DeliveryPerson(String firstName, String lastName, Address address, Shop employer){
        super(firstName, lastName, address);
        this.employer = employer;
    }

    @Override
    public int getDestination() {
    // implement
        return 0;
    }

    @Override
    public void setDestination(int destinationFloor) {
     // implement
    }

    @Override
    public int getCurrentFloor() {
        // implement
        return 0;
    }

    @Override
    public void setCurrrentFloor(int currentFloor) {
    // implement
    }

    @Override
    public void arriveOnFloor(int arrivalFloor) {
     //  implement. Set the state of the DeliveryPerson. Their currentFloor is the arrival flor.
        //  but the delivery person must return to floor 1 so set destination accordingly.
    }

    public String ringApartment(Apartment apt){
        return "Ringing floor: "+apt.getAddress().getFloorId()+", apt: "+apt.getAddress().getApartmentId();
    }

    public int getDoorKey() {
        // implement
        return 0;
    }

    @Override
    public void run() {
        if(ordersReady != null){

        }

    }

    public Map<Integer, List<FoodOrder>> getOrdersReady() {
        return ordersReady;
    }

    public void setOrdersReady(Map<Integer, List<FoodOrder>> ordersReady) {
        this.ordersReady = ordersReady;
    }
}
