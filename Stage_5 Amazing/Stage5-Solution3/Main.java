package numbers;

import java.util.*;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

abstract class NumCheck {
    abstract boolean checkNumber(long number);
}
class NumException extends Throwable {
    String str;
    NumException(String str) {
        this.str = str;
    }
    @Override
    public String getMessage() {
        return str;
    }
}

class AmazingNumber {
    private final Scanner sc;
    private long num;
    private final HashMap<String, NumCheck> checkers;
    public AmazingNumber(Scanner sc) {
        this.sc = sc;
        checkers = new HashMap<>() {
            {
                put("EVEN", new NumCheck() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 0;
                    }
                });
                put("ODD", new NumCheck() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 1;
                    }
                });
                put("BUZZ", new NumCheck() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 7 == 0 || number % 10 == 7;
                    }
                });
                put("DUCK", new NumCheck() {
                    @Override
                    public boolean checkNumber(long number) {
                        return Long.toString(number).contains("0");
                    }
                });
                put("PALINDROMIC", new NumCheck() {
                    @Override
                    public boolean checkNumber(long number) {
                        StringBuilder sb = new StringBuilder(Long.toString(number));
                        return sb.toString().equals(sb.reverse().toString());
                    }
                });
                put("GAPFUL", new NumCheck() {
                    @Override
                    boolean checkNumber(long number) {
                        String str = Long.toString(number);
                        if (str.length() < 3) {
                            return false;
                        } else {
                            long num = Long.parseLong(str.charAt(0) + ""
                                    + str.charAt(str.length() - 1));
                            return number % num == 0;
                        }
                    }
                });
                put("SPY", new NumCheck() {
                    @Override
                    boolean checkNumber(long number) {
                        long sum = 0L;
                        long product = 1L;
                        for (char ch : String.valueOf(number).toCharArray()) {
                            int num = ch - '0';
                            sum += num;
                            product *= num;
                        }
                        return sum == product;
                    }
                });
            }
        };
    }
    private void printRules() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }
    private byte processRequest() throws NumException {
        System.out.println("Enter a request:");
        String str = sc.nextLine().toUpperCase();
        if (str.trim().length() == 0) {
            printRules();
            return -1;
        }
        String[] parts = str.split("\\s+");
        try {
            num = Long.parseLong(parts[0]);
            if (num < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new NumException("The first parameter should be a natural number or zero.");
        }
        if (num == 0) {
            return 0;
        }
        if (parts.length == 1) {
            printInfoSingle();
            return 1;
        } else {
            try {
                long n2 = Long.parseLong(parts[1]);
                if (n2 < 0) {
                    throw new Exception();
                }
                if (parts.length == 2) {
                    printInfoDouble(n2);
                } else {
                    printContainsOrNot(n2, Arrays.stream(parts)
                            .skip(2).toArray(String[]::new));
                }
            } catch (Exception e) {
                throw new NumException("The second parameter should be a natural number.");
            }
            return 2;
        }

    }
    private void formatProperty(String name, NumCheck check) {
        System.out.printf("%12s: %s%n", name, check.checkNumber(num));
    }
    private void printDoubleOrMoreFormatted(String accepted) {
        System.out.println(num + " is " + accepted.substring(1, accepted.length() - 1));
    }
    private String getOkayDoubleOrMore() {
        List<String> strings = new ArrayList<>();
        for (String check : checkers.keySet()) {
            if (checkers.get(check).checkNumber(num)) {
                strings.add(check);
            }
        }
        return strings.toString();
    }
    private void printInfoDouble(long up) {
        for (long i = 0; i < up; i++) {
            printDoubleOrMoreFormatted(getOkayDoubleOrMore());
            ++num;
        }
    }
    private void printContainsOrNot(long total, String ...constraints) throws NumException {
        if (!checkers.containsKey(constraints[0])) {
            throw new NumException("The property [" + constraints[0] + "] is wrong.\n" +
                    "Available properties: " + checkers.keySet());
        }
        int count = 1;
        while (count <= total) {
            if (checkers.get(constraints[0]).checkNumber(num)) {
                printDoubleOrMoreFormatted(getOkayDoubleOrMore());
                ++count;
            }
            ++num;
        }

    }
    private void printInfoSingle() {
        System.out.printf("Properties of %d%n", num);
        checkers.forEach(this::formatProperty);
    }
    public void run(){
        System.out.println("Welcome to Amazing Numbers!");
        printRules();
        byte req = -1;
        do {
            try {
                req = processRequest();
            } catch (NumException e) {
                System.out.println(e.getMessage());
            }
        } while (req != 0);
        System.out.println("Goodbye!");
    }
}
public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        AmazingNumber number = new AmazingNumber(sc);
        number.run();
    }
}