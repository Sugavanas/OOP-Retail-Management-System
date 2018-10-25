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

        //For testing
        ArrayList<Item> itemArrayList = file.read();
        for (Item i : itemArrayList)
            System.out.println(i.saveString());
    }

    public static void removeItem(int code)
    {

    }

    public static void updateItem(Item i)
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

    public static void updateStock(int id, int newStock)
    {
        Item i = file.getByID(id);
        i.setStockAvailable(newStock);
        file.update(i);
    }
}
