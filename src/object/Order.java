package object;

import classes.Objects;
import db.Items;

import java.util.ArrayList;
import java.util.Date;

public class Order extends Objects
{
    private int id;

    private int[] item_codes;
    private int[] item_qty;

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

    public Order(int id, int[] item_codes, int[] item_qty, int customer_id, int staff_id, Date date, double total, double discount, double grand_total)
    {
        this.id = id;
        this.item_codes = item_codes;
        this.item_qty = item_qty;
        this.customer_id = customer_id;
        this.staff_id = staff_id;
        this.date = date;
        this.total = total;
        this.discount = discount;
        this.grand_total = grand_total;
    }

    @Override
    public void loadFromString(String data)
    {
        try {
            String[] parts = data.split(";");
            this.id = Integer.valueOf(parts[0]);

            String[] strCodes = parts[1].split(",");
            this.item_codes = new int[strCodes.length];
            for(int i = 0; i<strCodes.length; i++)
                this.item_codes[i] = Integer.valueOf(strCodes[i]);

            String[] strQty = parts[2].split(",");
            this.item_qty = new int[strQty.length];
            for(int i = 0; i<strQty.length; i++)
                this.item_qty[i] = Integer.valueOf(strQty[i]);

            this.customer_id = Integer.valueOf(parts[3]);

            this.staff_id = Integer.valueOf(parts[4]);

            this.date = new Date(Long.valueOf(parts[5]));

            this.total = Double.valueOf(parts[6]);

            this.discount = Double.valueOf(parts[7]);

            this.grand_total = Double.valueOf(parts[8]);
        } catch (Exception ex) {
            System.out.println("Error Loading."); //TODO log error
        }
    }

    @Override
    public String saveString()
    {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;", String.valueOf(id), getItemIds(), getItemQty(), String.valueOf(customer_id),
                String.valueOf(staff_id), String.valueOf(date.getTime()), String.valueOf(total),
                String.valueOf(discount), String.valueOf(grand_total));
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

    public int[] getItem_qty()
    {
        return item_qty;
    }

    public void setItem_qty(int[] item_qty)
    {
        this.item_qty = item_qty;
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

    private String getItemIds()
    {
        String result = "";
        for(int i : item_codes)
        {
            result += String.valueOf(i) + ",";
        }
        result = result.substring(0, result.length() - 1); //remove last comma

        return result;
    }

    private String getItemQty()
    {
        String result = "";
        for(int i : item_qty)
        {
            result += String.valueOf(i) + ",";
        }
        result = result.substring(0, result.length() - 1); //remove last comma

        return result;
    }
}
