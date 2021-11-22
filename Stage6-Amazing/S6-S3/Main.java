package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<String> properties = Arrays.asList("even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny");

    public static void main(String[] args) {
        boolean shouldContinue = true;
        Scanner scanner = new Scanner(System.in);
        displayMenu();

        while(shouldContinue) {
            System.out.println("Enter a request:");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");

            if (inputArray.length == 1) {
                long number = Long.parseLong(inputArray[0]);
                System.out.println();

                if (number == 0) {
                    System.out.println("Goodbye!");
                    shouldContinue = false;
                } else if (isNatural(number)) {
                    System.out.println("Properties of " + number);
                    System.out.println("\teven: " + isEven(number));
                    System.out.println("\todd: " + !isEven(number));
                    System.out.println("\tbuzz: " + isBuzz(number));
                    System.out.println("\tduck: " + isDuck(number));
                    System.out.println("\tpalindromic: " + isPalindromic(number));
                    System.out.println("\tgapful: " + isGapful(number));
                    System.out.println("\tspy: " + isSpy(number));
                    System.out.println("\tsquare: " + isSquare(number));
                    System.out.println("\tsunny: " + isSunny(number));
                    System.out.println();
                } else {
                    System.out.println("The first parameter should be a natural number or zero.\n");
                }
            } else if (inputArray.length == 2) {
                long firstParameter = Long.parseLong(inputArray[0]);
                long secondParameter = Long.parseLong(inputArray[1]);

                if (isNatural(firstParameter) && isNatural(secondParameter)) {
                    printSecondRequest(firstParameter, secondParameter);
                    System.out.println();
                } else if (!isNatural(firstParameter) && isNatural(secondParameter)) {
                    System.out.println("The first parameter should be a natural number or zero.\n");
                } else if (isNatural(firstParameter) && !isNatural(secondParameter)) {
                    System.out.println("The second parameter should be a natural number.\n");
                } else {
                    System.out.println("Both parameters should be natural numbers.\n");
                }
            } else if (inputArray.length == 3) {
                long firstParameter = Long.parseLong(inputArray[0]);
                long secondParameter = Long.parseLong(inputArray[1]);

                String indicatedProperty = inputArray[2].toLowerCase();
                printThirdRequest(firstParameter, secondParameter, indicatedProperty);
            } else if (inputArray.length == 4) {
                long firstParameter = Long.parseLong(inputArray[0]);
                long secondParameter = Long.parseLong(inputArray[1]);
                String firstProperty = inputArray[2].toLowerCase();
                String secondProperty = inputArray[3].toLowerCase();

                printFourthRequest(firstParameter, secondParameter, firstProperty, secondProperty);
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "\t* the first parameter represents a starting number;\n" +
                "\t* the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    public static List<String> checkProperties(long number) {
        List<String> resultsList = new ArrayList<>();
        if (isBuzz(number)) {
            resultsList.add("buzz");
        }
        if (isDuck(number)) {
            resultsList.add("duck");
        }
        if (isPalindromic(number)) {
            resultsList.add("palindromic");
        }
        if (isGapful(number)) {
            resultsList.add("gapful");
        }
        if (isEven(number)) {
            resultsList.add("even");
        }
        if (!isEven(number)) {
            resultsList.add("odd");
        }
        if (isSpy(number)) {
            resultsList.add("spy");
        }
        if (isSquare(number)) {
            resultsList.add("square");
        }
        if (isSunny(number)) {
            resultsList.add("sunny");
        }

        return resultsList;
    }

    public static void printProperties(long number) {
        List<String> resultsList = checkProperties(number);
        String result = String.join(", ", resultsList);
        System.out.println("\t" + number + " is " + result);
    }

    public static void printSecondRequest(long firstParameter, long secondParameter) {
        long lastNumber = firstParameter + secondParameter;
        long checkingNumber = firstParameter;

        while (checkingNumber < lastNumber) {
            printProperties(checkingNumber);
            checkingNumber++;
        }
    }

    public static void printThirdRequest(long firstParameter, long secondParameter, String property) {
        long checkingNumber = firstParameter;
        long counter = 0;

        if (!properties.contains(property)) {
            System.out.println("The property [" + property.toUpperCase() + "] is wrong.\n" +
                    "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
        } else {
            while (counter < secondParameter) {
                if (checkProperties(checkingNumber).contains(property)) {
                    printProperties(checkingNumber);
                    counter++;
                }
                checkingNumber++;
            }
        }
    }

    public static void printFourthRequest(long firstParameter, long secondParameter, String firstProperty, String secondProperty) {
        List<String> userProperties = Arrays.asList(firstProperty, secondProperty);
        long checkingNumber = firstParameter;
        long counter = 0;

        if (!properties.contains(firstProperty) && !properties.contains(secondProperty)) {
            System.out.println("The properties [" + firstProperty.toUpperCase() + ", " + secondProperty.toUpperCase() + "] are wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
        } else if (!properties.contains(firstProperty) && properties.contains(secondProperty)) {
            System.out.println("The property [" + firstProperty.toUpperCase() + "] is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
        } else if (properties.contains(firstProperty) && !properties.contains(secondProperty)) {
            System.out.println("The property [" + secondProperty.toUpperCase() + "] is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
        } else if (userProperties.contains("even") && userProperties.contains("odd")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                    "There are no numbers with these properties.");
        } else if (userProperties.contains("duck") && userProperties.contains("spy")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                    "There are no numbers with these properties.");
        } else if (userProperties.contains("sunny") && userProperties.contains("square")) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]\n" +
                    "There are no numbers with these properties.");
        } else {
            while (counter < secondParameter) {
                if (checkProperties(checkingNumber).contains(firstProperty) && checkProperties(checkingNumber).contains(secondProperty)) {
                    printProperties(checkingNumber);
                    counter++;
                }
                checkingNumber++;
            }
        }
    }

    public static boolean isNatural(long number) {
        return number >= 1;
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isBuzz(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    public static boolean isDuck(long number) {
        while (number != 0) {
            if (number % 10 == 0) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

    public static boolean isPalindromic(long number) {
        long temp = number;
        long reversed = 0;
        long rest;

        while (temp > 0) {
            rest = temp % 10;
            reversed = reversed * 10 + rest;
            temp /= 10;
        }

        return reversed == number;
    }

    public static boolean isGapful(long number) {
        String numberString = String.valueOf(number);

        if (numberString.length() < 3) {
            return false;
        }

        String[] strArray = numberString.split("");
        long divider = Long.parseLong((strArray[0] + strArray[strArray.length - 1]));

        return number % divider == 0;
    }

    public static boolean isSpy(long number) {
        String numberString = String.valueOf(number);
        String[] strArray = numberString.split("");
        int sum = 0;
        int product = 1;

        for (String s : strArray) {
            sum += Long.parseLong(s);
            product *= Long.parseLong(s);
        }

        return sum == product;
    }

    public static boolean isSquare(long number) {
        double squareRoot = Math.sqrt(number);
        return squareRoot - Math.floor(squareRoot) == 0;
    }

    public static boolean isSunny(long number) {
        return isSquare(number + 1);
    }
}