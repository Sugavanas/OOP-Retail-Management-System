package db;

import classes.FileIO;
import object.Item;
import object.Order;

import java.util.ArrayList;
import java.util.Date;

public class Orders
{
    private static FileIO<Order> file = new FileIO<>("orders.dat", Order.class);

    public static Order addOrder(ArrayList<Item> itemArrayList, int[] item_qty, int customer_id, int staff_id, double discount_percentage)
    {
        double total = 0;
        for (int i = 0; i < item_qty.length; i++) {
            Item item = itemArrayList.get(i);
            total += item.getPrice() * item_qty[i];

            Items.updateStock(item.getCode(), -(item_qty[i]));
        }
        double discount = (discount_percentage / 100) * total;
        double grand_total = total - discount;
        Order order = new Order(getNextID(), null, item_qty, customer_id, staff_id, getDate(), total, discount, grand_total);
        order.setItems(itemArrayList);
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
        if (orderArrayList.size() > 1)
            return Integer.valueOf(orderArrayList.get(orderArrayList.size() - 1).getID()) + 1;
        else
            return 1;
    }

    public static Order loadOrder(int id)
    {
        return file.getByID(id);
    }

}
