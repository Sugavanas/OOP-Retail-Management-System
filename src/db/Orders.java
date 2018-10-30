package db;

import classes.FileIO;
import object.Order;

import java.util.Date;

public class Orders {
    private static FileIO<Order> file = new FileIO<>("items.dat", Order.class);

    private static Date getDate()
    {
        return new Date();
    }
}
