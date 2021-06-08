package ir.ac.kntu.models.departments;

import ir.ac.kntu.models.delivery.Delivery;
import ir.ac.kntu.models.objects.*;
import ir.ac.kntu.models.order.Order;
import ir.ac.kntu.util.Selector;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.models.user.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class FruitMarket extends Department implements Stackable {
    private HashMap<Integer, ArrayList<Order>> ordersSchedule;

    public FruitMarket(String name, Address address, int workHoursOpen, int workHoursClose) {
        super(name, address, workHoursOpen, workHoursClose);
        ordersSchedule = new HashMap<>();
        for (int i = workHoursOpen; i < workHoursClose; i += 2) {
            ordersSchedule.put(i, new ArrayList<>());
        }
    }

    public FruitMarket(String name, Address address, int workHoursOpen, int workHoursClose, ArrayList<Item> items) {
        super(name, address, workHoursOpen, workHoursClose);
        setItems(items);
        ordersSchedule = new HashMap<>();
        for (int i = workHoursOpen; i < workHoursClose; i += 2) {
            ordersSchedule.put(i, new ArrayList<>());
        }
    }

    public HashMap<Integer, ArrayList<Order>> getOrdersSchedule() {
        return ordersSchedule;
    }

    public boolean isFullPeriod(int period) {
        return getTotalStock(period) >= 5;
    }

    public int getTotalStock(int period) {
        int sum = 0;
        for (Order order : ordersSchedule.get(period)) {
            sum += order.getItems().size();
        }
        return sum;
    }

    @Override
    public void getDepartmentItemMenu(ViewPerson viewPerson) {
        viewPerson.printDepartmentItemMenu(this);
    }

    @Override
    public Order checkOutCustomerOrder(Customer customer) {
        int period = Selector.getInstance().orderPeriodFruitMarketSelector(this);
        if (ordersSchedule.get(period).size() >= getDeliveries().size() || customer.getBasket().size() >5) {
            System.out.println("Out of limit for orders in this period!");
            return null;
        }
        Delivery delivery = getFreedelivery(period);
        if (delivery == null) {
            System.out.println("No delivery avaliable");
            return null;
        }
        Order order = new Order(customer, this, new ArrayList<>(customer.getBasket()), delivery, LocalDateTime.now());
        for (Item item : customer.getBasket()) {
            ((Fruit) item).setStock(((Fruit) item).getStock() - 1);
        }
        ordersSchedule.get(period).add(order);
        if (!customer.isHasSubsription()) {
            if (getTotalStock(period) >= 2.5) {
                order.setShippingCost(7500);
                customer.getWallet().useBalance(7500);
            } else {
                order.setShippingCost(5000);
                customer.getWallet().useBalance(5000);
            }
        }
        return order;
    }

    public Delivery getFreedelivery(int period) {
        boolean status = false;
        for (Delivery delivery : getDeliveries()) {
            for (Order order : ordersSchedule.get(period)) {
                if (order.getDelivery() == delivery) {
                    status = true;
                }
            }
            if (!status) {
                return delivery;
            }
        }
        return null;
    }


    public ArrayList<Fruit> getFruits() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        for (Item item : getItems()) {
            if (item instanceof Fruit) {
                fruits.add((Fruit) item);
            }
        }
        return fruits;
    }

}
