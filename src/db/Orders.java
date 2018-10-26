package db;

import classes.FileIO;
import object.Order;

public class Orders {
    private static FileIO<Order> file = new FileIO<>("items.dat", Order.class);
}
