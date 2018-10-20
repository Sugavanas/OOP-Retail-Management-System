package object;

import classes.Objects;

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
public class Item extends Objects {
    public int code;

    public String name;

    public double price;

    public double cost;

    public Item(String data)
    {
        super(data);
        try
        {
            String[] parts = data.split(";");
            this.code = Integer.valueOf(parts[0]);
            this.name = parts[1];
            this.price = Double.valueOf(parts[2]);
            this.cost = Double.valueOf(parts[3]);
        }
        catch(Exception ex)
        {
            System.out.println("Error Loading."); //TODO log error
        }
    }

    public Item(int code, String name, double price, double cost)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        this.cost = cost;
    }

    @Override
    public void loadFromString(String data) {
        //Just to test it for now
        code = 1;
        name = data;
    }

    @Override
    public String saveString() {
        return String.format("%s;%s;%s;%s;", String.valueOf(code), name, String.valueOf(price), String.valueOf(cost));
    }

    @Override
    public String getID() {
        return String.valueOf(code);
    }
}
