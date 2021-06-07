package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.FruitMarket;
import ir.ac.kntu.Supermarket;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.objects.Fruit;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.objects.Product;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.Selector;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.user.SellerMan;

public class SellerManService {
    private final Database database;

    private final ViewPerson viewPerson;

    public SellerManService(Database database) {
        this.database = database;
        this.viewPerson= new ViewPerson();
    }

    public void sellerManMenuStart(SellerMan sellerMan){
        SellerManMenuHandler.getInstance().sellerManMenuHandler(sellerMan,viewPerson,database,this);
    }

    public void getOrdersHandler(SellerMan sellerMan){
        viewPerson.printOrders(sellerMan.getDepartment().getOrders());
    }

    public void getCommentsHandler(SellerMan sellerMan){
        viewPerson.printComments(sellerMan.getDepartment().getComments());
    }

    public void getDeliveriesHandler(SellerMan sellerMan){
        viewPerson.printDeliveries(sellerMan.getDepartment().getDeliveries());
    }

    public void addItemHandler(SellerMan sellerMan){
        if (sellerMan.getDepartment() instanceof Restaurant){
            Food food = Selector.getInstance().selectFood(new ViewAdmin(), database.getFoods());
            System.out.print("Price : ");
            double price = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());

            sellerMan.getDepartment().addItem(new Food(food,price));
        } else if (sellerMan.getDepartment() instanceof Supermarket) {
            Product product = Selector.getInstance().selectProduct(new ViewAdmin(), database.getProducts());
            System.out.print("Price : ");
            double price = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
            System.out.println("Stock : ");

            int stock = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            sellerMan.getDepartment().addItem(new Product(product,price,stock));
        } else if (sellerMan.getDepartment() instanceof FruitMarket){
            Fruit fruit = Selector.getInstance().selectFruit(new ViewAdmin(), database.getFruits());
            System.out.print("Price : ");
            double price = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
            System.out.println("Stock : ");

            int stock = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            sellerMan.getDepartment().addItem(new Fruit(fruit,price,stock));
        }
    }

    public void removeItemHandler(SellerMan sellerMan){
        sellerMan.getDepartment().getItems().remove(Selector.getInstance().selectItem(new ViewAdmin()
                ,sellerMan.getDepartment().getItems()));
    }

    public void addDeliveryDepartment(SellerMan sellerMan){
        if (sellerMan.getDepartment() instanceof  Restaurant){
            sellerMan.getDepartment().addDelivery(Selector.getInstance().selectRestaurantDelivery(new ViewAdmin(),
                    (Restaurant) sellerMan.getDepartment(), database));
        } else {
            sellerMan.getDepartment().addDelivery(Selector.getInstance().selectFreeDelivery(database, sellerMan.getDepartment()));
        }
    }

    public void removeDeliveryDepartment(SellerMan sellerMan){
        sellerMan.getDepartment().removeDelivery(Selector.getInstance().selectToRemoveDelivery(new ViewAdmin(),database));
    }



}