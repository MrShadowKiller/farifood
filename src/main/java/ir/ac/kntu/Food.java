package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Objects;

public class Food {
    private String name;

    private double price;

    private int timeForCooking;

    private ArrayList<FoodRate> foodrates;

    public Food(String name, int timeForCooking) {
        this.name = name;
        this.timeForCooking = timeForCooking;
        foodrates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimeForCooking() {
        return timeForCooking;
    }

    public void setTimeForCooking(int timeForCooking) {
        this.timeForCooking = timeForCooking;
    }

    public ArrayList<FoodRate> getFoodrates() {
        return foodrates;
    }

    public void setFoodrates(ArrayList<FoodRate> foodrates) {
        this.foodrates = foodrates;
    }

    public void addFoodRate(FoodRate foodrate) {
        foodrates.add(foodrate);
    }

    public double averageRate() {
        double average = 0;
        for (FoodRate f : foodrates) {
            average += f.getRate();
        }
        return average / foodrates.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
