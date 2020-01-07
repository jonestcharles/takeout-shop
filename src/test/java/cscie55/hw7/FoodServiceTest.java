package cscie55.hw7;

import com.fasterxml.jackson.databind.ObjectMapper;
import cscie55.hw7.exception.NoSuchApartmentException;
import cscie55.hw7.foodservice.DeliveryPerson;
import cscie55.hw7.foodservice.Dish;
import cscie55.hw7.foodservice.FoodOrder;
import cscie55.hw7.foodservice.TakeOutShop;
import cscie55.hw7.impl.Address;
import cscie55.hw7.impl.Building;
import cscie55.hw7.impl.WebBrowser;
import cscie55.hw7.reader.MenuFileReader;
import cscie55.hw7.utils.NumUtil;
import cscie55.hw7.writer.MenuWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FoodServiceTest {

    private TakeOutShop takeOutShop;
    private List<Dish> menu;
    private WebBrowser browser;


    @Before
    public void setup(){
        takeOutShop = new TakeOutShop();
        menu = takeOutShop.getMenu();
    }

    @Test
    public void verifyShopAndMenu(){
        assertNotNull(takeOutShop);
        assertEquals(64, menu.size());
    }


    @Test
    public void testAddSingleMenuItem(){
        Dish d = new Dish("Pulled pork hero", false, 1800, Dish.Type.MEAT);
        takeOutShop.addMenuItem(d);
        List<Dish> newMenu = takeOutShop.getMenu();
        assertTrue(newMenu.contains(d));
    }
    @Test
    public void testSetNewMenu(){
        // original size of menu
        assertEquals(64, takeOutShop.getMenu().size());
        List<Dish> random = getRandomOrder(15);
        takeOutShop.setNewMenu(random);
        assertEquals(15, takeOutShop.getMenu().size());
    }

   /* NOTE: This test generates a file named 'menu.json' that contains a json representation of the original hard-coded menu in the TakeOutShop class.
     That menu contains 9 elements
   */
    @Test
    public void testFileReader(){
        MenuFileReader mr = new MenuFileReader();
        takeOutShop.setNewMenu(mr.read("menu.json"));
        assertEquals(10,takeOutShop.getMenu().size());
    }

    @Test
    public void testPublishMenu(){
        // original size hard-coded menu has 64 items
        assertEquals(64,takeOutShop.getMenu().size());
        //new Dish(String name, boolean isVegetarian, int calories, Enum Dish.Type.[MEAT, FISH, OTHER]);
        List<Dish> newMenu = Arrays.asList(
                new Dish("pork sirloin", false, 800, Dish.Type.MEAT),
                new Dish("beef sirloin", false, 700, Dish.Type.MEAT),
                new Dish("chicken sirloin", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER)
        );
        takeOutShop.addMenuItemList(newMenu);
        MenuWriter.publish("cscie55menu.json",menu);

    }

    @Test
    public void testGenerateReceipt(){
        Address addr = new Address(1,2,3);
        Dish[] d = {
                new Dish("spagetti sirloin", false, 1450, Dish.Type.OTHER),
                new Dish("pasta primavera", false, 1450, Dish.Type.OTHER)
        };
        FoodOrder fo = new FoodOrder(addr,d);
        takeOutShop.generateReceipt(fo);
    }

    @Test
    public void testLoadExternalMenuFile(){
        assertEquals(64,takeOutShop.getMenu().size());
        MenuFileReader mfr = new MenuFileReader();
        List<Dish> importedMenu = mfr.read("menu.json");
        takeOutShop.setNewMenu(importedMenu);
        assertEquals(importedMenu.size(),takeOutShop.getMenu().size());
    }

    @Test
    public void testLoadS3File() {
        String url = "https://cscie-55-out.s3.amazonaws.com/menu7.json";
        BufferedReader in = null;
        try {
            URL remoteDoc = new URL(url);
            in = new BufferedReader(
                    new InputStreamReader(remoteDoc.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException ioe) {

        }
    }

    @Test
    public void testReadStreamToJson(){
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://cscie-55-out.s3.amazonaws.com/menu7.json";
        BufferedReader in = null;
        try {
            URL remoteDoc = new URL(url);
            in = new BufferedReader(
                    new InputStreamReader(remoteDoc.openStream()));
            Dish[] menu = mapper.readValue(in, Dish[].class);
            in.close();
            assertEquals(64,menu.length);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException ioe) {

        }
    }

    @Test
    public void testPartitionByCalorieLimit(){
        List<Dish> menu = takeOutShop.getMenu();
        Map<Boolean,List<Dish>> map = takeOutShop.partitionByCalorieLimit(1200);
        List<Dish> fatty = map.get(false);
        assertTrue(fatty.stream().allMatch(d -> d.getCalories() > 1200));
    }

    @Test
    public void testGetVegetarianMenu(){
        List<Dish> vegDishes = takeOutShop.getVegetarianMenu();
        boolean allVeg = (vegDishes.stream().allMatch(d -> d.isVegetarian()));
        assertTrue(allVeg);
    }

    @Test
    public void testGenericMethodSlicer(){
        List<Dish> slicedDishList = takeOutShop.slice(takeOutShop.getMenu(), 4, 15);
        MenuWriter.publish("cscie55menu.json",slicedDishList);
        assertEquals(11,slicedDishList.size());
    }
    /**======================= helper methods  ================================ */

    private Address createRandomAddress(){
        return new Address(1, NumUtil.getRandomBetween(0,6), NumUtil.getRandomBetween(0,3));

    }
    private Dish[] createRandomDishes(){
        Dish[] dishes = {menu.get(NumUtil.getRandomBetween(0,8)), menu.get(NumUtil.getRandomBetween(0,8)), menu.get(NumUtil.getRandomBetween(0,8))};
        return dishes;
    }

    private List<Dish> getRandomOrder(int numItems){
        List<Dish> itemsToOrder= new ArrayList<>();
        int maxItem = menu.size()-1;
        int items = numItems;
        while(items > 0) {
            itemsToOrder.add(menu.get(NumUtil.getRandomBetween(0, maxItem)));
            items--;
        }
        return itemsToOrder;
    }


}
