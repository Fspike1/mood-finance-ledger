package moodledger;
import java.util.Scanner;
public class Demos {
    public static void main(String[] args) {
        System.out.println("==== DEMO: Using OR (||) in a loop ====");
        String[] lines = {
                "2025-04-25 | 07:04:33 | Bulk Mood Journal | EnhancedMoods | -130.47",
                "2025-04-24 | 09:07:44 | Quiet Flame Kit Paid | Kai C. | 60.74",
                "2025-04-24 | 14:45:47 | Mood Journal Paid | Roux V. | 15.99"
        };

        for (String line : lines) {
            // You used this condition to filter entries by specific vendors
            if (line.contains("Kai C.") || line.contains("Roux V.")) {
                System.out.println("Matched line with OR condition: " + line);
            } else {
                System.out.println("Unmatched line: " + line);
            }


        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main loop
        while (running) {
            System.out.println("💡 MAIN MENU");
            System.out.println("1 - Enter Submenu");
            System.out.println("2 - Exit App");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // This loop keeps the user inside the submenu
                    boolean inSubmenu = true;
                    while (inSubmenu) {
                        System.out.println("\nSUBMENU");
                        System.out.println("A - Do Something Cool");
                        System.out.println("B - Go Back");
                        System.out.print("Enter A or B: ");
                        String subChoice = scanner.next().toUpperCase();

                        if (subChoice.equals("A")) {
                            System.out.println("You did something cool!");
                        } else if (subChoice.equals("B")) {
                            System.out.println("Returning to main menu...");
                            inSubmenu = false; // this exits the inner loop
                        } else {
                            System.out.println("Invalid input. Try again.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Goodbye!");
                    running = false; // this exits the outer loop
                    break;

                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }

        scanner.close();
    }


    }













