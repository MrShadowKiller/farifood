package ir.ac.kntu.restaurant;

import ir.ac.kntu.Department;
import ir.ac.kntu.objects.Address;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.user.WeekDays;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.setting.UserSetting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Restaurant extends Department {

    private RestaurantSchedule[] schedule;

    private RestaurantType restaurantType;

    public Restaurant(String name, Address address, int workHoursOpen, int workHoursClose,
                      RestaurantSchedule[] schedule, RestaurantType restaurantType,
                      ArrayList<Item> items) {
        super(name, address, workHoursOpen, workHoursClose);
        this.schedule = schedule;
        this.restaurantType = restaurantType;
        setItems(items);
    }

    public Restaurant(String name, Address address, int workHoursOpen, int workHoursClose,
                      RestaurantSchedule[] schedule, RestaurantType restaurantType) {
        super(name,address,workHoursOpen,workHoursClose);
        this.schedule = schedule;
        this.restaurantType = restaurantType;
    }

    public RestaurantSchedule[] getSchedule() {
        return schedule;
    }

    public void setSchedule(RestaurantSchedule[] schedule) {
        this.schedule = schedule;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public void setDayAvailable(RestaurantSchedule day) {
        for (RestaurantSchedule d : schedule) {
            if (d == day) {
                d.setAvailability(true);
            }
        }
    }

    public ArrayList<Food> getFoods(){
        ArrayList<Food> foods = new ArrayList<>();
        for (Item item : getItems() ) {
            if (item instanceof Food){
                foods.add((Food) item);
            }
        }
        return foods;
    }

    public boolean isOpen(UserSetting userSetting) {
        RestaurantSchedule restaurantSchedule = RestaurantSchedule.DEFAULT;
        restaurantSchedule = restaurantSchedule.findTheSameDay(userSetting.getCurrentDay());
        for (RestaurantSchedule day : schedule) {
            if (day == restaurantSchedule && day.getAvailability()) {
                if (userSetting.getCurrentHour() >= getWorkHoursOpen() && userSetting.getCurrentHour() <= getWorkHoursClose()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Delivery getFreeDelivery(WeekDays day) {
        for (Delivery delivery : getDeliveries()) {
            for (DeliverySchedule deliverySchedule : delivery.getSchedule()) {
                if (deliverySchedule.getRestaurant() == this) {
                    return delivery;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getName() + "\t" + getAverageRate() + "\t";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return getName().equals(that.getName()) && getAddress().equals(that.getAddress()) &&
                restaurantType == that.restaurantType && getItems().equals(that.getItems());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), restaurantType, getItems());
    }
}
