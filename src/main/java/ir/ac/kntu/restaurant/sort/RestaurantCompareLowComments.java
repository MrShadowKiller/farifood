package ir.ac.kntu.restaurant.sort;

import ir.ac.kntu.restaurant.Restaurant;

import java.util.Comparator;

public class RestaurantCompareLowComments implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        return o1.getComments().size() - o2.getComments().size();
    }
}
