package db;

import classes.FileIO;
import object.Staff;

import java.util.ArrayList;

public class Staffs
{
    private static FileIO<Staff> file = new FileIO<>("staffs.dat", Staff.class);

    public static void addItem(int id, String name, String password, Boolean isAdmin)
    {
        Staff staff = new Staff(id, name, password, isAdmin);

        file.insert(staff);

        //For testing
        ArrayList<Staff> itemArrayList = file.read();
        for (Staff i : itemArrayList)
            System.out.println(i.saveString());
    }
}
