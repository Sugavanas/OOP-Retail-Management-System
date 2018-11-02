package classes;

/**
 * Abstract class for db methods.
 */
public abstract class Objects
{
    public Objects() {

    }

    /**
     * This constructor is used by FileIO to create the objects
     *
     * @param data Read line in string format.
     */
    public Objects(String data)
    {
        loadFromString(data);
    }

    /**
     * This method is used by the constructor to load data from string
     * @param data
     */
    public abstract void loadFromString(String data);

    /**
     * This method is used by the constructor to save data from string
     * @return
     */
    public abstract String saveString();

    public abstract String getID();
}
