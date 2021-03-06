import classes.Input;
import db.Customers;
import db.Items;
import db.Orders;
import db.Staffs;
import object.Customer;
import object.Item;
import object.Order;
import object.Staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static Input input; //Takes input from user

    private static Staff currentStaff;

    private static final String currencySymbol = "RM ";

    public static void main(String[] args)
    {
        input = new Input();

        System.out.println("Retail Management System V1.0");
        /*
         * If there are no staff, it will go to the first launch function.
         * Else if a staff profile is currently created in the database, it will go to the login screen.
         */
        if (!Staffs.hasStaffs())
            firstLaunch();
        else
            loginScreen();


        /*
         * Main Screen (After launching):
         * 1)Login
         * 2)Create staff ( only master password )
         *
         * Main Screen:
         * 1) Inventory Control
         * 2) Billing
         * 3) Customers
         * 4) Edit my profile
         * 5) Add/Remove staffs (if permission is given show this)
         *
         * -Main Screen Inventory Control:
         * 1) Items
         * 2) Item Stock
         *
         * --Modify Item
         * 1) Get item details
         * 2) Add item
         * 3) Remove item
         * 4) Edit item
         *
         * --Modify Stock
         * 1) Edit stock of item
         * 2) Check stock
         * 3) List Out of Stock Items
         *
         * -Billing
         * 1) Add new bill
         * 2) Check bill
         *
         * -Customers
         * 1) Add customer
         * 2) Check customer details
         * 3) Modify Customer
         *
         * -Edit my profile
         * 1) show information and directly ask for new info (leave blank to keep it uncahnged)
         *
         * -Staffs
         * 1) Add Staff
         * 2) Remove staff
         */
    }

    /**
     * Creating a staff profile.
     */
    private static void firstLaunch()
    {
        String first_name, last_name, password;

        System.out.println("Welcome, create a new staff login: ");

        System.out.print("Enter Staff First Name: ");
        first_name = input.next();
        System.out.print("Enter Staff Last Name: ");
        last_name = input.next();
        System.out.print("Enter Password: ");
        password = input.next();

        Staffs.addStaff(1, first_name, last_name, password, true);

        System.out.println("Staff created. You can now login using the details below.");
        System.out.println("Staff ID: " + 1);
        System.out.println("Staff Password: " + password);
        divider();
        loginScreen();

    }

    /**
     * Asks the staff to enter the credentials.
     */
    private static void loginScreen()
    {
        int id;
        String password;

        System.out.println("Welcome, enter login details below: (Enter ID as 0 to exit) ");
        do {
            System.out.print("Staff ID: ");
            id = input.nextInt();

            if (id == 0)
                return;

            System.out.print("Password: ");
            password = input.next();

            currentStaff = Staffs.Login(id, password);
            if (currentStaff == null)
                System.out.println("Invalid Login");
        } while (currentStaff == null);

        mainMenu();
    }

    /**
     * Once the staff has logged in, this is the Main Menu.
     */
    private static void mainMenu()
    {
        divider();
        System.out.println("Retail Management System (RMS) v1.0");
        System.out.println("Main Menu");
        System.out.println("1) Inventory Control\n" +
                "2) Billing\n" +
                "3) Customers\n" +
                "4) Edit my profile\n" +
                (currentStaff.getIsAdmin() ? "5) Add/Remove staffs\n" : "") + // If the staff is admin, the admin can add or remove other staffs.
                "0) Exit");
        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    exit();
                    break;
                case 1:
                    showInventoryMenu();
                    break;
                case 2:
                    showBillingMenu();
                    break;
                case 3:
                    showCustomersMenu();
                    break;
                case 4:
                    showProfileMenu();
                    break;
                case 5:
                    showStaffMenu();
                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        } while (option == -1);

    }


    /**
     * Menu showing the inventory
     */
    private static void showInventoryMenu()
    {
        divider();
        System.out.println("1) Items\n" +
                "2) Item Stock\n" +
                "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    showModifyItemsMenu();
                    break;
                case 2:
                    showModifyStockMenu();
                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        } while (option == -1);
    }

    /**
     * Menu for adding/checking new bills.
     */
    private static void showBillingMenu()
    {
        divider();
        System.out.println("1) Add new bill\n" +
                "2) Check bill\n" +
                "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    printBillingAdd();
                    break;
                case 2:
                    printShowBilling();
                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        } while (option == -1);
    }

    /**
     * Managing customer's information.
     */
    private static void showCustomersMenu()
    {
        divider();
        System.out.println("1) Add customer\n" +
                "2) Check customer details\n" +
                "3) Modify Customer\n" +
                "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    printCustomerAdd();
                    break;
                case 2:
                    printCustomerCheck();
                    break;
                case 3:
                    printCustomerModify();
                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        } while (option == -1);
    }

    /**
     * Edits current staff information.
     */
    private static void showProfileMenu()
    {
        printProfileEdit();
    }

    /**
     * If you are an admin, you have the authority to add/remove staffs.
     */
    private static void showStaffMenu()
    {
        if (!currentStaff.getIsAdmin()) {
            System.out.println("You do not have permission to access this.");
            mainMenu();
        }

        divider();
        System.out.println("1) Add Staff\n" +
                "2) Remove staff\n" +
                "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    printStaffAdd();
                    break;
                case 2:
                    printStaffRemove();
                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        } while (option == -1);
    }

    /**
     * Menu for managing items.
     */
    private static void showModifyItemsMenu()
    {
        divider();
        System.out.println("1) Get item details \n" +
                "2) Add item\n" +
                "3) Remove item\n" +
                "4) Edit item\n" +
                "0) Go back");
        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    showInventoryMenu();
                    break;
                case 1:
                    printItemDetails();
                    break;
                case 2:
                    printItemAdd();
                    break;
                case 3:
                    printItemRemove();
                    break;
                case 4:
                    printItemEdit();
                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        } while (option == -1);

    }

    /**
     * Menu for managing stock.
     */
    private static void showModifyStockMenu()
    {
        divider();
        System.out.println("1) Edit stock of item\n" +
                "2) Check stock\n" +
                "3) List out of stock items\n" +
                "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    showInventoryMenu();
                    break;
                case 1:
                    printStockEdit();
                    break;
                case 2:
                    printStockCheck();
                    break;
                case 3:
                    printStockOutOfStock();
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        } while (option == -1);
    }

    /**
     * Displays the item details, when you enter the item ID.
     */
    private static void printItemDetails()
    {
        System.out.print("Enter ID of item to be retrieved: ");
        int code = input.nextInt();
        Item i = Items.loadItem(code);
        if(i == null)
        {
            System.out.println("Invalid item.");
            showModifyItemsMenu();
        }
        System.out.printf("Item name: %s\nItem price: %s\nItem cost: %s\n", i.getName(), currencyFormat(i.getPrice()), currencyFormat(i.getCost()));

        showModifyItemsMenu();
    }

    /**
     * Adding new items.
     */
    private static void printItemAdd()
    {
        System.out.println("Creating new item: ");
        int code;
        String name;
        double price;
        double cost;
        int stockAvailable;

        System.out.print("Enter item code: ");
        code = input.nextInt();

        if(Items.loadItem(code) != null)
        {
            System.out.println("Item code already exist.");
            waitForEnter();
            showModifyItemsMenu();
        }

        System.out.print("Enter item name:");
        name = input.next();

        System.out.print("Enter item price: ");
        price = input.nextDouble();

        System.out.print("Enter item cost: ");
        cost = input.nextDouble();

        System.out.print("Enter item stock quantity: ");
        stockAvailable = input.nextInt();

        Items.addItem(code, name, price, cost, stockAvailable);

        System.out.println("Item Created.");

        waitForEnter();
        showModifyItemsMenu();
    }

    /**
     * Method used to remove items by entering the item ID.
     */
    private static void printItemRemove()
    {
        System.out.println("Enter ID of item to be removed: ");
        int code = input.nextInt();
        Items.removeItem(code);
        System.out.println("Item Removed");

        showModifyItemsMenu();
    }

    /**
     * Updating item information.
     */
    private static void printItemEdit()
    {
        System.out.print("Enter code of item to be edit: ");
        int code = input.nextInt();
        String name;
        double price;
        double cost;

        Item i = Items.loadItem(code);
        if(i == null)
        {
            System.out.println("Invalid item.");
            showModifyItemsMenu();
        }
        System.out.println("Modifying item " + code + " (Enter 0 to not change value)");

        System.out.print("Enter item name [" + i.getName() + "]: ");
        name = input.next();
        name = name.equals("0") ? i.getName() : name;

        System.out.print("Enter item price [" + i.getPrice() + "]:");
        price = input.nextDouble();
        price = price == 0 ? i.getPrice() : price;

        System.out.print("Enter item cost [" + i.getCost() + "]:");
        cost = input.nextDouble();
        cost = cost == 0 ? i.getCost() : cost;

        i.setName(name);
        i.setPrice(price);
        i.setCost(cost);
        Items.editItem(i);

        System.out.println("Item Updated.");

        showModifyItemsMenu();
    }

    /**
     * Updating Stock information.
     */
    private static void printStockEdit()
    {
        System.out.println();
        System.out.print("Enter ID of item to be updated: ");
        int code = input.nextInt();
        System.out.print("Enter stock to add amount: ");
        int addStock = input.nextInt();
        Items.updateStock(code, addStock);
        System.out.print("Stock updated. ");

        showModifyStockMenu();
    }

    /**
     * Checking stock information.
     */
    private static void printStockCheck()
    {
        System.out.print("Enter ID of item to be checked: ");
        int code = input.nextInt();
        Item i = Items.loadItem(code);
        System.out.printf("Item name: %s\nItem stock: %s", i.getName(), i.getStockAvailable());

        showModifyStockMenu();
    }

    /**
     * Check and List down all items that are not in stock or are in negative quantity.
     */
    private static void printStockOutOfStock()
    {
        divider();
        ArrayList<Item> itemArrayList = Items.outOfStock();
        if(itemArrayList.size() > 0)
        {
            System.out.println("Code \t Name \t Price \t Qty");
            for(Item i : itemArrayList)
            {
                System.out.println(i.getCode() + " \t " + i.getName() + " \t " + i.getPrice() + " \t " + i.getStockAvailable());
            }
            waitForEnter();
        }
        else
        {
            System.out.println("All items are in stock.");
        }
        showModifyItemsMenu();
    }

    /**
     *Calculates the Grand total for a specific customer.
     */
    private static void printBillingAdd()
    {
        divider();
        int customerID;
        int itemCode, itemQty;
        double discount;

        ArrayList<Item> itemArrayList = new ArrayList<>();
        ArrayList<Integer> itemQtys = new ArrayList<>();

        Customer customer = null;
        do {
            System.out.print("Enter customer ID (0 for Guest): ");
            customerID = input.nextInt();
            customer = Customers.loadCustomer(customerID);
        } while (customer == null);


        do {
            System.out.print("Enter the item code: (Enter 0 to continue)");
            itemCode = input.nextInt();

            if (itemCode != 0) {
                Item item = Items.loadItem(itemCode); //Load item to check if code exists
                if (item != null) {
                    itemArrayList.add(item);
                } else {
                    System.out.println("Invalid item code.");
                    continue;
                }
            } else {
                System.out.println();
                break;
            }

            System.out.print("Enter the item qty:");
            itemQty = input.nextInt();
            itemQtys.add(itemQty);

        } while (itemCode != 0);

        if(itemArrayList.size() == 0)
        {
            System.out.println("No Items were provided.");
            waitForEnter();
            showBillingMenu();
        }

        System.out.print("Enter discount percentage: ");
        discount = input.nextDouble();

        int[] itemQtyArr = new int[itemQtys.size()];
        for (int i = 0; i < itemQtyArr.length; i++) {
            itemQtyArr[i] = itemQtys.get(i);
        }

        Order o = Orders.addOrder(itemArrayList, itemQtyArr, customerID, Integer.valueOf(currentStaff.getID()), discount);
        divider();
        System.out.println("Order ID " + o.getID());
        System.out.println("Total amount: \t" + currencyFormat(o.getTotal()));
        System.out.println("Discount: \t" + currencyFormat(o.getDiscount()));
        System.out.println("Grand total: \t" + currencyFormat(o.getGrand_total()));

        waitForEnter();

        showBillingMenu();
    }

    /**
     * Method used to display the Grand total for a specific customer when the Item code is entered.
     */
    private static void printShowBilling()
    {
        divider();
        int order_id;
        System.out.println("Enter order ID: ");
        order_id = input.nextInt();
        Order o = Orders.loadOrder(order_id);

        if(o == null)
        {
            System.out.println("\nInvalid Order ID.");
        }
        else
        {
            System.out.println("Customer ID: " + o.getCustomer_id());
            ArrayList<Item> itemArrayList = o.getItems();
            for (int i = 0; i < itemArrayList.size(); i++) {
                System.out.println(itemArrayList.get(i).getName() + "\t" + currencyFormat(itemArrayList.get(i).getPrice() * o.getItem_qty()[i]));
                System.out.println("\t" + o.getItem_qty()[i] + " x " + currencyFormat(itemArrayList.get(i).getPrice()));
                System.out.println("--------------------------------");
            }
            System.out.println("Total amount: \t" + currencyFormat(o.getTotal()));
            System.out.println("Discount: \t" + currencyFormat(o.getDiscount()));
            System.out.println("Grand total: \t" + currencyFormat(o.getGrand_total()));
        }
        waitForEnter();
        showBillingMenu();
    }

    /**
     * Adding new customers to the database.
     */
    private static void printCustomerAdd()
    {
        divider();
        System.out.println("Creating new customer: ");
        int code;
        String First_name, Last_name, mobile_number;

        System.out.print("Enter customer code: ");
        code = input.nextInt();

        Customer c = Customers.loadCustomer(code);
        if(c != null)
            System.out.println("Customer ID already exists.");
        else {
            System.out.print("Enter customer first name: ");
            First_name = input.next();

            System.out.print("Enter customer last name: ");
            Last_name = input.next();

            System.out.print("Enter customer contact number: ");
            mobile_number = input.next();

            Customers.addCustomer(code, First_name, Last_name, mobile_number);

            System.out.println("Customer details added.");
        }
        showCustomersMenu();
    }

    /**
     * Displays the customer information.
     */
    private static void printCustomerCheck()
    {
        divider();
        System.out.print("Enter customer ID: ");
        int code = input.nextInt();
        Customer c = Customers.loadCustomer(code);
        System.out.printf("Customer name: %s %s\nCustomer contact number: %s", c.getFirst_name(), c.getLast_name(), c.getMobile_number());

        waitForEnter();
        showCustomersMenu();
    }

    /**
     * Edits customer profile.
     */
    private static void printCustomerModify()
    {

        System.out.print("\nEnter Customer ID: ");
        int code = input.nextInt();
        String First_name, Last_name, mobile_number;

        Customer c = Customers.loadCustomer(code);

        System.out.println("Modifying customer " + code + " (Enter 0 to not change value)");

        System.out.print("Enter customer first name [" + c.getFirst_name() + "]: ");
        First_name = input.next();
        First_name = First_name.equals("0") ? c.getFirst_name() : First_name;

        System.out.print("Enter customer last name [" + c.getLast_name() + "]: ");
        Last_name = input.next();
        Last_name = Last_name.equals("0") ? c.getLast_name() : Last_name;

        System.out.print("Enter customer contact number [" + c.getMobile_number() + "]: ");
        mobile_number = input.next();
        mobile_number = mobile_number.equals("0") ? c.getMobile_number() : mobile_number;

        c.setFirst_name(First_name);
        c.setLast_name(Last_name);
        c.setMobile_number(mobile_number);
        Customers.editCustomer(c);

        System.out.println("Customer details updated. ");

        showCustomersMenu();
    }

    /**
     * Edits staff profile.
     */
    private static void printProfileEdit()
    {
        String first_name;
        String last_name;
        String password;

        System.out.println("Editing profile (Enter 0 to not change value)");

        System.out.print("Enter first name [" + currentStaff.getFirst_name() + "]: ");
        first_name = input.next();
        first_name = first_name.equals("0") ? currentStaff.getFirst_name() : first_name;

        System.out.print("Enter last name [" + currentStaff.getLast_name() + "]: ");
        last_name = input.next();
        last_name = last_name.equals("0") ? currentStaff.getLast_name() : last_name;

        System.out.print("Enter password [" + currentStaff.getPassword() + "]: ");
        password = input.next();
        password = password.equals("0") ? currentStaff.getPassword() : password;

        currentStaff.setFirst_name(first_name);
        currentStaff.setLast_name(last_name);
        currentStaff.setPassword(password);

        Staffs.editStaff(currentStaff);

        System.out.println("Profile details updated. ");

        mainMenu();
    }

    /**
     * Adding a new staff.
     */
    private static void printStaffAdd()
    {
        int id;
        String first_name;
        String last_name;
        String password;
        Boolean isAdmin;

        divider();

        System.out.print("Enter Staff ID: ");
        id = input.nextInt();
        System.out.print("Enter first name: ");
        first_name = input.next();
        System.out.print("Enter last name: ");
        last_name = input.next();
        System.out.print("Enter password: ");
        password = input.next();
        System.out.print("Is this user an admin? (Y/N) ");
        char value = input.next().charAt(0);
        isAdmin = String.valueOf(value).toLowerCase().equals("y");


        Staffs.addStaff(id, first_name, last_name, password, isAdmin);

        System.out.println("Staff created");

        showStaffMenu();

    }

    /**
     * Function for removing staff.
     */
    private static void printStaffRemove()
    {
        System.out.print("Enter ID of Staff to be removed: ");
        int id = input.nextInt();
        Staffs.removeStaff(id);
        System.out.println("Staff removed");
        showStaffMenu();

    }

    private static void divider()
    {
        System.out.println("\n");
        System.out.println("======================================================================================");
        System.out.println();
    }

    /**
     * A function that waits for the user to press enter.
     */
    private static void waitForEnter()
    {
        System.out.println("\n\nPress enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A simple function that takes in a double and prints it out currency format.
     * @param amount The amount that needs to be printed out
     * @return String that can be displayed to the user.
     */
    private static String currencyFormat(double amount)
    {
        return currencySymbol + String.format("%.2f", amount);
    }

    /**
     * Terminates the program.
     */
    private static void exit()
    {
        System.exit(0);
    }
}
