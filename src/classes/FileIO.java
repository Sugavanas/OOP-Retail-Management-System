package classes;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * I/O class for reading/writing to files with basic functions such as getById/insert/update.
 */
public class FileIO<T extends Objects>
{
    /**
     * The directory to store data files
     */
    private static String dataDirectory = System.getProperty("user.dir") + "/data/";

    private String fileName;
    private Class<T> aClass;

    /**
     * Constructor
     * @param fileName Name of file to write/read from
     * @param aClass Any class that extends from Objects class
     */
    public FileIO(String fileName, Class<T> aClass)
    {
        this.fileName = fileName;
        this.aClass = aClass;
    }

    /**
     * Read from file
     *
     * @return ArrayList<T> or null if error
     */
    public ArrayList<T> read()
    {
        //Check if file exists.
        if(!exists(fileName))
            return new ArrayList<>();

        //Initialize the file.
        File file = new File(dataDirectory + fileName);

        try
        {
            Scanner sc = new Scanner(file);

            ArrayList<T> arrayList = new ArrayList<>();

            while (sc.hasNextLine())
            {
                T line = aClass.getConstructor(String.class).newInstance(sc.nextLine());
                arrayList.add(line);
            }

            sc.close();

            return arrayList;
        }
        catch (FileNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException ex)
        {
            System.out.println("ERROR : " + dataDirectory + "  "  + ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Get Object by id.
     * @param id unique id
     * @return T (object instance)
     */
    public T getByID(int id)
    {
        //Check if file exists.
        if(!exists(fileName))
            return null;

        //Initialize the file.
        File file = new File(dataDirectory + fileName);

        try
        {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
            {
                T line = aClass.getConstructor(String.class).newInstance(sc.nextLine());
                if(line.getID().equals(String.valueOf(id)))
                    return line;
            }

            sc.close();

            return null;
        }
        catch (FileNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException ex)
        {
            System.out.println("ERROR : " + dataDirectory + "  "  + ex.getMessage());
            return null;
        }
    }

    /**
     * Insert new line of data.
     * @param t Object instance
     */
    public void insert(T t)
    {
        if(!this.checkIDExists(t.getID()))
            return;

        try
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(dataDirectory + fileName, true));
            pw.println(t.saveString());
            pw.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("ERROR : " + dataDirectory + "  "  + ex.getMessage());
            return;
        }
    }

    /**
     * Update using id. Id field cannot be changed once inserted.
     * @param t Object Instance
     *
     * //TODO: find a better way to do this?
     */
    public void update(T t)
    {
        ArrayList<T> arrayList = this.read();

        for(int i = 0; i < arrayList.size(); i++)
        {
            if(arrayList.get(i).getID().equals(t.getID()))
            {
                arrayList.set(i, t);
            }
        }

        this.write(arrayList);
    }

    /**
     * Write multiple new entries to file
     * @param data ArrayList<T>
     */
    public void write(ArrayList<T> data)
    {
        //Check if file exists, and if not check and create the data directory
        if(!exists(fileName))
        {
            if(!createDirectory())
                return;
        }

        try
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(dataDirectory + fileName));
            for (T t : data)
                pw.println(t.saveString());
            pw.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("!" + ex.getMessage());
            return;
        }
    }

    public boolean checkIDExists(String id)
    {
        ArrayList<T> arrayList = this.read();
        for(T x : arrayList)
        {
            if(x.getID().equals(id))
                return false;
        }
        return true;
    }
    /**
     * Check if a file exists in data directory
     * @param fileName Name of file.
     * @return Boolean
     */
    private static Boolean exists(String fileName)
    {
        return Files.exists(Paths.get(dataDirectory + fileName));
    }

    /**
     * Check if a directory exists and if not create it recursively
     * @return Boolean
     */
    private static Boolean createDirectory()
    {
        if(Files.exists(Paths.get(dataDirectory)))
            return true;

        try
        {
            Path path = Paths.get(dataDirectory);
            Files.createDirectory(path);
            return true;
        }
        catch(IOException io)
        {
            //TODO: LOG ERROR
            return false;
        }
    }
}
