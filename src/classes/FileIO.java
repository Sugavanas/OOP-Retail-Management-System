package classes;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * I/O class for reading/writing to files.
 */
public class FileIO<T extends Objects>
{
    private static String dataDirectory = System.getProperty("user.dir") + "/data/";

    private String fileName;
    private Class<T> aClass;

    public FileIO(String fileName, Class<T> aClass)
    {
        this.fileName = fileName;
        this.aClass = aClass;
    }
    /**
     * Read from file
     *
     * @return ArrayList<obj> or null if error
     */

    public ArrayList<T> read()
    {
        //Check if file exists.
        if(!exists(fileName))
            return null;

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
            return null;
        }
    }

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
            return null;
        }
    }

    /**
     * Insert new line of data.
     * @param t Object instance
     */
    public void insert(T t)
    {
        try
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(dataDirectory + fileName, true));
            pw.println(t.saveString());
            pw.close();
        }
        catch (FileNotFoundException ex)
        {
            return;
        }
    }

    /**
     * Update using id. Id field cannot be changed once inserted.
     * @param t Object Instance
     */
    public void update(T t)
    {

    }

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
