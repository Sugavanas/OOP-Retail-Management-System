package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * I/O class for reading/writing to files.
 */
public class FileIO{
    private static String dataDirectory = System.getProperty("user.dir") + "/data/";

    public static <T> Object read(String fileName, Class<T> obj)
    {
        if(!exists(fileName))
            return null;

        File file = new File(dataDirectory + fileName);

        try
        {
            Scanner sc = new Scanner(file);

            ArrayList<T> arrayList = new ArrayList<>();

            while (sc.hasNextLine())
            {
                T line = obj.getConstructor(String.class).newInstance(sc.nextLine());
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

    //TODO: add another method that reads line by line and searches for data (for saving memory)

    public static void write(String fileName, String data)
    {
        //Check if file exists, and if not check and create the data directory
        if(!exists(fileName))
        {
            if(!createDirectory())
                return;
        }


    }

    public static Boolean exists(String fileName)
    {
        if(Files.exists(Paths.get(dataDirectory + fileName)))
            return true;
        else
            return false;
    }

    private static Boolean createDirectory()
    {
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
