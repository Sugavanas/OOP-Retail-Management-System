import db.Items;
import db.Staffs;
import object.Item;
import object.Staff;

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
        String name, password;

        System.out.println("Welcome, create a new staff login: ");

        System.out.println("Enter Staff Name: ");
        name = input.nextLine();
        System.out.println("Enter Password: ");
        password = input.nextLine();

        Staffs.addStaff(1, name, password, true);

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

                    break;
                case 2:

                    break;
                default:
                    System.out.println("Invalid option.");
                    option = -1;
                    break;
            }
        }while(option == -1);
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

                    break;
                case 2:

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

    }

    static void showStaffMenu()
    {
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

                    break;
                case 2:

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

    public static void printItemRemove()
    {
        System.out.println("Enter ID of item to be removed: ");
        int code = input.nextInt();
        Items.removeItem(code);

        showModifyItemsMenu();
    }

    public static void printItemDetails()
    {
        System.out.println("Enter ID of item to be added: ");
        int code = input.nextInt();
        Item i = Items.loadItem(code);

        System.out.printf("Item name: %s\nItem price: %s\nItem cost: %s\n" , i.getName(), String.format("%.2f", i.getPrice()), String.format("%.2f", i.getCost()));

        showModifyItemsMenu();
    }

    public static void printStockEdit()
    {
        System.out.print("Enter ID of item to be updated: ");
        int code = input.nextInt();
        System.out.print("Enter new stock amount: ");
        int newStock = input.nextInt();
        Items.updateStock(code, newStock);
        System.out.print("Stock updated. ");

        showModifyStockMenu();
    }

    public static void printStockCheck()
    {
        System.out.print("Enter ID of item to be checked: ");
        int code = input.nextInt();
        Item i = Items.loadItem(code);
        System.out.printf("Item name: %s\nItem stock: %s", i.getName(), i.getStockAvailable());

        showModifyStockMenu();
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
