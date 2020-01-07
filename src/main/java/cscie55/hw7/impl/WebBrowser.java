package cscie55.hw7.impl;

import cscie55.hw7.api.Shop;
import cscie55.hw7.foodservice.Dish;
import cscie55.hw7.foodservice.FoodOrder;

import java.util.List;

public class WebBrowser {

    private Shop<Dish> foodService;
    private Apartment apartment;
    //implement a logger

    /**
     * Constructor.
     * @param apartment - the containing Apartment. IOW, the parent object since the WebBrowser is in the Apt
     * @param shop -the food 'shop' [here TakeOutShop'] that will receive and process the order
     */
    public WebBrowser(Apartment apartment, Shop shop){
        foodService = shop;
        this.apartment = apartment;
    }

    /**
     *
     * @param itemsToOrder
     * @return -boolean indicating that the order has been placed w/ the FoodService
     */
    public boolean placeOrder(List<Dish> itemsToOrder){
        List<Dish> menu = foodService.getMenu();
        FoodOrder fo = (FoodOrder) foodService
                .placeOrder(this.apartment.getAddress(), itemsToOrder.toArray(new Dish[itemsToOrder.size()]));
        apartment.addOrderWaiting(fo);
        // log a message w/ level info indicating that order has been submitted
        return (fo != null);
    }
}
