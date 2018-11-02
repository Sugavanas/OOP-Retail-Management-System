package db;

import classes.FileIO;
import object.Customer;

public class Customers
{
    private static FileIO<Customer> file = new FileIO<>("customers.dat", Customer.class);

    public static void addCustomer(int id, String first_name, String last_name, String mobile_number)
    {
        Customer item = new Customer(id, first_name, last_name, mobile_number);

        file.insert(item);
    }

    public static Customer loadCustomer(int id)
    {
        if (id == 0)
            return new Customer(0, "Guest", "", "");
        else
            return file.getByID(id);
    }

    public static void editCustomer(Customer i)
    {
        file.update(i);
    }
}
