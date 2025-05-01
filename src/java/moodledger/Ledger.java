package moodledger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
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
                    break;
                case "R":
                    showReports(scanner);
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

            System.out.println("======= All Entries =======");

            while ((line = br.readLine()) != null) {//This makes sure each line of the file is read until there's null left to read

                if(line.trim().isEmpty())
                    continue;
                if (line.contains("Date")) {
                    continue;
                }

                //Add split in your while loop so that it will do everything in the loop. Outside the loop it won't work
                //split creates an array of strings[date,time,description,vendor,amount]
                String[] parts = line.split("\\|");
                if (parts.length < 5)
                    continue;
                Transactions entry = new Transactions(
                        parts[0].trim().replaceAll("\\s",""),//this is the date
                        parts[1].trim(),//time
                        parts[2].trim().toLowerCase(),//description
                        parts[3].trim().toLowerCase(),//vendor
                        Float.parseFloat(parts[4].trim())//in order to turn a string(Amount is a string) into float I have to use parse
                );

                System.out.println("These are all your entries: ");
                System.out.println(entry);

            }
            System.out.println("Press Enter to return to the Ledger Menu");
            new Scanner(System.in).nextLine();

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

            System.out.println("======= Deposits =======");

            while ((line = br.readLine()) != null) {//This makes sure each line of the file is read until there's null left to read
                if(line.trim().isEmpty())
                    continue;
                if (!line.contains("Paid")) {
                    continue;
                }

                //Add split in your while loop so that it will do everything in the loop. Outside the loop it won't work
                //split creates an array of strings[date,time,description,vendor,amount]
                String[] parts = line.split("\\|");
                if (parts.length < 5)
                    continue;
                Transactions entry = new Transactions(
                        parts[0].trim().replaceAll("\\s", ""),//this is the date
                        parts[1].trim(),//time
                        parts[2].trim().toLowerCase(),//description
                        parts[3].trim().toLowerCase(),//vendor
                        Float.parseFloat(parts[4].trim())//in order to turn a string(Amount is a string) into float I have to use parse
                );

                System.out.println("These are your deposits:");
                System.out.println(entry);


            }
            System.out.println("Press Enter to return to the Ledger Menu");
            new Scanner(System.in).nextLine();

            br.close();//always close before the catch but after you're done with your actions
        } catch (IOException e) {
            System.out.println("Uhh ohhh! What do we have here?");
        }


    }


    public static void showPaymentsOnly() {
        //try-catch block for reading a file
        System.out.println("======= Payments =======");
        try {
            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);//Reads file line by line
            String line; //have to create a variable to make sure each line is being held properly

            while ((line = br.readLine()) != null) {//This makes sure each line of the file is read until there's null left to read

                if(line.trim().isEmpty())
                    continue;
                if (line.contains("date")) {
                    continue;
                }

                //Add split in your while loop so that it will do everything in the loop. Outside the loop it won't work
                //split creates an array of strings[date,time,description,vendor,amount]
                String[] parts = line.split("\\|");
                if (parts.length < 5)
                    continue;
                if(parts[0].toLowerCase().contains("date")){
                    continue;
                }
                Transactions entry = new Transactions(
                        parts[0],//this is the date
                        parts[1].trim(),//time
                        parts[2].trim().toLowerCase(),//description
                        parts[3].trim().toLowerCase(),//vendor
                        Float.parseFloat(parts[4].trim())//in order to turn a string(Amount is a string) into float I have to use parse
                );
                if (entry.getAmount() >= 0)
                    continue;

                System.out.println("These are your payments: ");
                System.out.println(entry);


            }
            System.out.println("Press Enter to return to the Ledger Menu");
            new Scanner(System.in).nextLine();

            br.close();//always close before the catch but after you're done with your actions
        } catch (IOException e) {
            System.out.println("Uhh ohhh! What do we have here?");
        }


    }

    public static void showReports(Scanner scanner) {
        boolean inReportsMenu = true;
        while (inReportsMenu) {
            System.out.println("======= Reports Menu =======");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back to Ledger Menu");
            System.out.println("Please choose an option: ");

            int chooseDate = scanner.nextInt();
            scanner.nextLine();
            switch (chooseDate) {
                case 1:
                    showMonthToDate();
                    break;
                case 2:
                    showPreviousMonth();
                    break;
                case 3:
                    showYearToDate();
                    break;
                case 4:
                    showPreviousYear();
                    break;
                case 5:
                    searchByVendor(scanner);
                    break;
                case 6:
                    customSearch(scanner);
                case 0:
                    System.out.println("Returning to Ledger Menu");
                    inReportsMenu = false;//When this is false, it exits the loop
                    break;

                default:
                    System.out.println("Please select a valid option:(Select from numbers 0-5");

            }


        }
    }

    public static void showMonthToDate() {
        System.out.println("======= Month to Date =======");
        //Get today's date is?
        LocalDate today = LocalDate.now();

        //Get first day of month
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);

        try {

            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty())
                    continue;
                if (line.contains("Date")){
                    continue;
                }
                String[] parts = line.split("\\|");//This line makes each line in CSV file look like this: 2025-04-25 | 07:04:33 | Mood Journal | EnhancedMoods | 13.47
                if (parts.length < 5)
                    continue;
                String date = parts[0].trim().replaceAll("\\s","");//this format: yyyy-MM-dd
                String time = parts[1].trim(); //this format: hh:mm:ss
                String description = parts[2].trim().toLowerCase();
                String vendor = parts[3].trim().toLowerCase();
                String amount = parts[4].trim();//has to be turned into a float(using parse)

                LocalDate entryDate = LocalDate.parse(date);

                if((entryDate.isEqual(firstDayOfMonth) || entryDate.isAfter(firstDayOfMonth)) && (entryDate.isBefore(today)|| entryDate.isEqual(today))) {
                    System.out.println("Month to Date: " + line);//line is what will be read by the br
                    Transactions entry = new Transactions(date,time,description,vendor,Float.parseFloat(amount));
                    System.out.println("Month to Date Entry: " + entry);
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException e){
            System.out.println("Uhhhh ohhhh spaghettios!");
        }
    }

    public static void showPreviousMonth(){
        System.out.println("======= Previous Month =======");
        LocalDate today = LocalDate.now();

        LocalDate previousMonth = today.minusMonths(1);

        LocalDate firstDay = previousMonth.withDayOfMonth(1);
        LocalDate lastDay = previousMonth.withDayOfMonth(previousMonth.lengthOfMonth());

        try {

            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty())
                    continue;
                if (line.contains("Date")){
                    continue;
                }
                String[] parts = line.split("\\|");//This line makes each line in CSV file look like this: 2025-04-25 | 07:04:33 | Mood Journal | EnhancedMoods | 13.47
                if (parts.length < 5)
                    continue;
                if (parts[0].toLowerCase().contains("date")) continue;
                String date = parts[0];//this format: yyyy-MM-dd
                String time = parts[1].trim(); //this format: hh:mm:ss
                String description = parts[2].trim().toLowerCase();
                String vendor = parts[3].trim().toLowerCase();
                String amount = parts[4].trim();//has to be turned into a float(using parse)

                LocalDate entryDate = LocalDate.parse(date);

                if((entryDate.isEqual(firstDay) || entryDate.isAfter(firstDay)) && (entryDate.isBefore(lastDay)|| entryDate.isEqual(lastDay))) {
                    System.out.println("Previous Month: " + line);//line is what will be read by the br
                    Transactions entry = new Transactions(date,time,description,vendor,Float.parseFloat(amount));
                    System.out.println("Previous Month's Entries: " + entry);
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException e){
            System.out.println("Uhhhh ohhhh spaghettios!");
        }
    }


    public static void showYearToDate(){
            System.out.println("======= Year to Date =======");
            LocalDate today = LocalDate.now();
            LocalDate yearToDate = today.withDayOfYear(1);


            try {

                FileReader fr = new FileReader("MoodLedgerTransactions.csv");
                BufferedReader br = new BufferedReader(fr);

                String line;
                while ((line = br.readLine()) != null) {
                    if(line.trim().isEmpty())
                        continue;
                    if (line.contains("Date")){
                        continue;
                    }
                    String[] parts = line.split(" \\| ");//This line makes each line in CSV file look like this: 2025-04-25 | 07:04:33 | Mood Journal | EnhancedMoods | 13.47
                    if (parts.length < 5)
                        continue;
                    if (parts[0].toLowerCase().contains("date")) continue;
                    String date = parts[0].trim().replaceAll("\\s","");//this format: yyyy-MM-dd
                    String time = parts[1].trim(); //this format: hh:mm:ss
                    String description = parts[2].trim().toLowerCase();
                    String vendor = parts[3].trim().toLowerCase();
                    String amount = parts[4].trim();//has to be turned into a float(using parse)

                    LocalDate entryDate = LocalDate.parse(date);

                    if((entryDate.isEqual(yearToDate) || entryDate.isAfter(yearToDate)) && (entryDate.isBefore(today)|| entryDate.isEqual(today))) {
                        System.out.println("Year to Date: " + line);//line is what will be read by the br
                        Transactions entry = new Transactions(date,time,description,vendor,Float.parseFloat(amount));
                        System.out.println("Year to Date Entry: " + entry);
                    }
                    }
                br.close();
                fr.close();
            }
            catch (IOException e){
                System.out.println("Uhhhh ohhhh spaghettios!");
            }




    }

    public static void showPreviousYear(){


        System.out.println("======= Previous Year =======");
        LocalDate today = LocalDate.now();
        LocalDate yearToDate = today.withDayOfYear(1);
        LocalDate previousYear = yearToDate.minusYears(1);


        LocalDate firstDayOfYr = previousYear.withDayOfYear(1);
        LocalDate lastDayOfYr = previousYear.withDayOfYear(previousYear.lengthOfYear());

        try {

            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty())
                    continue;
                if (line.contains("Date")){
                    continue;
                }
                String[] parts = line.split("\\|");//This line makes each line in CSV file look like this: 2025-04-25 | 07:04:33 | Mood Journal | EnhancedMoods | 13.47
                if (parts.length < 5)
                    continue;
                if (parts[0].toLowerCase().contains("date")) continue;
                String date = parts[0].trim().replaceAll("\\s","");//this format: yyyy-MM-dd
                String time = parts[1].trim(); //this format: hh:mm:ss
                String description = parts[2].trim().toLowerCase();
                String vendor = parts[3].trim().toLowerCase();
                String amount = parts[4].trim();//has to be turned into a float(using parse)

                LocalDate entryDate = LocalDate.parse(date);

                if((entryDate.isEqual(firstDayOfYr) || entryDate.isAfter(firstDayOfYr)) && (entryDate.isBefore(lastDayOfYr)|| entryDate.isEqual(lastDayOfYr))) {
                    System.out.println("Previous Year: " + line);//line is what will be read by the br
                    Transactions entry = new Transactions(date,time,description,vendor,Float.parseFloat(amount));
                    System.out.println("\nPrevious Year's Entries:\n " + entry);
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException e){
            System.out.println("Uhhhh ohhhh spaghettios!");
        }


    }
    public static void searchByVendor(Scanner scanner){
        System.out.println("======= Search by Vendor =======");
        //use scanner to get vendor name from user
        System.out.println("Enter vendor name you are searching for: ");
        scanner.nextLine();
        String searchVendor = scanner.nextLine().trim();//use trim to get rid of accidental extra spaces after input


        try {
            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null){
                if(line.trim().isEmpty())
                    continue;
                if (line.contains("Date")) {
                   continue;
               }
               String[] parts = line.split("\\|");
                if (parts.length < 5)
                    continue;
                if (parts[0].toLowerCase().contains("date")) continue;
               String date = parts[0].trim().replaceAll("\\s","");
                String time = parts[1].trim();
                String description = parts[2].trim().toLowerCase();
                String vendor = parts[3].trim().toLowerCase();
                String amount = parts[4].trim();
              if (vendor.trim().equalsIgnoreCase(searchVendor)) {
                  Transactions entry = new Transactions(date,time,description,vendor,Float.parseFloat(amount));
                  System.out.println("Found Vendor Entry: " + entry);
              }
            }

        }
        catch (IOException e) {
            System.out.println("I can't read this!");

        }


    }

    public static void customSearch(Scanner scanner) {
        System.out.println("======= Custom Search =======");

        System.out.println("Enter start date (yyyy-MM-dd) or leave blank:3");
        String startDate = scanner.nextLine().trim();

        System.out.println("Enter end date (yyyy-MM-dd) or leave blank:");
        String endDate = scanner.nextLine().trim();

        System.out.println("Enter description keyword or leave blank:");
        String descriptionSt = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter the vendor or leave blank:");
        String vendorSt = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter amount or leave blank:");
        String amountSt = scanner.nextLine().trim();

        System.out.println("\nMatching Entries:\n");

        try {
            FileReader fr = new FileReader("MoodLedgerTransactions.csv");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (line.contains("Date")) continue;

                String[] parts = line.split("\\|");
                if (parts.length < 5)
                    continue;
                if (parts[0].toLowerCase().contains("date")) continue;

                String date = parts[0].trim().replaceAll("\\s", "");
                String time = parts[1].trim();
                String description = parts[2].trim();
                String vendor = parts[3].trim();
                String amount = parts[4].trim();

                LocalDate entryDate = LocalDate.parse(date);
                LocalDate start = startDate.isEmpty() ? null : LocalDate.parse(startDate);
                LocalDate end = endDate.isEmpty() ? null : LocalDate.parse(endDate);

                boolean matches = true;

                if (start != null && entryDate.isBefore(start)) matches = false;
                if (end != null && entryDate.isAfter(end)) matches = false;
                if (!descriptionSt.isEmpty() && !description.toLowerCase().contains(descriptionSt)) matches = false;
                if (!vendorSt.isEmpty() && !vendor.toLowerCase().contains(vendorSt)) matches = false;
                if (!amountSt.isEmpty() && !amount.toLowerCase().contains(amountSt)) matches = false;

                if (matches) {
                    Transactions entry = new Transactions(date, time, description, vendor, Float.parseFloat(amount));
                    System.out.println("Custom Match Found: " + entry);
                }
            }

            br.close();
            fr.close();
            System.out.println("\nPress Enter to return to Reports Menu");
            scanner.nextLine();


        } catch (IOException e) {
            System.out.println("I can't read this!");
        }
    }





}

