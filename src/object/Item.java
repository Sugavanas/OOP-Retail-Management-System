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
public class Item extends Objects
{
    private int code;

    private String name;

    private double price;

    private double cost;

    private int stockAvailable;

    public Item(String data)
    {
        super(data);
    }

    /**
     * Constructor
     * @param code
     * @param name
     * @param price
     * @param cost
     * @param stockAvailable
     */
    public Item(int code, String name, double price, double cost, int stockAvailable)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.stockAvailable = stockAvailable;
    }

    /**
     * Split string and load data into existing variables
     * @param data
     */
    @Override
    public void loadFromString(String data)
    {
        try {
            String[] parts = data.split(";");
            this.code = Integer.valueOf(parts[0]);
            this.name = parts[1];
            this.price = Double.valueOf(parts[2]);
            this.cost = Double.valueOf(parts[3]);
            this.stockAvailable = Integer.valueOf(parts[4]);
        } catch (Exception ex) {
            System.out.println("Error Loading."); //TODO log error
        }
    }

    /**
     * Returns a string for saving
     * @return
     */
    @Override
    public String saveString()
    {
        return String.format("%s;%s;%s;%s;%s;", String.valueOf(code), name, String.valueOf(price), String.valueOf(cost), String.valueOf(stockAvailable));
    }

    @Override
    public String getID()
    {
        return String.valueOf(code);
    }

    /**
     * Get Set methods
     */

    public int getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public int getStockAvailable()
    {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable)
    {
        this.stockAvailable = stockAvailable;
    }

}
