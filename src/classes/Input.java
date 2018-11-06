package classes;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input
{
    Scanner scanner;
    Pattern delimeter;
    public Input()
    {
        scanner = new Scanner(System.in);
        delimeter = scanner.delimiter();
    }
    public int nextInt()
    {
        /* //OLD Method
        Integer val = null;
        do {
            try {
                val = scanner.nextInt();
            }catch (Exception ex){
                System.out.println("Invalid Input Try again: " + ex.getMessage()) ;
                val = null;
                scanner = new Scanner(System.in);
            }
        }while(val == null);
        return val;


        while(!scanner.hasNextInt())
        {
            System.out.println("Invalid Input Try again!: ") ;
            scanner.nextLine();
        }
        return scanner.nextInt();

          */

        Integer val = null;
        do {
            try {
                val = scanner.nextInt();
            }catch (Exception ex){
                scanner.nextLine();
                System.out.println("Error Invalid Number Input") ;
                System.out.print("Try Again: ");
                val = null;

            }
        }while(val == null);
        return val;
    }

    public String next()
    {
        scanner.useDelimiter("\n"); //So space isn't counted as a delimeter.
        String val = null;
        do {
            try {
                val = scanner.next();
                if(!(val.length() > 0) || !(val.trim().length() > 0)) //Make sure input is not empty or just filled with spaces
                    val = null;
            }catch (Exception ex){
                scanner.nextLine();
                System.out.println("Error Invalid Input") ;
                System.out.print("Try Again: ");
                val = null;

            }
        }while(val == null);
        scanner.useDelimiter(delimeter); //Reset back to original delimeter.
        return val.trim();
    }

    public double nextDouble()
    {
        Double val = null;
        do {
            try {
                val = scanner.nextDouble();
            }catch (Exception ex){
                scanner.nextLine();
                System.out.println("Error Invalid Decimal Input") ;
                System.out.print("Try Again: ");
                val = null;

            }
        }while(val == null);
        return val;
    }

    public String nextLine()
    {
        return scanner.nextLine();
    }
}
