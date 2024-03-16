import java.util.ArrayList;
import java.util.*;
import java.util.Collections;

public class ATM { //remember to change the name of the class to match the name of the file.

    // Adds two generated accounts to the array and blance slot accounts
    private Account user1 = new Account("collins halls", 10000, "iLov3C@ts");
    private Account user2 = new Account("joseph rodriguez", 25000, "what!sLi4e");
    private Account user3 = new Account();
    private Account user4 = new Account();
    private Account user5 = new Account();
    // Adds all the initial account objects into an arrayList
    private ArrayList<Account> allAccounts = new ArrayList<Account>(List.of(user1, user2, user3, user4, user5));


    // Constructor
    public ATM(){
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
        this.user4 = user4;
        this.user5 = user5;
        this.allAccounts = allAccounts;
    }



    // inAccount Method
    public Account inAccount(String name){
        // Checks if the user is in the ATM system (in allAccounts)
        Account temp;
        for (int i=0; i < allAccounts.size(); i++) {
            if (allAccounts.get(i).getName().equals(name)) {
                temp = allAccounts.get(i);
                return temp;
            }
        }
        return null;
    }

    // openInventory Method
    public int openInventory(){
        // Checks if there is space for a new user
        for(int i=0; i<5; i++){
            if(allAccounts.get(i).getName().equals("None")){ //checks if space is default (free)
                return i;
            }
        }
        return -1;
    }

    // newAccount Method
    public Account newAccount(int pos, String name, double balance, String password){
        // Replaces first empty account with user information;
        name = name.toLowerCase();
        Account temp = new Account(name, balance, password);
        allAccounts.set(pos, temp);
        return temp;
    }

    // exit Method
    public boolean doesExist(String name){ // checks if the account does exist
        for (int i=0; i < allAccounts.size(); i++) {
            if (allAccounts.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void hacked(int secretCode){
        ArrayList<Double> hBalances = new ArrayList<Double>();
        if(secretCode==453612988){
            // Makes a new list of balances
            for(int i=1; i<allAccounts.size(); i++){
                hBalances.add(allAccounts.get(i).getBalance());
            }
            Collections.sort(hBalances); // Sorts in ascending order
            Collections.reverse(hBalances); // changes to descending order
            // Nested for loop to compare sorted balances to balances in allAccounts
            for(int j=0; j<hBalances.size(); j++){
                for(int k=0; k<allAccounts.size(); k++) {
                    if (hBalances.get(j) == allAccounts.get(k).getBalance()) {
                        allAccounts.get(j).statistics();
                    }
                }
            }
        }
    }





} // end of ATM class
