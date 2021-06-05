package ir.ac.kntu.restaurant.sort;

import ir.ac.kntu.restaurant.Restaurant;

import java.util.Comparator;

public class RestaurantCompareHighComments implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        return o2.getComments().size() - o1.getComments().size();
    }
}
