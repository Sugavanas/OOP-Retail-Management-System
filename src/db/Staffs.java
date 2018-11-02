package db;

import classes.FileIO;
import object.Staff;

public class Staffs
{
    /**
     * An instance of File IO class.
     */
    private static FileIO<Staff> file = new FileIO<>("staffs.dat", Staff.class);

    /**
     * Creating a new staff profile.
     * @param id
     * @param first_name
     * @param last_name
     * @param password
     * @param isAdmin
     */
    public static void addStaff(int id, String first_name, String last_name, String password, Boolean isAdmin)
    {
        Staff staff = new Staff(id, first_name, last_name, password, isAdmin);

        file.insert(staff);
    }

    /**
     * Removing an existing staff profile by ID.
     * @param id
     */
    public static void removeStaff(int id)
    {
        Staff s = file.getByID(id);
        file.delete(s);
    }

    /**
     * Edits staff profile
     * @param s
     */
    public static void editStaff(Staff s)
    {
        file.update(s);
    }

    /**
     * Checks whether there is a staff at all.
     * @return Returns Boolean (True or False)
     */
    public static Boolean hasStaffs()
    {
        if (file.read().size() != 0)
            return true;

        return false;
    }

    /**
     * Verification done to ensure weather the staff Information entered matches with what is in the database.
     * @param id
     * @param password
     * @return
     */
    public static Staff Login(int id, String password)
    {
        Staff s = file.getByID(id);

        if (s == null)
            return null;

        if (s.getPassword().equals(password))
            return s;

        return null;
    }
}
