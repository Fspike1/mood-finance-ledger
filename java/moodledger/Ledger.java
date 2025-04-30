package moodledger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    public static void ledgerScreen(Scanner scanner, List<Transactions> newTransactions) {
        boolean viewLedger = true;
        while (viewLedger) {
            System.out.println("=============================");
            System.out.println("Mood Ledger Screen");
            System.out.println("=============================");
            System.out.println("A) - Show All Entries");
            System.out.println("D)- Show only Deposits");
            System.out.println("P) - Show Only Payments");
            System.out.println("R) - Show Reports");
            System.out.println("H) - Return to HomeScreen");
            System.out.println("You are in the Mood Ledger");

            System.out.println("Enter your choice: ");

            String choice = scanner.next().toUpperCase();
            switch (choice) {
                case "A":
                    //Display all entries from csv file
                    showAllEntries();
                    break;
                case "D":
                    showDepositsOnly();
                    break;
                case "P":
                    showPaymentsOnly();
                case "R":
                    showReports();
                    break;
                case "H":
                    viewLedger = false;
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }
        }


    }

    public static void showAllEntries() {
        //try-catch block for reading a file
        try {
            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);//Reads file line by line
            String line; //have to create a variable to make sure each line is being held properly

            while ((line = br.readLine()) != null) {//This makes sure each line of the file is read until there's null left to read
                if (line.contains("Date")) {
                    continue;
                }

                //Add split in your while loop so that it will do everything in the loop. Outside the loop it won't work
                //split creates an array of strings[date,time,description,vendor,amount]
                String[] parts = line.split(" \\| ");
                Transactions entry = new Transactions(
                        parts[0],//this is the date
                        parts[1],//time
                        parts[2],//description
                        parts[3],//vendor
                        Float.parseFloat(parts[4])//in order to turn a string(Amount is a string) into float I have to use parse
                );
                System.out.println("Ledger Entries: " + entry);
                System.out.println("\n All Ledger Entries:\n");

            }

            br.close();//always close before the catch but after you're done with your actions
        } catch (IOException e) {
            System.out.println("Uhh ohhh! What do we have here?");
        }

    }

    public static void showDepositsOnly() {
        //try-catch block for reading a file
        try {
            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);//Reads file line by line
            String line; //have to create a variable to make sure each line is being held properly

            while ((line = br.readLine()) != null) {//This makes sure each line of the file is read until there's null left to read
                if (!line.contains("Paid")) {
                    continue;
                }

                //Add split in your while loop so that it will do everything in the loop. Outside the loop it won't work
                //split creates an array of strings[date,time,description,vendor,amount]
                String[] parts = line.split(" \\| ");
                Transactions entry = new Transactions(
                        parts[0],//this is the date
                        parts[1],//time
                        parts[2],//description
                        parts[3],//vendor
                        Float.parseFloat(parts[4])//in order to turn a string(Amount is a string) into float I have to use parse
                );

                System.out.println("These are your deposits: " + entry);


            }

            br.close();//always close before the catch but after you're done with your actions
        } catch (IOException e) {
            System.out.println("Uhh ohhh! What do we have here?");
        }


    }



    public static void showPaymentsOnly() {
        //try-catch block for reading a file
        try {
            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);//Reads file line by line
            String line; //have to create a variable to make sure each line is being held properly

            while ((line = br.readLine()) != null) {//This makes sure each line of the file is read until there's null left to read
                if (line.contains("date")) {
                    continue;
                }

                //Add split in your while loop so that it will do everything in the loop. Outside the loop it won't work
                //split creates an array of strings[date,time,description,vendor,amount]
                String[] parts = line.split(" \\| ");
                Transactions entry = new Transactions(
                        parts[0],//this is the date
                        parts[1],//time
                        parts[2],//description
                        parts[3],//vendor
                        Float.parseFloat(parts[4])//in order to turn a string(Amount is a string) into float I have to use parse
                );
                if (entry.getAmount() >= 0)
                    continue;

                System.out.println("These are your payments: " + entry);


            }

            br.close();//always close before the catch but after you're done with your actions
        } catch (IOException e) {
            System.out.println("Uhh ohhh! What do we have here?");
        }





    }
    public static void showReports() {

    }
}
