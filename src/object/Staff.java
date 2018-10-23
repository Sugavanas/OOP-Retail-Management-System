package object;

import classes.Objects;

public class Staff extends Objects
{
    private int id;

    private String name;

    private String password;

    private Boolean isAdmin;

    public Staff(String data)
    {
        super(data);
    }

    public Staff(int id, String name, String password, Boolean isAdmin)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public void loadFromString(String data)
    {
        try {
            String[] parts = data.split(";");
            this.id = Integer.valueOf(parts[0]);
            this.name = parts[1];
            this.password =  parts[2];
            this.isAdmin = (parts[3]).equals("1");
        } catch (Exception ex) {
            System.out.println("Error Loading."); //TODO log error
        }
    }

    @Override
    public String saveString()
    {
        return String.format("%s;%s;%s;%s;", String.valueOf(id), name, password, (isAdmin ? "1" : "0"));
    }

    @Override
    public String getID()
    {
        return String.valueOf(id);
    }

    private String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    private String getPassword()
    {
        return password;
    }

    private void setPassword(String password)
    {
        this.password = password;
    }

    private Boolean getIsAdmin()
    {
        return isAdmin;
    }
}
