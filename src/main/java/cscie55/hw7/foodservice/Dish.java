package cscie55.hw7.foodservice;

import java.util.List;

public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public List<Dish> menu;

    public enum Type { MEAT, FISH, OTHER }

    public Dish() {
    }

    public Dish(String name, boolean isVegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = isVegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public List<Dish> getMenu(){
        return this.menu;
    }

    public void setType(Type type) { this.type = type; }

    @Override
    public String toString() {
        return name;
    }

}

