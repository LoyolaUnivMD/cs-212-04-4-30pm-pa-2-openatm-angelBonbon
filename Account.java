import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

public class Account {

    Scanner scan = new Scanner(System.in);
    private String name;
    //private double[] transactions = new double[4];
    private ArrayList<Double> transactions = new ArrayList<Double>();
    private double balance;
    private String password;

    public Account(String name, double balance, String password){
        this.name = name;
        this.balance = balance;
        this.password = password;
    }

    public Account(){
        this.name = "None";
        this.balance = 0;
        this.password = null;
    }

    // Security Check Method
    public boolean securityCheck(){
        return true;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////

    // options Method
    public void options(){
        System.out.println("-".repeat(50));
        System.out.println("Please select from the following options:");
        System.out.println("-".repeat(50));
        System.out.println("S - Statistics\n\tDisplays your current balance, minimum transaction, maximum transaction, and transaction average ");
        System.out.println("T - Transaction\n\tDisplays your last five transactions");
        System.out.println("D - Deposit\n\tAllows you to deposit a provided amount into your balance");
        System.out.println("W - Withdraw\n\tAllows you to withdraw a provided amount from your balance");
        System.out.println("L - Log out of your account");
        System.out.println("-".repeat(50));
    }

    // isOwner Method
    public boolean isOwner(){
        System.out.println("Please confirm your identity by entering your password. You have 3 attempts.");
        int attemps = 0;
        String genPassword = scan.nextLine();
        while(!genPassword.equals(this.password) && attemps<2){
            System.out.println("The password you have entered is incorrect. Try again or exit.");
            genPassword = scan.nextLine();
            attemps+=1;
        }
        if(attemps<2){
            return true;
        } else {
            return false;
        }
    }


    // isNumber Method
    public boolean isNumber(String value){
        // Checks if a value is a number
        boolean isNumeric = true;
        try {
            Double.parseDouble(value); // used to typecast the value
        } catch (NumberFormatException e) {
            isNumeric = false; // runs false if value can't be casted to a double
        }
        return isNumeric;
    }

    public double checkNumber(String val){
        while (!this.isNumber(val)) {
            System.out.println("Error. Invalid withdraw amount. Please try again."); //Error Message
            System.out.println("\nHow much money would you like to withdraw? "); // Ask user for input again
            val = scan.nextLine();
        }
        return Double.parseDouble(val);
    }

    // deposit Method
    public double deposit(){
        System.out.println("-".repeat(50));
        // Ask user how much they want to deposit
        System.out.println("\nHow much money would you like to deposit? ");
        String deposit = scan.nextLine(); // typecasts input to a character
        // Stores double value withdraw
        double deposit1 = this.checkNumber(deposit);
        // While deposit is less than 0
        while (deposit1 < 0){
            System.out.println("Error. Cannot enter negative amount. Please try again."); //Error Message
            System.out.println("\nHow much money would you like to deposit? "); // Ask user for input again
            String temp = scan.nextLine();
            deposit1 = this.checkNumber(temp);
        }
        // Update balance (balance = balance + deposit)
        balance += deposit1;
        transactions.add(deposit1);
        // Completion message
        System.out.println("Your balance has been updated.");
        // Returns balance back to main
        return balance;
    }

    // withdraw Method
    public double withdraw(){
        System.out.println("-".repeat(50));
        // Ask user how much they want to withdraw
        System.out.println("How much money would you like to withdraw? ");
        String withdraw = scan.nextLine();
        // Stores double value withdraw
        double withdraw1 = this.checkNumber(withdraw);
        // While withdraw is less than 0 or withdraw amount with result in negative balance
        while (withdraw1 < 0 || balance-withdraw1<0){
            System.out.println("Error. Cannot enter negative amount. Please try again."); //Error Message
            System.out.println("\nHow much money would you like to withdraw? "); // Ask user for input again
            String temp = scan.nextLine();
            withdraw1 = this.checkNumber(temp); // keeps it in loop
        }
        // Update balance (balance = balance + withdraw)
        balance -= withdraw1;
        transactions.add(-(withdraw1));
        // Completion message
        System.out.println("Your balance has been updated.");
        // Returns balance back to main
        return balance;
    }

    // display Method
    public void statistics(){
        System.out.println("-".repeat(50));
        System.out.println("Balance: " + this.balance);
        findMin();
        findMax();
        getAvg();
    }

    public ArrayList<Double> lastTrans(){
        ArrayList<Double> last5Trans = new ArrayList<Double>();
        // Checks if there are more than 5 transactions
        if(transactions.size()>=5) {
            for (int i = transactions.size() - 5; i < transactions.size(); i++) {
                last5Trans.add(transactions.get(i)); // makes a new array with the last 5 trans.
            }
        } else { // Occurs if there are less than 5 transactions
            for(int i=0; i<transactions.size(); i++){
                last5Trans.add(transactions.get(i)); // makes a new array with all trans.
            }
        }
        return last5Trans;
    }

    public void display(){
        ArrayList<Double> fiveTrans = this.lastTrans();
        System.out.println("Five Latest Transactions: ");
        for (int i=0; i<fiveTrans.size(); i++) { // prints out all the transactions
            System.out.println("\ttransaction " + (i+1) + ": " + fiveTrans.get(i));
        }
    }

    public void findMin(){
        ArrayList<Double> fiveTrans = this.lastTrans();
        double min = 0;
        if(fiveTrans.size()>0) {
            min = fiveTrans.getFirst(); // sets first value in transaction to min
            for (int i = 1; i < fiveTrans.size(); i++) { // checks each number in transactions
                if (fiveTrans.get(i) < min) {
                    min = fiveTrans.get(i); // replaces min if there is a smaller value
                }
            }
        }
        System.out.println("Your minimum transaction is: " + min); // outputs to the user
    }

    public void findMax(){
        ArrayList<Double> fiveTrans = this.lastTrans();
        double max = 0;
        if(fiveTrans.size()>0) {
            max = fiveTrans.getFirst(); // sets first value in transaction to max
            for (int i = 1; i < fiveTrans.size(); i++) { // checks each number in transactions
                if (fiveTrans.get(i) > max) {
                    max = fiveTrans.get(i); // replaces max if there is a greater value
                }
            }
        }
        System.out.println("Your maximum transaction is: " + max); // outputs to the user
    }

    public void getAvg(){
        ArrayList<Double> fiveTrans = this.lastTrans();
        double total = 0;
        // adds up all the numbers in transaction
        for (int i=0; i<fiveTrans.size(); i++) {
            total += fiveTrans.get(i);
        }
        double avg = total / fiveTrans.size(); // finds average by dividing total by no. of transactions
        System.out.println("Your transaction average is: " + avg); // outputs to user
    }

    /// All methods that do not relate directly to transactions

    // getName Method
    public String getName() {
        return name;
    }

    // getBalance Method
    public double getBalance() {
        return balance;
    }

    /*public int getAccountNum() {
        this.transactions.get
    }*/

    /////////////////////////////////////////////////////////////////////////////////////////////////////////



}




