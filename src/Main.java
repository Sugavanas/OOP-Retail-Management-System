import db.Customers;
import db.Items;

public class Main {
    public static void main(String[] args)
    {
        Items.addItem(001, "dsd", 15, 6);
        Customers.addCustomer(1, "John", "Doe", "0123456789");
    }
}
