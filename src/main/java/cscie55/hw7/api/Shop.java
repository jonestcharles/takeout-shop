package cscie55.hw7.api;

import cscie55.hw7.impl.Address;

import java.util.List;

public interface Shop <T>{
    Order placeOrder(Address address, T[] items);
    List<T> getMenu();
    void setNewMenu(List<T> newMenu);
}
