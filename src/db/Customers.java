package db;

import classes.FileIO;
import object.Customer;

import java.util.ArrayList;

public class Customers {
    private static FileIO<Customer> file = new FileIO<>("customers.dat", Customer.class);

    public static void addCustomer(int id, String first_name, String last_name, String mobile_number)
    {
        Customer item = new Customer(id, first_name, last_name, mobile_number);

        file.insert(item);

        //For testing
        ArrayList<Customer> itemArrayList = file.read();
        for (Customer c : itemArrayList)
            System.out.println(c.saveString());
    }

    public static Customer loadCustomer(int code)
    {
        return file.getByID(code);
    }

    public static void editCustomer(Customer i)
    {
        file.update(i);
    }
}
