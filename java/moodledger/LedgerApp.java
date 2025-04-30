package moodledger;//if both main and custom class are in the same package, there is no need to import anything

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static moodledger.Ledger.ledgerScreen;




public class LedgerApp {
    public static void main(String[] args) throws IOException {//throws IO exception states that if there is a problem with the code, just let java crash

        List<Transactions> transactionsList = new ArrayList<>();//this list can be accessed in every method

        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = true;


        while (loggedIn)//this loop is saying while the user is logged into the app, they can do any of the following things
        {homeScreen();//This has to be here because we need to know what screen we are focused on


        int userChoice = scanner.nextInt();//just learned that I need to add this in the loop so user can see choices before making them.



        switch (userChoice) {//if user inputs 1 then case 1 output will appear and so on
            case 1:
                boolean inDepositScreen = true;//While user is in the deposit screen what do they want to do?
                while(inDepositScreen) {
                    addDepositScreen(scanner, transactionsList);
                    System.out.println("Press 0 to return home");
                    int input = scanner.nextInt();
                    if (input == 0)
                        inDepositScreen = false;

                }break;
            case 2:
                boolean inPaymentScreen = true;//While user is in the payment screen what do they want to do?
                while (inPaymentScreen) {
                    makePaymentScreen(scanner, transactionsList);
                    System.out.println("Press 0 to return home");
                    int input = scanner.nextInt();
                    if (input == 0)
                        inPaymentScreen = false;

                } break;
            case 3:
                boolean inLedgerScreen = true;//While user is in the ledger screen what do they want to do?
                while (inLedgerScreen) {
                    ledgerScreen(scanner, transactionsList);
                    System.out.println("Press 0 to return home");
                    int input = scanner.nextInt();
                    if (input == 0)
                        inLedgerScreen = false;
                } break;
            case 4:
                System.out.println("See you again soon!");
                System.out.println("Exiting...");
                loggedIn = false;//false stops the loop from running
                break;

            default:// if the user displays something wrong, default will correct it
                System.out.println("Please select a number 1-4 for a valid option.");
                break;
        }

        }
    }


    public static void homeScreen() {//this method is only used for showing the screen. anything that needs doing should be done in the main

        System.out.println("================================");
        System.out.println(" MOOD LEDGER HOME SCREEN ");
        System.out.println("=================================");
        System.out.println("What would you like to do today?");
        System.out.println("1- Add Deposit");
        System.out.println("2- Make Payment");
        System.out.println("3- Go to Ledger");
        System.out.println("4- Exit");

        System.out.println("Please select one.");



    }
    public static void addDepositScreen(Scanner scanner , List<Transactions> newTransactions) {



        System.out.println("=============================");
        System.out.println("Add A Deposit Screen");
        System.out.println("=============================");
        System.out.println("Make a deposit here.");

        System.out.println("Please enter the date (yyyy-MM-dd):");
        scanner.nextLine();//since this is the same scanner from the main method and I used and scanner.nextInt,
        // I added an additional line or the code gets thrown off
        String date = scanner.nextLine();


        System.out.println("Please enter the time(hh:mm:ss):");
        String time = scanner.nextLine();

        System.out.println("What did you order?:");
        String description = scanner.nextLine();

        System.out.println("Enter vendor's name:");
        String vendor = scanner.nextLine();

        System.out.println("Please enter the amount: ");
        float amount = scanner.nextFloat();
        scanner.nextLine();


        Transactions newDeposit = new Transactions(date,time,description,vendor,amount);
        newTransactions.add(newDeposit);
        //In order to read a file we have to write one first
        //try-catch block for writing a file
        try {
            FileWriter writer = new FileWriter("MoodLedgerTransactions.csv", true);//the append:true is saying dont delete anything just add new things to the file
                writer.write(newDeposit.getDate() + " | " + newDeposit.getTime() + " | " + newDeposit.getDescription() + " | " + newDeposit.getVendor()+ " | " + newDeposit.getAmount()+ "\n");

            writer.close();//close after you are done with your action
        } catch (IOException e) {//catch IOException e allows coder to catch their error and print a message that suits them
            System.out.println("You made an oopsie! Fix it or die trying.");
        }
        //Don't need a BufferedReader here because I am not displaying or searching for entries





    }



    public static void makePaymentScreen(Scanner scanner , List<Transactions> newTransactions) {
        System.out.println("=============================");
        System.out.println("Make A Payment Screen");
        System.out.println("=============================");
        System.out.println("Welcome to the payment screen");
        System.out.println("Make a payment here.");


        System.out.println("Please enter the date (yyyy-MM-dd):");
        scanner.nextLine();//since this is the same scanner from the main method and I used and scanner.nextInt,
        // I added an additional line or the code gets thrown off
        String date = scanner.nextLine();


        System.out.println("Please enter the time(hh:mm:ss):");
        String time = scanner.nextLine();

        System.out.println("What did you order?:");
        String description = scanner.nextLine();

        System.out.println("Enter the Vendor:");
        String vendor = scanner.nextLine();

        System.out.println("Please enter the amount: ");
        float amount = scanner.nextFloat()*-1;//*-1 to show that user is making a payment
        scanner.nextLine();


        Transactions newPayment = new Transactions(date,time,description,vendor,amount);
        newTransactions.add(newPayment);
        //In order to read a file we have to write one first
        //try-catch block for writing a file
        try {
            FileWriter writer = new FileWriter("MoodLedgerTransactions.csv", true);//the append:true is saying dont delete anything just add new things to the file
            writer.write(newPayment.getDate() + " | " + newPayment.getTime() + " | " + newPayment.getDescription() + " | " + newPayment.getVendor()+ " | " + newPayment.getAmount()+ "\n");

            writer.close();//close after you are done with your action
        } catch (IOException e) {//catch IOException e allows coder to catch their error and print a message that suits them
            System.out.println("You made an oopsie! Fix it or die trying.");
        }
        //Don't need a BufferedReader here because I am not displaying or searching for entries
    }

}