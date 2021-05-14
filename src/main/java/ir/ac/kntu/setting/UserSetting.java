package ir.ac.kntu.setting;

import ir.ac.kntu.WeekDays;

public class UserSetting {
    private FoodSortOption foodSortOption;

    private RestaurantSortOption restaurantSortOption;

    private WeekDays currentDay;

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
