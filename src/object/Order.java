package object;

import classes.Objects;
import db.Items;

import java.util.ArrayList;
import java.util.Date;

public class Order extends Objects
{
    private int id;

    private int[] item_codes;

    private int customer_id;

    private int staff_id;

    private Date date;

    private double total;

    private double discount;

    private double grand_total;

    public Order(String data)
    {
        super(data);
    }

    @Override
    public void loadFromString(String data)
    {

    }

    @Override
    public String saveString()
    {
        return null;
    }

    @Override
    public String getID()
    {
        return String.valueOf(id);
    }

    public ArrayList<Item> getItems()
    {
        ArrayList<Item> itemArrayList = new ArrayList<>();

       for(int i = 0; i < item_codes.length; i++)
       {
           itemArrayList.add(Items.loadItem(item_codes[i]));
       }

       return itemArrayList;
    }

    public void setItems(ArrayList<Item> itemArrayList)
    {
        int[] item_codes = new int[itemArrayList.size()];
        int i = 0;
        for (Item item : itemArrayList)
        {
            item_codes[i++] = Integer.valueOf(item.getID());
        }
        this.item_codes = item_codes;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public int getStaff_id()
    {
        return staff_id;
    }

    public void setStaff_id(int staff_id)
    {
        this.staff_id = staff_id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public double getGrand_total()
    {
        return grand_total;
    }

    public void setGrand_total(double grand_total)
    {
        this.grand_total = grand_total;
    }
}
