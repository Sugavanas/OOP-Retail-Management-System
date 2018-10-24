package db;

import classes.FileIO;
import object.Staff;

import java.util.ArrayList;

public class Staffs
{
    private static FileIO<Staff> file = new FileIO<>("staffs.dat", Staff.class);

    public static void addStaff(int id, String name, String password, Boolean isAdmin)
    {
        Staff staff = new Staff(id, name, password, isAdmin);

        file.insert(staff);
    }

    public static Boolean hasStaffs()
    {
        if(file.read().size() != 0)
            return true;

        return false;
    }

    public static Staff Login(int id, String password)
    {
        Staff s = file.getByID(id);
        if(s.getPassword().equals(password))
            return s;

        return null;
    }
}
