package db;

import classes.FileIO;
import object.Order;

import java.util.Date;

public class Orders {
    private static FileIO<Order> file = new FileIO<>("items.dat", Order.class);

    public static void addOrder(int[] item_codes, int customer_id, int staff_id, double discount_percentage)
    {
        Order order = new Order(id, item_codes, customer_id, staff_id, getDate(), total, discount, grand_total);

        file.insert(order);
    }

    private static Date getDate()
    {
        return new Date();
    }
}
