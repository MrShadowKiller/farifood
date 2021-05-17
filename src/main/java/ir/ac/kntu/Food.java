package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Objects;

public class Food {
    private String name;

    private double price;

    private int timeForCooking;

    private ArrayList<UserRate> foodrates;

    public Food(String name, int timeForCooking) {
        this.name = name;
        this.timeForCooking = timeForCooking;
        foodrates = new ArrayList<>();
        price = 0;
    }

    public Food(Food food,double price) {
        this.name = food.name;
        this.timeForCooking = food.timeForCooking;
        this.price = price;
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

    public ArrayList<UserRate> getFoodrates() {
        return foodrates;
    }

    public void setFoodrates(ArrayList<UserRate> foodrates) {
        this.foodrates = foodrates;
    }

    public void addFoodRate(UserRate foodrate) {
        foodrates.add(foodrate);
    }

    @Override
    public String toString() {
        return name +"\t" + timeForCooking + "min\t" + price
                +"$\t" + getAverageRate();
    }

    public double getAverageRate() {
        if (foodrates.size()==0){
            return 5;
        }
        double average = 0;
        for (UserRate f : foodrates) {
            average += f.getRate();
        }
        return average / foodrates.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name) && timeForCooking == food.timeForCooking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
