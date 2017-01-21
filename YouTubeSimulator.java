/**
 * Created by iwebb on 20JAN17.
 *
 * This program simulates your fame as a YouTuber based on subscriber count,
 * videos, and weeks.
 *
 * I worked on the homework assignment alone, using only course materials.
 */

import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;

class YouTubeSimulator {
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
            int subs = 0;
            int weeks = 0;
            int newSubs = 0;
            int newVids = 0;
            int newViews = 0;

            // Prompt the user for their name, subscribers, and number of weeks
            // to simulate
            System.out.print("What is your name? ");
            name = input.nextLine();
            System.out.print("How many subscribers do you have? ");
            subs = input.nextInt();
            System.out.print("How many weeks would you like to simulate? ");
            weeks = input.nextInt();

            // Begin the simulation
            System.out.printf("\nSimulating %d weeks:\n\n", weeks);
            while (currWeek <= weeks) {
                // Reset all of the weekly variables
                newSubs = 0;
                newVids = 0;
                newViews = 0;

                // Ask the user how many videos they want to make, making sure
                // that they are choosing 1-5, inclusive
                while (newVids < 1 || newVids > 5) {
                    System.out.print(
                        "How many videos would you like to make during week "
                        + currWeek
                        + "? ");
                    newVids = input.nextInt();
                    input.nextLine();
                }

                // Calculate new subscribers from weekly growth
                newSubs = (int) (subs * 0.02);

                // Check to see if it is a collaboration week
                if (currWeek % 4 == 0) {
                    // Calculate the subscribers from collaboration
                    int collabSubs = (int) ((subs + newSubs) * 1.2 * 0.3);
                    newSubs += collabSubs;
                    collabVideos += 1;
                    System.out.println(
                        "\nCongratulations on your collab video! You gained "
                        + collabSubs
                        + " subscribers.");
                }

                // Account for the new subscribers
                subs += newSubs;

                // Calculate new views
                newViews = newVids * (int) (subs * 0.6);

                // Account for new views and view on old videos
                views += newViews + (int) (videos * subs * 0.05);

                // Print out the weekly summary
                System.out.printf("\n%s, here is your weekly summary:\n", name);
                System.out.printf("%13d: New Videos\n", newVids);
                System.out.printf("%13d: New Subscribers\n", newSubs);
                System.out.printf("%13d: Views\n", newViews);
                System.out.printf("%13s: Ad Revenue\n\n",
                                  df.format(newViews * 0.05));

                // Add in new videos and subscribers
                videos += newVids;

                // Wait for the user to press enter before continuing
                if (currWeek != weeks) {
                    System.out.println("Press enter to go to the next week.");
                    input.nextLine();
                }

                // Increment the current week
                currWeek++;
            }

            // Wait for the user to press enter
            System.out.println("Press enter to see your total summary.");
            input.nextLine();

            // Print the final simulation results
            System.out.printf("%s, here is your total summary:\n", name);
            System.out.printf("%13d: Total Videos\n", videos);
            System.out.printf("%13d: Collaboration Videos\n", collabVideos);
            System.out.printf("%13d: Views\n", views);
            System.out.printf("%13d: Subscribers\n", subs);
            System.out.printf("%13s: Ad Revenue\n\n", df.format(views * 0.05));

            // Ask if they want to simulate again
            System.out.println(
                "Would you like to simulate again? Enter \"Y\" if so.");
            simulate = input.nextLine().equals("Y");
        }
    }
}
