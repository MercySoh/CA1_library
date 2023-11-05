package app;

import java.util.Scanner;

/**
 * @author Conor, Mercy, Julie
 */
public class LibraryApp {

    public static void main(String[] args){

        System.out.println("Enter one of the following option");
        System.out.println("To Register type: 1");
        System.out.println("To Login type: 2");
        System.out.println("To Logout type: 0");

        Scanner userInput = new Scanner(System.in);  // Create a Scanner object

        int option = userInput.nextInt();  // Read user input

        switch (option) {
            case 1:
                System.out.println("Please Enter Your details");
                String name = userInput.nextLine();  // Read user input
                String email = userInput.nextLine();  // Read user input
                String username = userInput.nextLine();  // Read user input
                String password = userInput.nextLine();  // Read user input
                String phone = userInput.nextLine();  // Read user input
                String address = userInput.nextLine();  // Read user input
                String city = userInput.nextLine();  // Read user input
                String postcode = userInput.nextLine();  // Read user input
                // call Methods to
                break;
            case 2:
                System.out.println("Please Enter Login details");
                break;
            case 0:
                System.out.println("You are Logged out !");
                break;
            default:
                System.out.println("Looking forward to the Weekend");
        }
    }
}
