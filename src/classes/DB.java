package classes;

/**
 * Abstract class for db methods.
 */
public abstract class DB
{
    public DB(String data)
    {
        loadFromString(data);
    }

    public abstract void loadFromString(String data);

    public abstract Object getByID(int ID);
}
