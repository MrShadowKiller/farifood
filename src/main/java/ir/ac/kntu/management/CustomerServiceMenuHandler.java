package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.Department;
import ir.ac.kntu.enums.customermenu.*;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.Selector;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

public class CustomerServiceMenuHandler {
    private final CustomerService customerService;

    private final ViewPerson viewPerson;

    private final Database database;

    public CustomerServiceMenuHandler(CustomerService customerService, Database database, ViewPerson viewPerson) {
        this.customerService = customerService;
        this.viewPerson = viewPerson;
        this.database = database;
    }

    public void customerMenuTabHandler(Customer customer) {
        if (!customer.getUserSetting().isAlreadyCreated()) {
            customerService.setUserSetting(customer);
        }
        customer.emptyBasket();
        viewPerson.printCustomerMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (CustomerMenuOptions.findOption(userInput)) {
            case RESTAURANTS_FOODS:
                restaurantsFoodsTabHandler(customer);
                break;
            case SUPERMARKETS:
                departmentMenu(Selector.getInstance().selectSuperMarketHandler(viewPerson,database),customer);
                break;
            case FRUITMARKETS:
                departmentMenu(Selector.getInstance().selectFruitMarketHandler(viewPerson,database),customer);
                break;
            case EDIT_INFORMATION:
                customerService.editCustomerInformation(customer);
                break;
            case SHOW_INFORMATION:
                showCustomerInformationHandler(customer);
                break;
            case ADD_BALANCE:
                addBalanceHandler(customer);
                break;
            case SETTING:
                customerService.setUserSetting(customer);
                break;
            case EXIT:
                return;
            default:
                customerMenuTabHandler(customer);
        }
        customerMenuTabHandler(customer);
    }

    public void showCustomerInformationHandler(Customer customer) {
        viewPerson.printShowCustomerTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (ShowCustomerOptions.findOption(userInput)) {
            case SHOW_INFO:
                customerService.showCustomerInfo(customer);
                break;
            case SHOW_ORDERS:
                customerService.showCustomerOrders(customer);
                break;
            case SHOW_COMMENTS:
                customerService.showCustomerComments(customer);
                break;
            case EXIT:
                return;
            default:
                showCustomerInformationHandler(customer);
                break;
        }
        showCustomerInformationHandler(customer);
    }

    public void addBalanceHandler(Customer customer) {
        viewPerson.printAddBalanceTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AddBalanceOptions.findOption(userInput)) {
            case ADD_CREDIT_CARD:
                customerService.addCreditCardHandler(customer);
                break;
            case ADD_WALLET_BALANCE:
                customerService.addWalletBalanceHandler(customer);
                break;
            case ADD_CREDIT_CARD_BALANCE:
                customerService.addCreditCardBalanceHandler(customer);
                break;
            case EXIT:
                return;
            default:
                addBalanceHandler(customer);
        }
        addBalanceHandler(customer);
    }


    public void restaurantsFoodsTabHandler(Customer customer) {
        viewPerson.printRestaurantFoodTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (BuyFoodTabOptions.findOption(userInput)) {
            case SHOW_RESTAURANTS:
                customerService.showDefaultRestaurants(customer);
                break;
            case SHOW_BEST_THREE:
                customerService.showBestThreeRestaurants(customer);
                break;
            case SHOW_WITH_BEST_FOOD:
                customerService.showBestRestaurantsForFood(customer);
                break;
            case SEARCH_BY_NAME:
                customerService.searchByRestaurantName(customer);
                break;
            case SEARCH_BY_TYPE:
                customerService.searchByRestaurantType(customer);
                break;
            case SEARCH_BY_NEIGHBOR:
                customerService.searchByNeighbor(customer);
                break;
            case SHOW_FOOD_COMMENTS:
                customerService.showFoodCommentsHandler();
                break;
            case EXIT:
                return;
            default:
                restaurantsFoodsTabHandler(customer);
        }
        restaurantsFoodsTabHandler(customer);
    }

    public void departmentMenu(Department department, Customer customer) {
        if (department == null){
            return;
        }
        customerService.setItemDepartmentSort(department, customer);
        viewPerson.printDepartmentMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (DepartmentMenuOptions.findOption(userInput)) {
            case SHOW_INFORMATION:
                customerService.showDepartmentInformationHandler(department);
                break;
            case SELECT_ITEM:
                customerService.buyItemHandler(department, customer);
                break;
            case SHOW_COMMENTS:
                viewPerson.printComments(department.getComments());
                break;
            case CHECKOUT:
                customerService.checkOutItemsHandler(customer, department);
                break;
            case EXIT:
                return;
            default:
                departmentMenu(department, customer);
        }
        departmentMenu(department, customer);
    }

}
