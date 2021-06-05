package ir.ac.kntu.restaurant.sort;

import ir.ac.kntu.restaurant.Restaurant;

import java.util.Comparator;

public class RestaurantCompareHighRate implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        if (o1.getAverageRate() - o2.getAverageRate() > 0){
            return -1;
        } else if (o1.getAverageRate() == o2.getAverageRate()){
            return 0;
        }
        return 1;
    }
}
