package db;

import classes.FileIO;
import object.Order;

import java.util.ArrayList;
import java.util.Date;

public class Orders {
    private static FileIO<Order> file = new FileIO<>("items.dat", Order.class);

    public static void addOrder(int[] item_codes, int[] item_qty, int customer_id, int staff_id, double discount_percentage)
    {
        Order order = new Order(id, item_codes, item_qty, customer_id, staff_id, getDate(), total, discount, grand_total);

        file.insert(order);
    }

    private static Date getDate()
    {
        return new Date();
    }

    private static int getNextID()
    {
        ArrayList<Order> orderArrayList = file.read();
        if(orderArrayList.size() > 1)
            return Integer.valueOf(orderArrayList.get(orderArrayList.size() - 1).getID()) + 1;
        else
            return 1;
    }
}
