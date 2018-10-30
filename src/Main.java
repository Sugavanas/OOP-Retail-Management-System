import db.Customers;
import db.Items;
import db.Orders;
import db.Staffs;
import object.Customer;
import object.Item;
import object.Order;
import object.Staff;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input;

    static Staff currentStaff;

    public static void main(String[] args)
    {
        input = new Scanner(System.in);

        System.out.println("Retail Management System V1.0");
        if(!Staffs.hasStaffs())
            firstLaunch();
        else
            loginScreen();


        /**
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

    public static void firstLaunch()
    {
        String first_name, last_name, password;

        System.out.println("Welcome, create a new staff login: ");

        System.out.println("Enter Staff First Name: ");
        first_name = input.nextLine();
        System.out.println("Enter Staff Last Name: ");
        last_name = input.nextLine();
        System.out.println("Enter Password: ");
        password = input.nextLine();

        Staffs.addStaff(1, first_name, last_name, password, true);

        System.out.println("Staff created. You can now login using the details below.");
        System.out.println("Staff ID: " + 1);
        System.out.println("Staff Password: " + password);
        divider();
        loginScreen();

    }

    public static void loginScreen()
    {
        int id;
        String password;

        System.out.println("Welcome, enter login details below: (Enter ID as 0 to exit) ");
        do {
            System.out.print("Staff ID: ");
            id = input.nextInt();

            if(id == 0)
                return;

            System.out.print("Password: ");
            password = input.next();

            currentStaff = Staffs.Login(id, password);
            if(currentStaff == null)
                System.out.println("Invalid Login");
        }while(currentStaff == null);

        mainMenu();
    }

    public static void mainMenu()
    {
        divider();
        System.out.println("Retail Management System (RMS) v1.0");
        System.out.println("Main Menu");
        System.out.println("1) Inventory Control\n" +
                            "2) Billing\n" +
                            "3) Customers\n" +
                            "4) Edit my profile\n" +
                            (currentStaff.getIsAdmin() ? "5) Add/Remove staffs\n" : "") +
                            "0) Exit");
        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch(option)
            {
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
        }while(option == -1);

    }

    static void showInventoryMenu()
    {
        divider();
        System.out.println("1) Items\n" +
                            "2) Item Stock\n" +
                            " 0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch(option)
            {
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
        }while(option == -1);
    }

    static void showModifyItemsMenu()
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

            switch(option)
            {
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
        }while(option == -1);

    }

    static void showModifyStockMenu()
    {
        divider();
        System.out.println("1) Edit stock of item\n" +
                           "2) Check stock\n" +
                           "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch(option)
            {
                case 0:
                    showInventoryMenu();
                    break;
                case 1:
                    printStockEdit();
                    break;
                case 2:
                    printStockCheck();
                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        }while(option == -1);
    }

    static void showBillingMenu()
    {
        divider();
        System.out.println("1) Add new bill\n" +
                "2) Check bill\n" +
                "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch(option)
            {
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
        }while(option == -1);
    }

    static void printShowBilling ()
    {
        int order_id;
        System.out.println("Enter order ID: ");
        order_id = input.nextInt();
        Order o = Orders.loadOrder(order_id);

        System.out.println("Customer ID: " + o.getCustomer_id());
        ArrayList<Item> itemArrayList = o.getItems();
        for (int i =0; i < itemArrayList.size(); i++)
        {
            System.out.println("Name: " + itemArrayList.get(i).getName());
            System.out.println("Price: " + itemArrayList.get(i).getPrice());

        }
        System.out.println("Total: " + o.getTotal());
        System.out.println("Discount: " + o.getDiscount() );
        System.out.println("Grand Total: " + o.getGrand_total());

    }

    static void printBillingAdd ()
    {
        int customerID;
        int itemCode, itemQty;
        double discount;

        ArrayList<Integer> itemCodes = new ArrayList<Integer>();
        ArrayList<Integer> itemQtys = new ArrayList<Integer>();

        System.out.print("Enter customer ID (0 for guest: ");
        customerID = input.nextInt();

        do {
            System.out.print("Enter the item code: (Enter 0 to exit)");
            itemCode = input.nextInt();
            if (itemCode != 0) {
                itemCodes.add(itemCode);
            } else
                break;

            System.out.print("Enter the item qty:");
            itemQty = input.nextInt();
            itemQtys.add(itemQty);

        } while (itemCode != 0);

        System.out.print("Enter discount percentage: ");
        discount = input.nextDouble();

        int[] itemCodesArr = new int[itemCodes.size()];
        int[] itemQtyArr = new int[itemQtys.size()];
        for (int i = 0; i < itemCodesArr.length; i++) {
            itemCodesArr[i] = itemCodes.get(i);
            itemQtyArr[i] = itemQtys.get(i);
        }

        Order o = Orders.addOrder(itemCodesArr, itemQtyArr, customerID, Integer.valueOf(currentStaff.getID()), discount);
        divider();
        System.out.println("Order ID " + o.getID());
        System.out.println("Total amount: \tRM " + String.format("%.2f", o.getTotal()));
        System.out.println("Discount: \tRM " + String.format("%.2f",o.getDiscount()));
        System.out.println("Grand total: \tRM " + String.format("%.2f", o.getGrand_total()));
        showBillingMenu();
    }

    static void showCustomersMenu()
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

            switch(option)
            {
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
        }while(option == -1);
    }

    static void showProfileMenu()
    {
        printProfileEdit();
    }

    static void showStaffMenu()
    {
        if(!currentStaff.getIsAdmin()) {
            System.out.println("You do not have permission to access this.");
            mainMenu();
        }
        System.out.println("1) Add Staff\n" +
                "2) Remove staff\n" +
                "0) Go back");

        int option;
        do {
            System.out.print("Choose option: ");
            option = input.nextInt();

            switch(option)
            {
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
        }while(option == -1);
    }

    static void printItemAdd()
    {
        System.out.println("Creating new item: ");
        int code;
        String name;
        double price;
        double cost;
        int stockAvaiable;

        System.out.print("Enter item code: ");
        code = input.nextInt();

        System.out.print("Enter item name:");
        name = input.next();

        System.out.print("Enter item price: ");
        price = input.nextDouble();

        System.out.print("Enter item cost: ");
        cost = input.nextDouble();

        System.out.print("Enter item stock quantity: ");
        stockAvaiable = input.nextInt();

        Items.addItem(code, name, price, cost, stockAvaiable);

        System.out.println("Item Created.");

        showModifyItemsMenu();
    }

    static void printItemRemove()
    {
        System.out.println("Enter ID of item to be removed: ");
        int code = input.nextInt();
        Items.removeItem(code);

        showModifyItemsMenu();
    }

    static void printItemEdit()
    {
        System.out.print("Enter code of item to be edit: ");
        int code = input.nextInt();
        String name;
        double price;
        double cost;



        Item i = Items.loadItem(code);

        System.out.println("Modifying item " + code + " (Enter 0 to not change value)");

        System.out.print("Enter item name [" + i.getName() +"]: ");
        name = input.next();
        name = name.equals("0") ? i.getName() : name;

        System.out.print("Enter item price [" + i.getPrice() +"]:");
        price = input.nextDouble();
        price = price == 0 ? i.getPrice() : price;

        System.out.print("Enter item cost [" + i.getCost() +"]:");
        cost = input.nextDouble();
        cost = cost == 0 ? i.getCost() : cost;

        i.setName(name);
        i.setPrice(price);
        i.setCost(cost);
        Items.editItem(i);

        System.out.println("Item Updated.");

        showModifyItemsMenu();
    }

    static void printItemDetails()
    {
        System.out.println("Enter ID of item to be retrieved: ");
        int code = input.nextInt();
        Item i = Items.loadItem(code);

        System.out.printf("Item name: %s\nItem price: %s\nItem cost: %s\n" , i.getName(), String.format("%.2f", i.getPrice()), String.format("%.2f", i.getCost()));

        showModifyItemsMenu();
    }

    static void printStaffAdd()
    {
     int id;
     String first_name;
     String last_name;
     String password;
     Boolean isAdmin;

     System.out.print("Enter Staff id: ");
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

    static void printStaffRemove()
    {
        System.out.print("Enter ID of Staff to be removed: ");
        int id = input.nextInt();
        Staffs.removeStaff(id);
        System.out.println("Staff removed");
        showStaffMenu();

    }


    static void printStockEdit()
    {
        System.out.print("Enter ID of item to be updated: ");
        int code = input.nextInt();
        System.out.print("Enter new stock amount: ");
        int newStock = input.nextInt();
        Items.updateStock(code, newStock);
        System.out.print("Stock updated. ");

        showModifyStockMenu();
    }

    static void printStockCheck()
    {
        System.out.print("Enter ID of item to be checked: ");
        int code = input.nextInt();
        Item i = Items.loadItem(code);
        System.out.printf("Item name: %s\nItem stock: %s", i.getName(), i.getStockAvailable());

        showModifyStockMenu();
    }

    static void printCustomerAdd()
    {
        System.out.println("Creating new customer: ");
        int code;
        String First_name, Last_name, mobile_number;

        System.out.print("Enter customer code: ");
        code = input.nextInt();

        System.out.print("Enter customer first name: ");
        First_name = input.next();

        System.out.print("Enter customer last name: ");
        Last_name = input.next();

        System.out.print("Enter customer contact number: ");
        mobile_number = input.next();

        Customers.addCustomer(code, First_name, Last_name, mobile_number);

        System.out.println("Customer details added.");

        showCustomersMenu();
    }

    static void printCustomerCheck()
    {
        System.out.print("Enter customer ID: ");
        int code = input.nextInt();
        Customer c = Customers.loadCustomer(code);
        System.out.printf("Customer name: %s %s\nCustomer contact number: %s", c.getFirst_name(), c.getLast_name(), c.getMobile_number());

        showCustomersMenu();
    }

    static void printCustomerModify()
    {
        System.out.print("Enter customer ID: ");
        int code = input.nextInt();
        String First_name, Last_name, mobile_number;

        Customer c = Customers.loadCustomer(code);

        System.out.println("Modifying customer " + code + " + (Enter 0 to not change value)");

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

    public static void printProfileEdit()
    {
        String first_name;
        String last_name;
        String password;

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

    public static void divider()
    {
        System.out.println("\n");
        System.out.println("======================================================================================");
        System.out.println();
    }

    static void exit()
    {
        System.exit(0);
    }
}
