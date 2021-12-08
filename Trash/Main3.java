package numbers;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static long num1;
    static long num2;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to Amazing Numbers!");
            System.out.println();
            System.out.println("Supported requests:");
            System.out.println("- enter a natural number to know its properties;");
            System.out.println("- enter two natural numbers to obtain the properties of the list:");
            System.out.println(" * the first parameter represents a starting number;");
            System.out.println(" * the second parameters show how many consecutive numbers are to be processed;");
            System.out.println("- separate the parameters with one space;");
            System.out.println("- enter 0 to exit.");
            do {
                System.out.println("Enter a request: ");
                String input = scanner.nextLine();
                String[] inputs = input.split(" ");
                if (inputs[0].isEmpty()) break;
                try {
                    num1 = Long.parseLong(inputs[0]);
                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                if (num1 < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                if (inputs.length > 1) {
                    num2 = Long.parseLong(inputs[1]);
                    if (num2 < 0) {
                        System.out.println("second parameter should be a natural number");
                        continue;
                    }
                    for (long i = 0; i < num2; i++) {
                        inlineDisplay(num1 + i);
                    }
                } else {
                    if (num1 == 0) System.exit(0);
                    else display(num1);
                }

            } while (true);
        }

    }

    static boolean isEven(long x) {
        return x % 2 == 0;
    }

    static boolean isBuzz(long x) {
        return x % 10 == 7 || x % 7 == 0;
    }

    static String explanation(long x) {
        if (x % 10 == 7 && x % 7 == 0) return x + " is divisible by 7 and ends with 7.";
        else if (x % 10 == 7) return x + " is ends with 7.";
        else if (x % 7 == 0) return x + " is divisible by 7.";
        else return x + " is neither divisible by 7 nor does it end with 7.";
    }

    static void display(long n) {
        System.out.println("Properties of " + n);
        System.out.println("buzz: " + isBuzz(n));
        System.out.println("duck: " + isDuck(n));
        System.out.println("palindromic: " + isPalindromic(n));
        System.out.println("gapful :" + isGapful(n));
        System.out.println("even: " + isEven(n));
        System.out.println("odd: " + !isEven(n));
    }

    static void inlineDisplay(long n) {
        StringBuilder output = new StringBuilder();
        output.append(n + " is ");
        if (isBuzz(n)) output.append("buzz,");
        if (isDuck(n)) output.append("duck,");
        if (isPalindromic(n)) output.append("palindromic,");
        if (isGapful(n)) output.append("gapful,");
        if (isEven(n)) output.append("even");
        else output.append("odd");
        System.out.println(output.toString());
    }

    private static boolean isGapful(long n) {
        String number = String.valueOf(n);
        if (number.length() < 3) return false;
        String gap = new String(number.charAt(0) + "" + number.charAt(number.length() - 1));
        if (n % Integer.parseInt(gap) == 0) {
            return true;
        } else return false;
    }

    private static boolean isPalindromic(long n) {
        String palindromic = String.valueOf(n);
        char[] result = new char[palindromic.length()];
        for (int i = 1; i <= palindromic.length(); i++) {
            result[i - 1] = palindromic.charAt(palindromic.length() - i);
        }
        String check = String.copyValueOf(result);

        return palindromic.equals(check);
    }

    static boolean isDuck(long n) {
        String duck = String.valueOf(n);
        for (int i = 1; i < duck.length(); i++) {
            if (duck.charAt(i) == '0') return true;
        }
        return false;

    }

}
