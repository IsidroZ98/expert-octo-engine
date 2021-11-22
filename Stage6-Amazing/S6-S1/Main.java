package numbers;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "   * the first parameter represents a starting number;\n" +
                "   * the second parameters show how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n\n");
        while (true) {
            System.out.println("Enter a request:");
            String numbers = scanner.nextLine();
            String[] arr = numbers.split(" ");
            long number = Long.parseLong((String) Array.get(arr, 0));

            if (number == 0) {
                break;
            }
            if (arr.length == 2) {
                lenght2(number, arr);
            } else if (arr.length == 1) {
                lenght1(number);
            } else if (arr.length == 3) {
                lenght3(number, arr);
            } else if (arr.length == 4) {
                lenght4(number, arr);
            }
        }
        System.out.println("Goodbye!");

    }

    public static boolean isNatural(long number) {
        return number >= 1;
    }

    public static boolean isEven(long number) {
        return number % 2 != 1;
    }

    public static boolean isBuzz(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    public static boolean isDuck(long number) {
        long help = number;
        while (help > 0) {
            if (help % 10 == 0) {
                return true;
            }
            help /= 10;
        }
        return false;
    }

    public static boolean isPalindromic(long number) {
        int[] digits = new int[0];
        long help = number;
        while (help > 0) {
            digits = Arrays.copyOf(digits, digits.length + 1);
            digits[digits.length - 1] = (int) (help % 10);
            help /= 10;
        }
        if (digits.length == 1) {
            return true;
        }
        for (int i = 0; i < digits.length / 2; i++) {
            if (digits[i] != digits[digits.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGapful(long number) {
        String x = Long.toString(number);
        if (x.length() < 3) {
            return false;
        } else {
            String concetr = Character.toString(x.charAt(0)) + Character.toString(x.charAt(x.length() - 1));
            long concentration = Long.parseLong(concetr);
            return number % concentration == 0;
        }

    }

    public static boolean isSpy(long number) {
        String x = Long.toString(number);
        Long y = Long.parseLong(x);
        long sum = 0;
        long product = 1;

        for (int i = 0; i < x.length(); i++) {
            sum += y % 10;
            product = product * (y % 10);
            y /= 10;
        }
        return sum == product;
    }

    public static boolean isSquare(long number) {
        double x = (double) number;
        x = Math.sqrt(x);
        return ((x == Math.floor(x)) && !Double.isInfinite(x));
    }

    public static boolean isSunny(long number) {
        long x = number + 1;
        return isSquare(x);
    }



    public static void propertiesOf(long number) {
        if (isEven(number)) {
            System.out.print("even, ");
        }
        if (!isEven(number)) {
            System.out.print("odd, ");
        }
        if (isBuzz(number)) {
            System.out.print("buzz, ");
        }
        if (isDuck(number)) {
            System.out.print("duck, ");
        }
        if (isPalindromic(number)) {
            System.out.print("palindromic, ");
        }
        if (isGapful(number)) {
            System.out.print("gapful, ");
        }
        if (isSpy(number)) {
            System.out.print("spy, ");
        }
        if (isSquare(number)) {
            System.out.print("square, ");
        }
        if (isSunny(number)) {
            System.out.print("sunny, ");
        }
    }

    public static void writeProperties(long number, long numberOf) {
        if (numberOf == 1) {
            System.out.printf("         %d is ", number);
            propertiesOf(number);
            System.out.println();
        } else {
            for (long i = number; i < number + numberOf; i++) {
                System.out.printf("         %d is ", i);
                propertiesOf(i);
                System.out.println();
            }
        }
    }

    public static boolean whichProperty(long number, String property) {
        switch (property) {
            case "EVEN":
                return isEven(number);
            case "ODD":
                return !isEven(number);
            case "BUZZ":
                return isBuzz(number);
            case "DUCK":
                return isDuck(number);
            case "PALINDROMIC":
                return isPalindromic(number);
            case "GAPFUL":
                return isGapful(number);
            case "SPY":
                return isSpy(number);
            case "SQUARE":
                return isSquare(number);
            case "SUNNY":
                return isSunny(number);
            default:
                System.out.println("The property [" + property + "] is wrong.\n" +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                return false;
        }
    }

    public static void withPropertyVer1 (long number, long numberOf, String property) {
        if (checkIsProperty(property)) {
            int i = 0;
            long help = number;
            while (i < numberOf) {
                if (whichProperty(help, property)) {
                    writeProperties(help, 1);
                    i++;
                }
                help++;
            }
        } else {
            System.out.println("The property [" + property + "] is wrong.\n" +
                    "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
        }
    }

    public static void withPropertyVer2 (long number, long numberOf, String property, String property$) {
        int i = 0;
        long help = number;
        while (i < numberOf) {
            if (whichProperty(help, property) && whichProperty(help, property$)) {
                writeProperties(help, 1);
                i++;
            }
            help++;
        }
    }



    public static boolean checkMutuallyExclusive(String property, String property$) {
        if (property.equals("ODD") && property$.equals("EVEN") || property.equals("EVEN") && property$.equals("ODD")) {
            return false;
        } else if (property.equals("DUCK") && property$.equals("SPY") || property.equals("SPY") && property$.equals("DUCK")) {
            return false;
        }else return (!property.equals("SUNNY") || !property$.equals("SQUARE")) && (!property.equals("SQUARE") || !property$.equals("SUNNY"));
    }

    public static boolean checkIsProperty(String property) {
        String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY"};
        for (String s : properties) {
            if (s.equals(property)) {
                return true;
            }
        }
        return false;
    }



    public static void lenght1(long number) {
        if (isNatural(number)) {
            System.out.println("Properties of " + number);
            System.out.printf("   even: %b%n" +
                            "   odd: %b%n" +
                            "   buzz: %b%n" +
                            "   duck: %b%n" +
                            "   palindromic: %b%n" +
                            "   gapful: %b%n" +
                            "   spy: %b%n" +
                            "   square: %b%n" +
                            "   sunny: %b%n%n"
                    , isEven(number), !isEven(number), isBuzz(number),
                    isDuck(number), isPalindromic(number), isGapful(number),
                    isSpy(number), isSquare(number), isSunny(number));
        } else {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
    }

    public static void lenght2(long number, String[] arr) {
        if (isNatural(number)) {
            long numberOf = Long.parseLong((String) Array.get(arr, 1));
            if (numberOf < 1) {
                System.out.println("The second parameter should be a natural number.");
            } else {
                writeProperties(number, numberOf);
            }
        } else {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
    }

    public static void lenght3(long number, String[] arr) {
        if (isNatural(number)) {
            long numberOf = Long.parseLong((String) Array.get(arr, 1));
            if (numberOf < 1) {
                System.out.println("The second parameter should be a natural number.");
            } else {
                String property = (String) Array.get(arr, 2);
                property = property.toUpperCase(Locale.ROOT);
                withPropertyVer1(number, numberOf, property);
            }
        } else {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
    }

    public static void lenght4(long number, String[] arr) {
        if (isNatural(number)) {
            long numberOf = Long.parseLong((String) Array.get(arr, 1));
            if (numberOf < 1) {
                System.out.println("The second parameter should be a natural number.");
            } else {
                String property = (String) Array.get(arr, 2);
                String property$ = (String) Array.get(arr, 3);
                property = property.toUpperCase(Locale.ROOT);
                property$ = property$.toUpperCase(Locale.ROOT);
                if (!checkIsProperty(property) && !checkIsProperty(property$)) {
                    System.out.println("The properties [" + property + ", " + property$ +"] are wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                } else {
                    if (checkIsProperty(property)) {
                        if (checkIsProperty(property$)) {
                            if (checkMutuallyExclusive(property, property$)) {
                                withPropertyVer2(number, numberOf, property, property$);
                            } else {
                                System.out.println("The request contains mutually exclusive properties: [" + property + ", " + property$ + "]\n" +
                                        "There are no numbers with these properties.");
                            }
                        } else {
                            System.out.println("The property [" + property$ + "] is wrong.\n" +
                                    "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                        }
                    } else {
                        System.out.println("The property [" + property + "] is wrong.\n" +
                                "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                    }
                }
            }
        } else {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
    }
}