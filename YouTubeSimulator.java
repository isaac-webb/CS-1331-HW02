// I worked on the homework assignment alone, using only course materials.

import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.NumberFormat;
import java.text.DecimalFormat;

/**
 * Simulates your fame as a YouTuber based on subscriber count, videos, and
 * weeks.
 *
 * @author iwebb6
 */

public class YouTubeSimulator {

    /**
     * Responsible for everything: getting user input, running the simulation,
     * and doing all calculations.
     *
     * @param args Command line arguments, all of which will be ignored
     */
    public static void main(String[] args) {
        // Create the Scanner to get user input
        Scanner input = new Scanner(System.in);

        // Create a number formatter
        DecimalFormat df = (DecimalFormat) (NumberFormat.getInstance());
        df.applyPattern("$########0.00");

        // Make a variable to store whether or not to simulate again
        boolean simulate = true;

        // Continue simulating while the user wants to
        while (simulate) {
            // Declare variables that we will need throughout the program
            String name;
            int videos = 0;
            int collabVideos = 0;
            int views = 0;
            int currWeek = 1;
            int subs = -1;
            int weeks = -1;
            int newSubs = 0;
            int newVids = 0;
            int newViews = 0;

            // Prompt the user for their name, subscribers, and number of weeks
            // to simulate
            System.out.print("What is your name? ");
            name = input.nextLine();

            // Catch sneaky TAs
            while (subs < 0) {
                System.out.print("How many subscribers do you have? ");

                try {
                    subs = input.nextInt();
                } catch (InputMismatchException e) {
                    subs = -1;
                }
                input.nextLine();

                // Let the user know they messed up
                if (subs < 0) {
                    System.out.println(
                        "Hey now, that wasn't what I asked for... "
                        + "Nonnegative integers please :)");
                }
            }

            // Sneaky once again... Tsk-tsk
            while (weeks < 0) {
                System.out.print("How many weeks would you like to simulate? ");

                try {
                    weeks = input.nextInt();
                } catch (InputMismatchException e) {
                    weeks = -1;
                }
                input.nextLine();

                // Print out our nice error message
                if (weeks < 0) {
                    System.out.println(
                        "Hey now, that wasn't what I asked for... "
                        + "Nonnegative integers please :)");
                }
            }

            // Begin the simulation
            System.out.printf("\nSimulating %d weeks:\n\n", weeks);
            while (currWeek <= weeks) {
                // Reset all of the weekly variables
                newSubs = 0;
                newVids = 0;
                newViews = 0;

                // Ask how many videos to make, make sure it's 1-5, inclusive
                while (newVids < 1 || newVids > 5) {
                    System.out.print(
                        "How many videos would you like to make during week "
                        + currWeek
                        + " (1-5)? ");

                    // Why even try... :D
                    try {
                        newVids = input.nextInt();
                    } catch (InputMismatchException e) {
                        newVids = -1;
                    }
                    input.nextLine();

                    // Let 'em know
                    if (newVids < 1 || newVids > 5) {
                        System.out.println(
                            "Come on... Integers 1-5 inclusive... pls");
                    }
                }

                // Calculate new subscribers from weekly growth
                newSubs = (int) (subs * 0.02);

                // Check for collaboration week, and do calculations if needed
                if (currWeek % 4 == 0) {
                    int collabSubs = (int) ((subs + newSubs) * 1.2 * 0.3);
                    newSubs += collabSubs;
                    collabVideos += 1;
                    System.out.println(
                        "\nCongratulations on your collab video! You gained "
                        + collabSubs
                        + " subscribers.");
                }

                // Account for the new subscribers, views, and videos
                subs += newSubs;
                newViews = newVids * (int) (subs * 0.6);
                views += newViews + (int) (videos * subs * 0.05);
                videos += newVids;

                // Print out the weekly summary
                System.out.printf("\n%s, here is your weekly summary:\n", name);
                System.out.printf("%13d: New Videos\n", newVids);
                System.out.printf("%13d: New Subscribers\n", newSubs);
                System.out.printf("%13d: Views\n", newViews);
                System.out.printf("%13s: Ad Revenue\n\n",
                                  df.format(newViews * 0.05));

                // Wait for the user to press enter, then increment the week
                if (currWeek != weeks) {
                    System.out.println("Press enter to go to the next week.");
                    input.nextLine();
                }
                currWeek++;
            }

            // Wait for the user to press enter, then print the results
            System.out.println("Press enter to see your total summary.");
            input.nextLine();
            System.out.printf("%s, here is your total summary:\n", name);
            System.out.printf("%13d: Total Videos\n", videos);
            System.out.printf("%13d: Collaboration Videos\n", collabVideos);
            System.out.printf("%13d: Views\n", views);
            System.out.printf("%13d: Subscribers\n", subs);
            System.out.printf("%13s: Ad Revenue\n\n", df.format(views * 0.05));

            // Ask if they want to simulate again
            System.out.println(
                "Would you like to simulate again? Enter \"Y\" if so.");
            simulate = input.nextLine().equalsIgnoreCase("Y");
        }
    }
}
