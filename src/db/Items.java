package db;

import classes.FileIO;
import object.Item;

import java.util.ArrayList;

public class Items
{
    private static FileIO<Item> file = new FileIO<>("items.dat", Item.class);

    public static void addItem(int code, String name, double price, double cost)
    {
        Item item = new Item(code, name, price, cost);

        file.insert(item);

        //For testing
        ArrayList<Item> itemArrayList = file.read();
        for (Item i : itemArrayList)
            System.out.println(i.saveString());
    }
}
