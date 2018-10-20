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

    public abstract void loadFromString(String data);

    public abstract String saveString();

    public abstract String getID();
}
