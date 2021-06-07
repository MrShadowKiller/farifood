package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.enums.adminmenu.SellerManEditOptions;
import ir.ac.kntu.enums.adminmenu.SuperMarketEditOptions;
import ir.ac.kntu.enums.sellermanmenu.SellerManMenuOptions;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.user.SellerMan;

public class SellerManMenuHandler {
    private static final SellerManMenuHandler INSTANCE = new SellerManMenuHandler();
    private ViewPerson viewPerson;

    private Database database;

    private SellerManService sellerManService;

    private SellerManMenuHandler() {
    }

    public static SellerManMenuHandler getInstance() {
        return INSTANCE;
    }

    public void sellerManMenuHandler(SellerMan sellerMan,ViewPerson viewPerson,Database database,SellerManService sellerManService){
        this.sellerManService = sellerManService;
        this.viewPerson = viewPerson;
        this.database = database;
        viewPerson.printSellerManMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (SellerManMenuOptions.findOption(userInput)) {
            case EDIT_DEPARTMENT_INFO:
                sellerManDepartmentEdit(sellerMan);
                break;
            case GET_ORDERS:
                sellerManService.getOrdersHandler(sellerMan);
                break;
            case GET_DELIVERIES:
                sellerManService.getDeliveriesHandler(sellerMan);
                break;
            case GET_COMMENTS:
                sellerManService.getCommentsHandler(sellerMan);
                break;
            case EXIT:
                return;
            default:
                sellerManMenuHandler(sellerMan,viewPerson,database,sellerManService);
        }
        sellerManMenuHandler(sellerMan,viewPerson,database,sellerManService);
    }

    public void sellerManDepartmentEdit(SellerMan sellerMan){
        if (sellerMan.getDepartment() == null){
            System.out.println("NO DEPARTMENT!");
            return;
        }
        viewPerson.printSellerManEditDepartmentTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (SellerManEditOptions.findOption(userInput)) {
            case CHANGE_NAME:
                database.changeDepartmentName(sellerMan.getDepartment());
                break;
            case ADD_ITEM:
                sellerManService.addItemHandler(sellerMan);
                break;
            case REMOVE_ITEM:
                sellerManService.removeItemHandler(sellerMan);
                break;
            case ADD_DELIVERY:
                sellerManService.addDeliveryDepartment(sellerMan);
                break;
            case REMOVE_DELIVERY:
                sellerManService.removeDeliveryDepartment(sellerMan);
                break;
            case EXIT:
                return;
            default:
                sellerManDepartmentEdit(sellerMan);
        }
        sellerManDepartmentEdit(sellerMan);
    }



}