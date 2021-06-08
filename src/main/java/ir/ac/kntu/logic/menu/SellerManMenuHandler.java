package ir.ac.kntu.logic.menu;

import ir.ac.kntu.Database;
import ir.ac.kntu.logic.services.SellerManService;
import ir.ac.kntu.models.enums.adminmenu.SellerManEditOptions;
import ir.ac.kntu.models.enums.sellermanmenu.SellerManMenuOptions;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.models.user.SellerMan;
import ir.ac.kntu.util.ScannerWrapper;

public class SellerManMenuHandler {
    private final ViewPerson viewPerson;

    private final Database database;

    private final SellerManService sellerManService;

    public SellerManMenuHandler(SellerManService sellerManService, Database database, ViewPerson viewPerson) {
        this.sellerManService = sellerManService;
        this.viewPerson = viewPerson;
        this.database = database;
    }

    public void sellerManMenuTabHandler(SellerMan sellerMan) {

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
                sellerManMenuTabHandler(sellerMan);
        }
        sellerManMenuTabHandler(sellerMan);
    }

    public void sellerManDepartmentEdit(SellerMan sellerMan) {
        if (sellerMan.getDepartment() == null) {
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
