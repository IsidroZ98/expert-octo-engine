package numbers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NumberController {

    private final NumberProperties properties;
    private final Scanner scanner;

    public NumberController(NumberProperties properties, Scanner scanner) {
        this.properties = properties;
        this.scanner = scanner;
    }

    public void run() {
        boolean isRunning = true;

        List<String> properties = Arrays.asList(
                "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD"
        );

        printGreeting();
        printMenu();

        while (isRunning) {
            System.out.print("Enter a request: ");
            String[] input = scanner.nextLine().split("\\s+");

            //check for empty input
            if (input[0].isEmpty()) {
                printMenu();
                continue;
            }
            //check to make sure first argument is int
            if (!input[0].matches("\\d+")) {
                System.out.println("\nThe first parameter should be a natural number or zero.\n");
                continue;
            }

            //check to make sure second argument is int
            if (input.length == 2 && (!input[1].matches("\\d+") || Long.parseLong(input[1]) < 0)) {
                System.out.println("\nThe second parameter should be a natural number.\n");
                continue;
            }

            //check if third argument, if we have it, is a valid property
            if (input.length == 3 && !properties.contains(input[2].toUpperCase())) {
                System.out.println("\nThe property [" + input[2] + "] is wrong.\n" +
                        "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]\n");
                continue;
            }

            long number = Long.parseLong(input[0]);

            //check if user wants to quit
            if (number == 0) {
                isRunning =false;
                System.out.println("\nGoodbye!");
                continue;
            }

            //get number properties
            if (input.length == 1) {
                printNumberProperties(number);
            } else if (input.length == 2) {
                int range = Integer.parseInt(input[1]);
                printRangeOfProperties(number, range);
            } else if (input.length == 3) {
                int howMany = Integer.parseInt(input[1]);
                String property = input[2].toLowerCase();
                printSpecificRangeOfProperties(number, howMany, property);
            }
        }
    }

    private void printNumberProperties(long num) {
        boolean isEven = properties.isEven(num);
        boolean isOdd = !isEven;
        boolean isBuzz = properties.isBuzzNumber(num);
        boolean isDuck = properties.isDuckNumber(num);
        boolean isPalindromic = properties.isPalindromicNumber(num);
        boolean isGapful = properties.isGapfulNumber(num);
        boolean isSpy = properties.isSpyNumber(num);

        System.out.println("\nProperties of " + num + "\n" +
                "        even: " + isEven + "\n" +
                "         odd: " + isOdd + "\n" +
                "        buzz: " + isBuzz + "\n" +
                "        duck: " + isDuck + "\n" +
                " palindromic: " + isPalindromic + "\n" +
                "      gapful: " + isGapful + "\n" +
                "         spy: " + isSpy + "\n"
        );

    }

    private void printRangeOfProperties(long num, int range) {
        System.out.println();
        long upTo = num + range;

        while (num < upTo) {
            String numberProperties = properties.getProperties(num);
            System.out.println(numberProperties);
            num++;
        }

        System.out.println();
    }

    private void printSpecificRangeOfProperties(long num, int howMany, String property) {
        System.out.println();
        int found = 0;

        while (found < howMany) {
            String numberProperties = properties.getProperties(num);
            if (numberProperties.contains(property)) {
                System.out.println(numberProperties);
                found++;
            }

            num++;
        }

        System.out.println();
    }


    private void printGreeting() {
        System.out.println("Welcome to Amazing Numbers!");
    }

    private void printMenu() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }


}