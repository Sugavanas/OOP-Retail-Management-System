package db;

import classes.FileIO;
import object.Customer;

public class Customers
{
    /**
     * An instance of File IO class.
     */
    private static FileIO<Customer> file = new FileIO<>("customers.dat", Customer.class);

    /**
     * Adding customer information.
     * @param id
     * @param first_name
     * @param last_name
     * @param mobile_number
     */
    public static void addCustomer(int id, String first_name, String last_name, String mobile_number)
    {
        Customer item = new Customer(id, first_name, last_name, mobile_number);

        file.insert(item);
    }

    /**
     * Load customer by ID.
     * @param id Id of the customer
     * @return
     */
    public static Customer loadCustomer(int id)
    {
        if (id == 0)
            return new Customer(0, "Guest", "", "");
        else
            return file.getByID(id);
    }

    /**
     * Edits customer information.
     * @param i
     */
    public static void editCustomer(Customer i)
    {
        file.update(i);
    }
}
