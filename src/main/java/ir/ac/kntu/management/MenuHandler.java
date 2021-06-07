package ir.ac.kntu.management;

import ir.ac.kntu.enums.adminmenu.AdminMenuOptions;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.user.Admin;

public class MenuHandler {
    private static final MenuHandler INSTANCE = new MenuHandler();

    private MenuHandler() {
    }

    public static MenuHandler getInstance(){
        return INSTANCE;
    }

    public void adminMenuHandler(Admin admin, ViewAdmin viewAdmin,AdminService adminService) {
        viewAdmin.printAdminStartMenu();
        int adminOptionInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AdminMenuOptions.findOption(adminOptionInput)) {
            case ADMINS -> adminService.adminsTabHandler(admin);
            case CUSTOMERS -> adminService.customersTabHandler(admin);
            case DELIVERIES:
                adminService.deliveriesTabHandler(admin);
                break;
            case FOOD:
                adminService.foodTabHandler(admin);
                break;
            case RESTAURANTS:
                adminService.restaurantsTabHandler(admin);
                break;
            case SUPERMARKET:
                adminService.superMarketTabHandler(admin);
                break;
            case ORDERS:
                adminService.ordersTabHandler(admin);
                break;
            case EXIT:
                return;
            default:
                adminMenuHandler(admin);
        }
        adminMenuHandler(admin);
    }


}
