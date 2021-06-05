package ir.ac.kntu.restaurant.sort;

import ir.ac.kntu.restaurant.Restaurant;

import java.util.Comparator;

public class RestaurantCompareRising implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        if (o1.getOrders().size() >= o2.getOrders().size() && o2.getAverageRate() >=3 ){
            return 1;
        } else {
            return -1;
        }
    }
}
