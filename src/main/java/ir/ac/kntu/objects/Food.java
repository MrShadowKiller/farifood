package ir.ac.kntu.objects;

import ir.ac.kntu.user.UserRate;

import java.util.ArrayList;
import java.util.Objects;

public class Food extends Item {
    private int timeForCooking;

    public Food(String name, int timeForCooking) {
        super(name,0);
        this.timeForCooking = timeForCooking;
    }

    public Food(Food food) {
        super(food.getName(),food.getPrice());
        this.timeForCooking = food.timeForCooking;
    }

    public Food(Food food, double price) {
        super(food.getName(),price);
        this.timeForCooking = food.timeForCooking;
    }

    public int getTimeForCooking() {
        return timeForCooking;
    }

    @Override
    public String toString() {
        return getName() + "\t" + timeForCooking + "min\t" + getPrice()
                + "$\t" + getAverageRate();
    }

    public double getAverageRate() {
        if (foodrates.size() == 0) {
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
        return getName().equals(food.getName()) && timeForCooking == food.timeForCooking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }
}
