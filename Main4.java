package numbers;
import java.util.Scanner;

public class Main {

    public static void printInstruction(){
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

    public static boolean isGapful(String str,long n) {
        int size = str.length();
        if (size < 3) {
            return false;
        }
        String divisor = String.valueOf(str.charAt(0)) + String.valueOf(str.charAt(size - 1));
        int a = Integer.parseInt(divisor);
        if (n % a != 0) {
            return false;
        }
        return true;
    }
    public static boolean isPalindromic(String str) {
        int size = str.length();
        for (int i = 0; i < size; i++) {
            if (str.charAt(i) != str.charAt(size - i - 1)){
                return false;
            }
        }
        return true;
    }
    public static boolean isEven(long n) {
        if (n % 2 == 0) {
            return true;
        }
        return false;
    }

    public static boolean isBuzz(long n) {
        if (n % 10 == 7 || n  % 7 == 0) {
            return true;
        }
        return false;
    }

    public static boolean isDuck(long n) {
        if (n % 10 == 7 || n  % 7 == 0) {
            return true;
        }
        return false;
    }

    public static boolean isDuck(String num) {
        if (num.contains("0")) {
            return true;
        }
        return false;
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
            if (size == 1 || Long.parseLong(arr[1]) == 0) {
                if (n < 1) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else {
                    boolean isEven = isEven(n);
                    boolean isBuzz = isBuzz(n);
                    boolean isDuck = isDuck(num);
                    boolean isPalindromic = isPalindromic(num);
                    boolean isGapful = isGapful(num, n);

                    System.out.println("\tProperties of " + n);
                    System.out.println("\t\tbuzz: " + isBuzz);
                    System.out.println("\t\tduck: " + isDuck);
                    System.out.println("\t\tpalindromic: " + isPalindromic);
                    System.out.println("\t\tgapful: " + isGapful);
                    System.out.println("\t\teven: " + isEven);
                    System.out.println("\t\todd: " + !isEven);

                }
                System.out.print("Enter a request:");
                arr = scanner.nextLine().split(" ");
                size = arr.length;
                n = Long.parseLong(arr[0]);
                num = String.valueOf(n);
            } else {
                long b = Long.parseLong(arr[1]);
                if (b > 0) {
                    for (long i = n; i < n + b; i++) {
                        String buzz = "";
                        String duck = "";
                        String even = "";
                        String gap = "";
                        String  palin= "";
                        String ans = "";
                        if (isBuzz(i)) {
                            ans = ans + " buzz";
                        }
                        if (isDuck(String.valueOf(i))) {
                            ans = ans + " duck";
                        }
                        if (isPalindromic(String.valueOf(i))) {
                            ans = ans +  " palindromic";
                        }
                        if (isGapful(String.valueOf(i), i)) {
                            ans = ans + " gapful";
                        }
                        if (isEven(i)) {
                            ans = ans + " even";
                        } else {
                            ans = ans + " odd";
                        }
                        String[] ansarr = ans.split(" ");
                        ans = "";
                        for (int j = 1; j < ansarr.length; j++) {
                            ans = ans + ansarr[j] + ", ";
                        }
                        int size1 = ans.length();
                        System.out.println(i + " is " + ans.substring(0,size1 - 2));
                    }
                    System.out.print("Enter a request:");
                    arr = scanner.nextLine().split(" ");
                    size = arr.length;
                    n = Long.parseLong(arr[0]);
                    num = String.valueOf(n);
                } else {
                    System.out.print("second parameter should be a natural number");
                    System.out.print("Enter a request:");
                    arr = scanner.nextLine().split(" ");
                    size = arr.length;
                    n = Long.parseLong(arr[0]);
                    num = String.valueOf(n);
                }
            }
        }
        System.out.println("Goodbye!");
    }
}
