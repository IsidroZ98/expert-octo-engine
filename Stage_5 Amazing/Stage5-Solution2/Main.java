package numbers;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void printInstruction() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

    public static boolean isGapful(String str, long n) {
        int size = str.length();
        if (size < 3) {
            return false;
        }
        int divisor = Character.getNumericValue(str.charAt(0)) * 10 + Character.getNumericValue(str.charAt(size - 1));
        return n % divisor == 0;
    }

    public static boolean isPalindromic(String str) {
        int size = str.length();
        for (int i = 0; i < size; i++) {
            if (str.charAt(i) != str.charAt(size - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEven(long n) {
        return n % 2 == 0;
    }

    public static boolean isBuzz(long n) {
        return n % 10 == 7 || n % 7 == 0;
    }

    public static boolean isSpy(String num) {
        long sum = 0;
        long mul = 1;
        for (int i = 0; i < num.length(); i++) {
            sum += Character.getNumericValue(num.charAt(i));
            mul *= Character.getNumericValue(num.charAt(i));
        }
        return (sum == mul);
    }

    public static boolean isDuck(String num) {
        return num.contains("0");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printInstruction();

        System.out.print("Enter a request:");
        String[] arr = scanner.nextLine().split(" ");
        int size = arr.length;
        long n = Long.parseLong(arr[0]);
        String num = String.valueOf(n);

        while (n != 0) {
            if (size == 1) {
                if (n < 1) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else {
                    boolean isEven = isEven(n);
                    boolean isBuzz = isBuzz(n);
                    boolean isDuck = isDuck(num);
                    boolean isPalindromic = isPalindromic(num);
                    boolean isGapful = isGapful(num, n);
                    boolean isSpy = isSpy(num);

                    System.out.println("\tProperties of " + n);
                    System.out.println("\t\tbuzz: " + isBuzz);
                    System.out.println("\t\tduck: " + isDuck);
                    System.out.println("\t\tpalindromic: " + isPalindromic);
                    System.out.println("\t\tgapful: " + isGapful);
                    System.out.println("\t\tspy: " + isSpy);
                    System.out.println("\t\teven: " + isEven);
                    System.out.println("\t\todd: " + !isEven);

                }
                System.out.print("Enter a request:");
                arr = scanner.nextLine().split(" ");
                size = arr.length;
                n = Long.parseLong(arr[0]);
                num = String.valueOf(n);
            } else if (size == 2) {
                long b = Long.parseLong(arr[1]);
                if (b > 0) {
                    for (long i = n; i < n + b; i++) {
                        StringBuilder ans = new StringBuilder();
                        if (isBuzz(i)) {
                            ans.append(" buzz");
                        }
                        if (isDuck(String.valueOf(i))) {
                            ans.append(" duck");
                        }
                        if (isPalindromic(String.valueOf(i))) {
                            ans.append(" palindromic");
                        }
                        if (isGapful(String.valueOf(i), i)) {
                            ans.append(" gapful");
                        }
                        if (isSpy(String.valueOf(i))) {
                            ans.append(" spy");
                        }
                        if (isEven(i)) {
                            ans.append(" even");
                        } else {
                            ans.append(" odd");
                        }
                        String a = String.valueOf(ans);
                        String[] ansarr = a.split(" ");
                        a = "";
                        for (int j = 1; j < ansarr.length; j++) {
                            a = a + ansarr[j] + ", ";
                        }
                        int size1 = ans.length();
                        System.out.println(i + " is " + ans.substring(0, size1));
                    }
                } else {
                    System.out.print("second parameter should be a natural number");
                }
                System.out.print("Enter a request:");
                arr = scanner.nextLine().split(" ");
                size = arr.length;
                n = Long.parseLong(arr[0]);
                num = String.valueOf(n);
            } else {
                long b = Long.parseLong(arr[1]);
                String c = arr[2];
                int index = -1;
                String[] availableProperties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY"};
                for (int i = 0; i < 7; i++) {
                    if (c.equals(availableProperties[i])) {
                        index = i;
                    }
                }
                if (index == -1) {
                    System.out.println("The property [" + c + "] is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
                } else {
                    for (long i = n; true; i++) {
                        StringBuilder ans = new StringBuilder();
                        if (isBuzz(i)) {
                            ans.append(" BUZZ");
                        }
                        if (isDuck(String.valueOf(i))) {
                            ans.append(" DUCK");
                        }
                        if (isPalindromic(String.valueOf(i))) {
                            ans.append(" PALINDROMIC");
                        }
                        if (isGapful(String.valueOf(i), i)) {
                            ans.append(" GAPFUL");
                        }
                        if (isSpy(String.valueOf(i))) {
                            ans.append(" SPY");
                        }
                        if (isEven(i)) {
                            ans.append(" EVEN");
                        } else {
                            ans.append(" ODD");
                        }
                        String a = String.valueOf(ans);
                        String[] ansarr = a.split(" ");
                        a = "";
                        for (int j = 1; j < ansarr.length; j++) {
                            a = a + ansarr[j] + ", ";
                        }
                        int size1 = ans.length();
                        if (a.contains(c)) {
                            System.out.println(i + " is " + ans.substring(0, size1 - 2));
                            b--;
                        }
                        if (b == 0) {
                            break;
                        }
                    }
                }
                System.out.print("Enter a request:");
                arr = scanner.nextLine().split(" ");
                size = arr.length;
                n = Long.parseLong(arr[0]);
                num = String.valueOf(n);
            }
        }
        System.out.println("Goodbye!");
    }
}