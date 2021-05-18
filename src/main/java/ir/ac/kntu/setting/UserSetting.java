package ir.ac.kntu.setting;

import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.user.WeekDays;

public class UserSetting {
    private FoodSortOption foodSortOption;

    private RestaurantSortOption restaurantSortOption;

    private WeekDays currentDay;

    private int currentHour;

    public UserSetting(FoodSortOption foodSortOption, RestaurantSortOption restaurantSortOption,
                       WeekDays currentDay, int currentHour) {
        this.foodSortOption = foodSortOption;
        this.restaurantSortOption = restaurantSortOption;
        this.currentDay = currentDay;
        this.currentHour = currentHour;
    }

    public UserSetting() {
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

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public boolean isAlreadyCreated() {
        return foodSortOption != null;
    }
}
