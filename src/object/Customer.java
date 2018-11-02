package object;

import classes.Objects;

public class Customer extends Objects
{
    private int id;

    private String first_name;
    private String last_name;

    private String mobile_number;

    public Customer(String data)
    {
        super(data);
    }

    /**
     * Constructor
     * @param id
     * @param first_name
     * @param last_name
     * @param mobile_number
     */
    public Customer(int id, String first_name, String last_name, String mobile_number)
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_number = mobile_number;
    }

    /**
     * Split string and load the data into existing variables.
     * @param data
     */
    @Override
    public void loadFromString(String data)
    {
        try {
            String[] parts = data.split(";");
            this.id = Integer.valueOf(parts[0]);
            this.first_name = parts[1];
            this.last_name = parts[2];
            this.mobile_number = parts[3];
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
        return String.format("%s;%s;%s;%s;", String.valueOf(id), first_name, last_name, mobile_number);
        //this is same as printing it like this
        // return id + ";" + first_name + ";" + last_name + ";" + mobile_number;
    }

    @Override
    public String getID()
    {
        return String.valueOf(id);
    }

    /*
     * Get Set methods
     */

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getMobile_number()
    {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number)
    {
        this.mobile_number = mobile_number;
    }
}
