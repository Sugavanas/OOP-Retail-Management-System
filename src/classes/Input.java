package classes;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class that basically does the same thing as Scanner but catches errors instead of terminating the program.
 */
public class Input
{

    Scanner scanner;

    Pattern delimiter;

    public Input()
    {
        scanner = new Scanner(System.in);
        delimiter = scanner.delimiter(); //store the default delimiter.
    }

    /**
     * Read the next integer and catch input mismatch exception.
     * @return String
     */
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

    /**
     * Read the next string and catch input mismatch exception. Change scanner delimiter to \n so space character is accepted as input.
     * @return String
     */
    public String next()
    {
        scanner.useDelimiter("\n"); //So space isn't counted as a delimiter.
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
        scanner.useDelimiter(delimiter); //Reset back to original delimeter.
        return val.trim();
    }

    /**
     * Read the nextDouble and catch input mismatch exception
     * @return double
     */
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

    /**
     * Read the nextLine
     * @return String
     */
    public String nextLine()
    {
        return scanner.nextLine();
    }
}
