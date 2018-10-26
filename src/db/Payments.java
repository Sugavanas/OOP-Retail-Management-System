package db;

import classes.FileIO;
import object.Payment;

public class Payments {
    private static FileIO<Payment> file = new FileIO<>("items.dat", Payment.class);


}
