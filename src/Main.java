import com.sun.org.apache.xpath.internal.operations.Bool;
import db.Customers;
import db.Items;
import db.Staffs;
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
         * 2) Add itee
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

        System.out.print("Enter Staff Name: ");
        name = input.next();
        System.out.print("Enter Password: ");
        password = input.next();

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

        System.out.println("Welcome, enter login details below: ");
        do {
            System.out.print("Staff ID: ");
            id = input.nextInt();

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

    }

    public static void divider()
    {
        System.out.println("\n\n");
        System.out.println("======================================================================================");
        System.out.println("\n\n");
    }
}
