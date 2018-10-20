package object;

import classes.DB;

/*
 * Item Object class. Each item instance will have its own object.
 *
 * Current Fields:
 * code - Item barcode
 * name - Item name
 * price - Item price
 * cost - Item cost
 *
 */
public class Item extends DB {
    public int code;

    public String name;

    public double price;

    public double cost;

    public Item(String data)
    {
        super(data);
    }

    @Override
    public void loadFromString(String data) {
        //Just to test it for now
        code = 1;
        name = "test";
    }

    @Override
    public Object getByID(int ID) {
        return null;
    }
}
