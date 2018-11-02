package db;

import classes.FileIO;
import object.Item;

import java.util.ArrayList;

public class Items
{
    /**
     * An instance of File IO class.
     */
    private static FileIO<Item> file = new FileIO<>("items.dat", Item.class);

    /**
     * Adding Item information.
     * @param code
     * @param name
     * @param price
     * @param cost
     * @param stockAvaiable
     */
    public static void addItem(int code, String name, double price, double cost, int stockAvaiable)
    {
        Item item = new Item(code, name, price, cost, stockAvaiable);

        file.insert(item);
    }

    /**
     * Removes Item by Id.
     * @param code
     */
    public static void removeItem(int code)
    {
        Item i = file.getByID(code);
        file.delete(i);
    }

    /**
     * Edditing item information.
     * @param i
     */
    public static void editItem(Item i)
    {
        file.update(i);
    }

    /**
     * Loads Item by ID.
     * @param code
     * @return
     */
    public static Item loadItem(int code)
    {
        return file.getByID(code);
    }

    /**
     * Gets Stock by ID.
     * @param id
     * @return
     */
    public static int getStock(int id)
    {
        return file.getByID(id).getStockAvailable();
    }

    /**
     * Updates stock by ID.
     * @param id
     * @param addStock
     */
    public static void updateStock(int id, int addStock)
    {
        Item i = file.getByID(id);

        i.setStockAvailable(i.getStockAvailable() + addStock);

        file.update(i);
    }
}
