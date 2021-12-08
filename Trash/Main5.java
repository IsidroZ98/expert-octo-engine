import java.util.*;

public class Main {

    boolean running = true;
    String gapfulNumber = "1";

    public void checkNumber(long num) {
        String strNum = String.valueOf(num);
        if (num < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }
        if (num == 0) {
            running = false;
            System.out.println("Goodbye!");
            return;
        }
        if (strNum.length() > 2) {
            String a = String.valueOf(strNum.charAt(0));
            String b = String.valueOf(strNum.charAt(strNum.length() - 1));
            gapfulNumber = a.concat(b);
        }
        System.out.println("Properties of " + num);
        System.out.println("even: " + (num % 2 == 0));
        System.out.println("odd: " + (num % 2 == 1));
        System.out.println("buzz: " + (num % 10 == 7 || num % 7 == 0));
        System.out.println("duck: " + strNum.contains("0"));
        System.out.println("palindromic: " + strNum.equals(new StringBuilder(strNum).reverse().toString()));
        System.out.println("gapful: " + (num > 99 && num % Long.parseLong(gapfulNumber) == 0));
    }

    public void listProps(long num, long count) {
        if (count < 0) {
            System.out.println("second parameter should be a natural number");
            return;
        }
        for (long i = num; i < num + count; i++) {
            String strNum = String.valueOf(i);
            if (strNum.length() > 2) {
                String a = String.valueOf(strNum.charAt(0));
                String b = String.valueOf(strNum.charAt(strNum.length() - 1));
                gapfulNumber = a.concat(b);
            }
            System.out.print(i + " is ");
            System.out.print((i % 2 == 0) ? "even" : "odd");
            System.out.print((i % 10 == 7 || i % 7 == 0) ? ", buzz" : "");
            System.out.print(strNum.contains("0") ? ", duck" : "");
            System.out.print(strNum.equals(new StringBuilder(strNum).reverse().toString()) ? ", palindromic" : "");
            System.out.println((i > 99 && i % Long.parseLong(gapfulNumber) == 0) ? ", gapful" : "");
        }
    }

    public static void main(String[] args) {

        Main obj = new Main();
        Scanner scanner = new Scanner(System.in);
        String[] input;
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        while (obj.running) {
            System.out.println("Enter a request:");
            try {
                input = scanner.nextLine().split(" ");
            } catch (Exception e) {
                System.out.println("Something went wrong!");
                return;
            }
            if (input.length == 1) {
                obj.checkNumber(Long.parseLong(input[0]));
            } else {
                obj.listProps(Long.parseLong(input[0]), Long.parseLong(input[1]));
            }
        }
    }
}
