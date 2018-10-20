import classes.FileIO;
import object.Item;

public class Main {
    public static void main(String[] args) {
        Item i = new Item("test");
        System.out.println(i.name + i.code);
    }
}
