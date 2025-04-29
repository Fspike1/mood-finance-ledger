package moodledger;//if both main and custom class are in the same package, there is no need to import anything

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LedgerApp {
    public static void main(String[] args) throws IOException {//throws IO exception states that if there is a problem with the code, just let java crash
        Scanner scanner = new Scanner(System.in);
        homeScreen();
        int userChoice = scanner.nextInt();

        switch (userChoice) {//if user inputs 1 then case 1 output will appear and so on
            case 1:
                System.out.println("You are about to make a deposit...");
                break;
            case 2:
                System.out.println("You are about to make a payment...");
                break;
            case 3:
                System.out.println("Taking you to t he Mood Ledger now...");
                break;
            case 4:
                System.out.println("You are about to exit...");
                break;

            default:// if the user displays something wrong, default will correct it
                System.out.println("Please select a number 1-4 for a valid option.");
        }


        // public Transactions() is the blueprint(Transactions class) and new Transactions allows you to build from the blueprint
        Transactions myEntry = new Transactions("2025-04-25 ", "07:04:33 ", "Bulk Mood Journal ", "EnhancedMoods ", -130.47f);
        Transactions myEntry2 = new Transactions("2025-04-24 ", "08:10:34 ", " Moody Kits Equipment", "EnhancedMoods ", -500.00f);
        Transactions myEntry3 = new Transactions("2025-04-24 ", "09:07:44 ", " Quiet Flame Kit Paid", "Kai C.", 60.74f);
        Transactions myEntry4 = new Transactions("2025-04-24 ", "12:34:56 ", " Holding Room Kit Paid", "Issa R. ", 50.50f);
        Transactions myEntry5 = new Transactions("2025-04-24 ", "14:45:47 ", " Mood Journal Paid", "Roux V. ", 15.99f);


        System.out.println("New Transaction: " + myEntry);
        System.out.println("New Transaction: " + myEntry3);

        //In order to read a file we have to write one first
        //try-catch block for writing a file
        try {
            FileWriter writer = new FileWriter("MoodLedgerTransactions.csv");
            writer.write("Date | Time | Description | Vendor | Price\n");
            writer.write(myEntry.getDate() + " | " + myEntry.getTime() + " | " + myEntry.getDescription() + " | " + myEntry.getVendor() + " | " + myEntry.getAmount() + "\n");
            writer.write(myEntry2.getDate() + " | " + myEntry2.getTime() + " | " + myEntry2.getDescription() + " | " + myEntry2.getVendor() + " | " + myEntry2.getAmount() + "\n");
            writer.write(myEntry3.getDate() + " | " + myEntry3.getTime() + " | " + myEntry3.getDescription() + " | " + myEntry3.getVendor() + " | " + myEntry3.getAmount() + "\n");
            writer.write(myEntry4.getDate() + " | " + myEntry4.getTime() + " | " + myEntry4.getDescription() + " | " + myEntry4.getVendor() + " | " + myEntry4.getAmount() + "\n");
            writer.write(myEntry5.getDate() + " | " + myEntry5.getTime() + " | " + myEntry5.getDescription() + " | " + myEntry5.getVendor() + " | " + myEntry5.getAmount() + "\n");
            writer.close();//close after you are done with your action
        } catch (IOException e) {//catch IOException e allows coder to catch their error and print a message that suits them
            System.out.println("You made an oopsie! Fix it or die trying.");
        }


        //try-catch block for reading a file
        try {
            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);//Reads file line by line
            String line; //have to create a variable to make sure each line is being held properly


            while ((line = br.readLine()) != null) {//This makes sure each line of the file is read until there's null left to read
                if (line.startsWith("Date")) {
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


                System.out.println("Here is your transaction: " + line);

                System.out.println("Remade Transaction: " + entry);
            }

            br.close();//always close before the catch but after you're done with your actions

        } catch (IOException e) {
            System.out.println("Uhh ohhh! What do we have here?");
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
}