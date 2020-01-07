package cscie55.hw7.foodservice;

import cscie55.hw7.api.Order;
import cscie55.hw7.impl.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FoodOrder implements Order {

    /**     *
     * orderId is used to identify each unique instance.
     *
     * pickupId is used by the kitchen to indicate that the order has been processed
     * the inventoryId field is static. Thus it is shared by every instance of the FoodOrder class.
     * With each FoodOrder created, we increase it.
     */
    private int orderId = 0;
    private static int inventoryId = 0;
    private List<Dish> items = new ArrayList<>();
    private Address address;
    private static final int UNDEFINED = -1;
    private int pickupId = UNDEFINED;
    private int orderAckId = UNDEFINED;
    private String chefSignature = "";
    private static final Logger LOGGER = LogManager.getLogger(FoodOrder.class.getName());

    /**
     *
     * @param address - the address from which the order came and to which it should be delivered
     * @param dishes - an array of Dish items selected from the a menu
     */
    public FoodOrder(Address address, Dish[] dishes) {
        orderId = (++inventoryId);
        this.address = address;
        this.items = Arrays.asList(dishes);
    }

    public int getOrderId(){
        return this.orderId;
    }

    /**
     *
     * @return - a list of Dish items
     */
    public List<Dish> getItems(){
        return this.items;
    }

    public Address getAddress() {
        return this.address;
    }

    public int getPickupId() {
        return pickupId;
    }

    /**
     * pickupId is set in the kitchen by the Chef when order is ready to be added to the 'output' collection
     * @param pickupId
     */
    public void setPickupId(int pickupId) {
        this.pickupId = pickupId;
    }

    public int getOrderAckId() {
        return orderAckId;
    }

    public void setOrderAckId(int orderAckId) {
        this.orderAckId = orderAckId;
    }

    public String getChefSignature() {
        return chefSignature;
    }

    public void setChefSignature(String chefSignature) {
        this.chefSignature = chefSignature;
    }

    @Override
    public boolean equals(Object o) {
        //impl equals()
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodOrder foodOrder = (FoodOrder) o;
        return getOrderId() == foodOrder.getOrderId() &&
                getPickupId() == foodOrder.getPickupId() &&
                getItems().equals(foodOrder.getItems()) &&
                getAddress().equals(foodOrder.getAddress());
    }

    @Override
    public int hashCode() {
        //impl hashcode()
        return Objects.hash(getOrderId(), getItems(), getAddress(), getPickupId());
    }



     /**
     * this method is intended for the consumer's use.
      * IOW, it should be called when the order arrives at its destination
     */
    @Override
    public void consume() {
        // should log a message level 'info' indicating that the order is consumed
    }
}

