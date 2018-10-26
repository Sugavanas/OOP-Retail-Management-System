package db;

import classes.FileIO;
import object.Staff;

import java.util.ArrayList;

public class Staffs
{
    private static FileIO<Staff> file = new FileIO<>("staffs.dat", Staff.class);

    public static void addStaff(int id, String first_name, String last_name, String password, Boolean isAdmin)
    {
        Staff staff = new Staff(id, first_name, last_name, password, isAdmin);

        file.insert(staff);
    }

    public static void removeStaff(int id)
    {
        Staff s = file.getByID(id);
        file.delete(s);
    }

    public static void editStaff(Staff s) {file.update(s);}

    public static Boolean hasStaffs()
    {
        if(file.read().size() != 0)
            return true;

        return false;
    }

    public static Staff Login(int id, String password)
    {
        Staff s = file.getByID(id);

        if(s == null)
            return null;

        if(s.getPassword().equals(password))
            return s;

        return null;
    }
}
