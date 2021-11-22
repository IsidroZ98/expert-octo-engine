package numbers;

import java.util.Scanner;

public class Main {
    static void suppportedRequests(){
        System.out.println("\nSupported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }
    static void availableProperties(){
        System.out.println("\nAvailable properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]\n");
    }
    static void printProps(long i){
        String[] props = new String[7];
        int j = 0;
        if (isBuzz(i)) {
            props[j] = "buzz";
            j++;
        }
        if (isDuck(i)) {
            props[j] = "duck";
            j++;
        }
        if (isPalindrome(i)) {
            props[j] = "palindromic";
            j++;
        }
        if (isGapFul(i)) {
            props[j] = "gapful";
            j++;
        }
        if (isSpy(i)) {
            props[j] = "spy";
            j++;
        }
        if (isEven(i)) {
            props[j] = "even";
            j++;
        }
        if (isOdd(i)) {
            props[j] = "odd";
        }
        StringBuilder strprops = new StringBuilder();
        for (String s : props) {
            if (s != null) {
                strprops.append(s).append(", ");
            }
        }
        System.out.print("\n             " + i + " is " + strprops.substring(0, strprops.length() - 2));
    }
    static boolean isEven(long num){
        return num % 2 == 0;
    }
    static boolean isOdd(long num){
        return num % 2 != 0;
    }
    static boolean isBuzz(long num){
        long lastDigit = num%10;
        if (num > 0) {
            return num % 7 == 0 || lastDigit == 7;
        } else {
            return false;
        }
    }
    static boolean isDuck(long num){
        long temp=num;
        while(temp>0){
            long rem=temp%10;
            temp/=10;
            if(rem==0){
                return true;
            }
        }
        return false;
    }
    static boolean isPalindrome(long num) {
        long temp;
        temp = num;
        long rev = 0;
        while (temp > 0){
            long rem;
            rem = temp % 10;
            rev = rev * 10 + rem;
            temp /= 10;
        }
        return rev == num;
    }
    static boolean isGapFul(long num){
        StringBuilder str = new StringBuilder(String.valueOf(num));
        if (str.length() >= 3) {
            String s = str.charAt(0) + String.valueOf(str.charAt(str.length() - 1));
            int mod = Integer.parseInt(s);
            return num % mod == 0;
        } else {
            return false;
        }
    }
    static boolean isSpy(long num) {
        long sum = 0;
        long mul = 1;
        while (num > 0) {
            long rem = num % 10;
            sum += rem;
            mul *= rem;
            num /= 10;
        }
        return sum == mul;
    }
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        suppportedRequests();
        while (true) {
            System.out.print("Enter a request: ");
            String[] string;
            string = sc.nextLine().split(" ");
            if (string.length == 1){
                if (string[0].equals("")){
                    suppportedRequests();
                } else {
                    try {
                        long num = Long.parseLong(string[0]);
                        if (num == 0) {
                            System.out.println("\nGood bye!");
                            System.exit(0);
                        } else if (num < 0) {
                            System.out.println("\nThe first parameter should be a natural number or zero.\n");
                        } else {
                            System.out.println("\nProperties of " + num);
                            System.out.println("        buzz: " + isBuzz(num));
                            System.out.println("        duck: " + isDuck(num));
                            System.out.println(" palindromic: " + isPalindrome(num));
                            System.out.println("      gapful: " + isGapFul(num));
                            System.out.println("         spy: " + isSpy(num));
                            System.out.println("        even: " + isEven(num));
                            System.out.println("         odd: " + isOdd(num));
                            System.out.println();
                        }
                    } catch (Exception e) {
                        System.out.println("\nThe first parameter should be a natural number or zero.\n");
                    }
                }
            } else if (string.length == 2) {
                try {
                    long num = Long.parseLong(string[0]);
                    long i = num;
                    try {
                        long end = Long.parseLong(string[1]);
                        if (num < 0) {
                            System.out.println("\nThe first parameter should be a natural number or zero.\n");
                        } else if (end < 0) {
                            System.out.println("\nThe second parameter should be a natural number or zero.\n");
                        } else {
                            while (i < num + end) {
                                String[] props = new String[7];
                                int j = 0;
                                if (isBuzz(i)) {
                                    props[j] = "buzz";
                                    j++;
                                }
                                if (isDuck(i)) {
                                    props[j] = "duck";
                                    j++;
                                }
                                if (isPalindrome(i)) {
                                    props[j] = "palindromic";
                                    j++;
                                }
                                if (isGapFul(i)) {
                                    props[j] = "gapful";
                                    j++;
                                }
                                if (isSpy(i)){
                                    props[j] = "spy";
                                    j++;
                                }
                                if (isEven(i)) {
                                    props[j] = "even";
                                    j++;
                                }
                                if (isOdd(i)) {
                                    props[j] = "odd";
                                }
                                StringBuilder strprops = new StringBuilder();
                                for (String s : props) {
                                    if (s != null) {
                                        strprops.append(s).append(", ");
                                    }
                                }
                                System.out.print("\n             " + i + " is " + strprops.substring(0, strprops.length() - 2));
                                i++;
                            }
                            System.out.println("\n");
                        }
                    } catch (Exception e){
                        System.out.println("\nThe second parameter should be a natural number or zero.");
                    }
                } catch (Exception e){
                    System.out.println("\nThe first parameter should be a natural number or zero.\n");
                }
            } else {
                boolean available = false;
                String[] availableProps = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD"};
                for (String a : availableProps) {
                    if (a.equals(string[2].toUpperCase())){
                        available = true;
                        break;
                    }
                }
                if (available) {
                    try {
                        long startingRange = Long.parseLong(string[0]);
                        try {
                            long availableRange = Long.parseLong(string[1]);
                            if (startingRange < 0) {
                                System.out.println("\nThe first parameter should be a natural number or zero.\n");
                            } else if (availableRange < 0) {
                                System.out.println("\nThe second parameter should be a natural number or zero.\n");
                            } else {
                                long checkSecondParameter=0;
                                long i = startingRange;
                                switch (string[2].toUpperCase()) {
                                    case "BUZZ":
                                        while (checkSecondParameter < availableRange) {
                                            if (isBuzz(i)) {
                                                checkSecondParameter++;
                                                printProps(i);
                                            }
                                            i++;
                                        }
                                        break;
                                    case "SPY":
                                        while (checkSecondParameter < availableRange) {
                                            if (isSpy(i)) {
                                                checkSecondParameter++;
                                                printProps(i);
                                            }
                                            i++;
                                        }
                                        break;
                                    case "EVEN":
                                        while (checkSecondParameter < availableRange) {
                                            if (isEven(i)) {
                                                checkSecondParameter++;
                                                printProps(i);
                                            }
                                            i++;
                                        }
                                        break;
                                    case "ODD":
                                        while (checkSecondParameter < availableRange) {
                                            if (isOdd(i)) {
                                                checkSecondParameter++;
                                                printProps(i);
                                            }
                                            i++;
                                        }
                                        break;
                                    case "DUCK":
                                        while (checkSecondParameter < availableRange) {
                                            if (isDuck(i)) {
                                                checkSecondParameter++;
                                                printProps(i);
                                            }
                                            i++;
                                        }
                                        break;
                                    case "PALINDROMIC":
                                        while (checkSecondParameter < availableRange) {
                                            if (isPalindrome(i)) {
                                                checkSecondParameter++;
                                                printProps(i);
                                            }
                                            i++;
                                        }
                                        break;
                                    default:  // Gapful
                                        while (checkSecondParameter < availableRange) {
                                            if (isGapFul(i)) {
                                                checkSecondParameter++;
                                                printProps(i);
                                            }
                                            i++;
                                        }
                                        break;
                                }
                                System.out.println("\n");
                            }
                        } catch (Exception e){
                            System.out.println("\nThe second parameter should be a natural number or zero.");
                        }
                    } catch (Exception e){
                        System.out.println("\nThe first parameter should be a natural number or zero.\n");
                    }
                } else {
                    System.out.println("\nThe property ["+string[2].toUpperCase()+"] is wrong.\n" +
                            "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]\n");
                }
            }
        }
    }
}
