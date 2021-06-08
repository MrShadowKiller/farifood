package ir.ac.kntu.models.departments.restaurant;

import ir.ac.kntu.models.departments.Department;
import ir.ac.kntu.models.objects.Address;
import ir.ac.kntu.models.objects.Food;
import ir.ac.kntu.models.objects.Item;
import ir.ac.kntu.models.order.Order;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.models.user.Customer;
import ir.ac.kntu.models.user.WeekDays;
import ir.ac.kntu.models.delivery.DeliverySchedule;
import ir.ac.kntu.models.delivery.Delivery;
import ir.ac.kntu.models.user.setting.UserSetting;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        super(name, address, workHoursOpen, workHoursClose);
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

    public ArrayList<Food> getFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        for (Item item : getItems()) {
            if (item instanceof Food) {
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
                if (deliverySchedule.getDepartment() == this && deliverySchedule.getRate() == day.getRate()) {
                    return delivery;
                }
            }
        }
        return null;
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

    @Override
    public void getDepartmentItemMenu(ViewPerson viewPerson) {
        viewPerson.printDepartmentItemMenu(this);
    }

    @Override
    public Order checkOutCustomerOrder(Customer customer) {
        Delivery delivery = getFreeDelivery(customer.getUserSetting().getCurrentDay());
        if (delivery == null) {
            System.out.println("There is no Delivery available");
            return null;
        }
        Order order = new Order(customer, this, new ArrayList<>(customer.getBasket()),
                delivery, LocalDateTime.now());
        delivery.addOrder(order);
        return (order);
    }
}
