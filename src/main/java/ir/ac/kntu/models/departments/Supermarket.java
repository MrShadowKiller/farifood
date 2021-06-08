package ir.ac.kntu.models.departments;

import ir.ac.kntu.models.delivery.Delivery;
import ir.ac.kntu.models.objects.Address;
import ir.ac.kntu.models.objects.Item;
import ir.ac.kntu.models.objects.Product;
import ir.ac.kntu.models.order.Order;
import ir.ac.kntu.util.Selector;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.models.user.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Supermarket extends Department implements Stackable {
    private final HashMap<Integer, ArrayList<Order>> ordersSchedule;

    public Supermarket(String name, Address address, int workHoursOpen, int workHoursClose) {
        super(name, address, workHoursOpen, workHoursClose);
        ordersSchedule = new HashMap<>();
        for (int i = workHoursOpen; i < workHoursClose; i++) {
            ordersSchedule.put(i, new ArrayList<>());
        }
    }

    @Override
    public void getDepartmentItemMenu(ViewPerson viewPerson) {
        viewPerson.printDepartmentItemMenu(this);
    }

    @Override
    public Order checkOutCustomerOrder(Customer customer) {
        int period = Selector.getInstance().orderPeriodSuperMarketSelector(this);
        if (ordersSchedule.get(period).size() >= getDeliveries().size()) {
            System.out.println("Cant order in this clock");
            return null;
        }
        Delivery delivery = getFreedelivery(period);
        if (delivery == null) {
            System.out.println("No delivery avaliable");
            return null;
        }
        Order order = new Order(customer, this, new ArrayList<>(customer.getBasket()), delivery, LocalDateTime.now());
        for (Item item : customer.getBasket()) {
            ((Product) item).setStock(((Product) item).getStock() - 1);
        }
        ordersSchedule.get(period).add(order);
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

    public Supermarket(String name, Address address, int workHoursOpen, int workHoursClose, ArrayList<Item> items) {
        super(name, address, workHoursOpen, workHoursClose);
        ordersSchedule = new HashMap<>();
        for (int i = workHoursOpen; i < workHoursClose; i++) {
            ordersSchedule.put(i, new ArrayList<>());
        }
        setItems(items);
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (Item item : getItems()) {
            if (item instanceof Product) {
                products.add((Product) item);
            }
        }
        return products;
    }

}
