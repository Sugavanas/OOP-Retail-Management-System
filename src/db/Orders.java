package db;

import classes.FileIO;
import object.Item;
import object.Order;

import java.util.ArrayList;
import java.util.Date;

public class Orders {
    private static FileIO<Order> file = new FileIO<>("orders.dat", Order.class);

    public static Order addOrder(int[] item_codes, int[] item_qty, int customer_id, int staff_id, double discount_percentage)
    {
        double total = 0;
        for (int i = 0; i<item_codes.length; i++){
            Item item = Items.loadItem(item_codes [i]);
            total += item.getPrice() * item_qty [i];
        }
        double discount = (discount_percentage/100)*total;
        double grand_total = total - discount;
        Order order = new Order(getNextID(), item_codes, item_qty, customer_id, staff_id, getDate(), total, discount, grand_total);

        file.insert(order);

        return order;
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

    public static Order loadOrder(int id)
    {
        return file.getByID(id);
    }

}
