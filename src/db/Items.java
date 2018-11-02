package db;

import classes.FileIO;
import object.Item;

import java.util.ArrayList;

public class Items
{
    private static FileIO<Item> file = new FileIO<>("items.dat", Item.class);

    public static void addItem(int code, String name, double price, double cost, int stockAvaiable)
    {
        Item item = new Item(code, name, price, cost, stockAvaiable);

        file.insert(item);
    }

    public static void removeItem(int code)
    {
        Item i = file.getByID(code);
        file.delete(i);
    }

    public static void editItem(Item i)
    {
        file.update(i);
    }

    public static Item loadItem(int code)
    {
        return file.getByID(code);
    }

    public static int getStock(int id)
    {
        return file.getByID(id).getStockAvailable();
    }

    public static void updateStock(int id, int addStock)
    {
        Item i = file.getByID(id);

        i.setStockAvailable(i.getStockAvailable() + addStock);

        file.update(i);
    }
}
