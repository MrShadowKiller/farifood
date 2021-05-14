package ir.ac.kntu.user;

import ir.ac.kntu.WeekDays;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;

public class UserSetting {
    private FoodSortOption foodSortOption;

    private RestaurantSortOption restaurantSortOption;

    private WeekDays currentDay;

    public UserSetting(FoodSortOption foodSortOption, RestaurantSortOption restaurantSortOption,
                       WeekDays currentDay) {
        this.foodSortOption = foodSortOption;
        this.restaurantSortOption = restaurantSortOption;
        this.currentDay = currentDay;
    }

    public FoodSortOption getFoodSortOption() {
        return foodSortOption;
    }

    public void setFoodSortOption(FoodSortOption foodSortOption) {
        this.foodSortOption = foodSortOption;
    }

    public RestaurantSortOption getRestaurantSortOption() {
        return restaurantSortOption;
    }

    public void setRestaurantSortOption(RestaurantSortOption restaurantSortOption) {
        this.restaurantSortOption = restaurantSortOption;
    }

    public WeekDays getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(WeekDays weekDays) {
        this.currentDay = weekDays;
    }
}
