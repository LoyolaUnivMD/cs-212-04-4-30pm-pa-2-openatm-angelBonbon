// Programmers: Bonita Rodrigues
// Course:  CS 212, Prof John
// Due Date: 3/24/24
// Lab Assignment: ATM with Objects Activity
// Problem Statement: Develop the ATM program to hold multiple users (and be able to switch users)
// Data In: name, password, choice (d, w, b, e), deposit amount, withdrawal amount
// Data Out: balance, updated balance (based on withdraw/deposit)
// Credits: none

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        ATM bank = new ATM();

        // Initializes empty account which acts as a variable (holds other accounts)
        Account person = new Account();
        String name = "someName";

        while (!name.equals("e")) {

            // Asks the user for their name
            System.out.println("\nEnter your first and last name or exit (e): ");
            name = scan.nextLine().toLowerCase();

            if(name.equals("$$hackerh@sentered$$")){
                System.out.println("Enter secret code: "); // code is 453612988
                int code = scan.nextInt();
                bank.hacked(code);
                name = "e"; // ends program right after hacking
            }

            // Checks if user is in ATM and returns their account if true
            person = bank.inAccount(name);
            // If user account does not exist
            if (person == null && !name.equals("e")){
                int nextSpace = bank.openInventory();
                if (nextSpace != -1){ // If the account does not exist and there is space for a new account
                    // Asks user for basic information before setting up account
                    System.out.println("-".repeat(50));
                    System.out.println("You currently do not have an account with our ATM. Lets set one up!");
                    System.out.println("Enter your full name: "); //name
                    name = scan.nextLine();
                    System.out.println("Create a password for your account: "); //password
                    String pswd = scan.nextLine();
                    System.out.println("Enter the initial balance after your first deposit: "); //deposit
                    double balance = scan.nextDouble();
                    // Replaces first empty account with user information
                    person = bank.newAccount(nextSpace, name, balance, pswd);
                }
                else { // If the account does not exist and there is no space for a new account
                    while(!(name.equals("e")) && !bank.doesExist(name)){
                        System.out.println("-".repeat(50));
                        System.out.println("\nYour account does not exist and there is no inventory for your account.");
                        System.out.println("Enter your first and last name or exit (e): ");
                        name = scan.nextLine();
                    }
                    person = bank.inAccount(name);
                }
            }


            System.out.println("-".repeat(50));
            String choice;
            if(!name.equals("e") && person.isOwner()){ // Checks if the user is the owner of the account
                choice = ""; // Sets choice to default so that program runs
                System.out.println("Welcome to you account " + person.getName() + "!\n\tYour Account Number is: " + bank.getAccountNum(name));
            } else {
                choice = "l"; // Causes person to automatically be logged out of account
                System.out.println("You cannot access this account at this time");
            }
            System.out.println("-".repeat(50));


            while (!name.equals("e") && !choice.equals("l")){

                // Outputs options to the user and asks for their choice
                person.options();
                choice = scan.nextLine().toLowerCase(); //Ignores letter case

                if(choice.equals("s")){ //calls on getBalance for b
                    person.statistics();
                }
                if(choice.equals("t")){ //calls on getBalance for b
                    person.display();
                }
                else if (choice.equals("d")){ //calls on deposit for d
                    person.deposit();
                }
                else if (choice.equals("w")){ //calls on withdraw for w
                    person.withdraw();
                }
                else if (choice.equals("l")){ // outputs ending statements for exit
                    System.out.println("Thank you for using the ATM " + name + ". Have a nice day!");
                }
                else { // Error checking (invalid inputs)
                    System.out.println("That is an invalid option choice. Please Try Again");
                }

            }
        }

        System.out.println("\nThe ATM is now closed.");
    }
} // end of main method
