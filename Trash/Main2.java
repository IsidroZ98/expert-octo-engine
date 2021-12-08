package numbers;

import java.util.Scanner;

public class Main extends Notice {
    static boolean isExit = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printWelcome();
        printInstructions();
        do {
            printRequestAsk();
            String string = scanner.nextLine();
            String[] numbers = string.split(string.replace(" ", "").isEmpty() ? "" : " ");//solved "space input problem"
            long num1 = NumberProperties.getNumeric(numbers[0]);
            long num2 = NumberProperties.getNumeric(numbers.length > 1 ? numbers[1] : "0");
            inputProcessing(num1, num2, numbers);
        } while (!isExit);
    }

    private static void inputProcessing(long num1, long num2, String[] numbers) {
        System.out.println();
        if (num1 == 0) {
            printBye();
            isExit = true;
        } else if (numbers[0].isEmpty()) {
            printInstructions();
        } else if (num1 < 0) {
            printError1();
        } else if (num2 < 0) {
            printError2();
        } else if (num2 > 0) {
            for (long i = num1; i < num1 + num2; i++) {
                printProperties(i, num2);
            }
        } else {
            printProperties(num1, num2);
        }
    }
}


class NumberProperties {    //this class parses the properties of numbers, then returns a value.

    public static long getNumeric(String string) {
         long number;
         try {
            number = Long.parseLong(string);
         } catch (Exception e) {
            number = -1;
         }
         return number;
     }

    public static boolean isPalindrome(long number) {
        String string = String.valueOf(number);
        boolean palindromic = true;
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
                palindromic = false;
                break;
            }
        }
        return palindromic;
    }

    public static boolean isGapful(long number) {
            String[] string = String.valueOf(number).split("");
            String concat = string[0] + string[string.length - 1];
            return number / 100 > 0 && number % Integer.parseInt(concat) == 0;
    }

    public static boolean isEven(long number) {
        return (number % 2 == 0);
    }

    public static boolean isBuzz(long number) {
        return (number % 7 == 0 || number % 10 == 7);
    }

    public static boolean isDuck(long number) {
        return String.valueOf(number).substring(1).contains("0");
    }
}


class Notice extends NumberProperties {   //this class contains all messages and prints them.
    public static void printWelcome() {
        System.out.println("Welcome to Amazing Numbers!" + "\n");
    }

    public static void printBye() {
        System.out.println("Goodbye!");
    }

    public static void printRequestAsk() {
        System.out.print("\n" + "Enter a request: ");
    }

    public static void printError1() {
        System.out.println("\n" + "The first parameter should be a natural number or zero.");
    }

    public static void printError2() {
        System.out.println("The second parameter should be a natural number.");
    }

    public static void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameters show how many consecutive numbers are to be processed;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }

    public static void printProperties(long num1, long num2) {
        if(num2 == 0) {
            System.out.println("Properties of " + num1);
            System.out.println("        even: " + isEven(num1));
            System.out.println("         odd: " + !isEven(num1));
            System.out.println("        buzz: " + isBuzz(num1));
            System.out.println("        duck: " + isDuck(num1));
            System.out.println(" palindromic: " + isPalindrome(num1));
            System.out.println("      gapful: " + isGapful(num1));
        } else {
            String properties = (isEven(num1) ? "even, " : "")
                              + (!isEven(num1) ? "odd, " : "")
                              + (isBuzz(num1) ? "buzz, " : "")
                              + (isDuck(num1) ? "duck, " : "")
                              + (isPalindrome(num1) ? "palindromic, " : "")
                              + (isGapful(num1) ? "gapful, " : "");
            System.out.print("             " + num1 + " is ");
            System.out.println(properties.substring(0, properties.length() - 2));
        }
    }
}