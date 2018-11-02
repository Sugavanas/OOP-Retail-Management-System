package object;

import classes.Objects;

public class Staff extends Objects
{
    private int id;

    private String first_name;

    private String last_name;

    private String password;

    private Boolean isAdmin;

    public Staff(String data)
    {
        super(data);
    }

    /**
     * Constructor
     * @param id
     * @param first_name
     * @param last_name
     * @param password
     * @param isAdmin
     */
    public Staff(int id, String first_name, String last_name, String password, Boolean isAdmin)
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.isAdmin = isAdmin;
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
            this.id = Integer.valueOf(parts[0]);
            this.first_name = parts[1];
            this.last_name = parts[2];
            this.password = parts[3];
            this.isAdmin = (parts[4]).equals("1");
        } catch (Exception ex) {
            System.out.println("Error Loading."); //TODO log error
        }
    }

    /**
     * Returns string for saving
     * @return
     */
    @Override
    public String saveString()
    {
        return String.format("%s;%s;%s;%s;%s;", String.valueOf(id), first_name, last_name, password, (isAdmin ? "1" : "0"));
    }

    @Override
    public String getID()
    {
        return String.valueOf(id);
    }
    /*
    get set methods
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Boolean getIsAdmin()
    {
        return isAdmin;
    }
}
