import java.util.*;

public class Main {

    boolean running = true;
    HashMap<String, Boolean> props = new HashMap<String, Boolean>();

    long spyProduct = 1;
    long spySum = 0;

    public boolean gapfulChecker(long num, String strNum) {
        String gapfulNumber = "1";
        if (strNum.length() > 2) {
            String a = String.valueOf(strNum.charAt(0));
            String b = String.valueOf(strNum.charAt(strNum.length() - 1));
            gapfulNumber = a.concat(b);
        }
        return num > 99 && num % Long.parseLong(gapfulNumber) == 0;
    }

    public boolean spyChecker(String strNum) {
        spyProduct = 1;
        spySum = 0;
        for (int i = 0; i < strNum.length(); i++) {
            long num = Long.parseLong(String.valueOf(strNum.charAt(i)));
            spyProduct *= num;
            spySum += num;
        }
        return spyProduct == spySum;
    }

    public boolean squareChecker(long num) {
        double sq = Math.sqrt(num);
        boolean square = sq - Math.floor(sq) == 0;
        return square;
    }

    public boolean sunnyChecker(long num) {
        boolean sunny = squareChecker(num + 1);
        return sunny;
    }

    public void addProps(long num) {
        String strNum = String.valueOf(num);
        props.put("even", (num % 2 == 0));
        props.put("odd", (num % 2 == 1));
        props.put("buzz", (num % 10 == 7 || num % 7 == 0));
        props.put("duck", (strNum.contains("0")));
        props.put("palindromic", (strNum.equals(new StringBuilder(strNum).reverse().toString())));
        props.put("gapful", (gapfulChecker(num, strNum)));
        props.put("spy", (spyChecker(strNum)));
        props.put("square", (squareChecker(num)));
        props.put("sunny", (sunnyChecker(num)));
    }

    public void checkNumber(long num) {
        if (num < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }
        if (num == 0) {
            running = false;
            System.out.println("Goodbye!");
            return;
        }
        addProps(num);

        System.out.println("Properties of " + num);
        System.out.println("even: " + props.get("even"));
        System.out.println("odd: " + props.get("odd"));
        System.out.println("buzz: " + props.get("buzz"));
        System.out.println("duck: " + props.get("duck"));
        System.out.println("palindromic: " + props.get("palindromic"));
        System.out.println("gapful: " + props.get("gapful"));
        System.out.println("spy: " + props.get("spy"));
        System.out.println("square: " + props.get("square"));
        System.out.println("sunny: " + props.get("sunny"));
    }

    public void listProps(long num, long count) {
        if (count < 0) {
            System.out.println("The second parameter should be a natural number.");
            return;
        }
        for (long i = num; i < num + count; i++) {
            addProps(i);

            System.out.print(i + " is ");
            System.out.print(props.get("even") ? "even" : "odd");
            System.out.print(props.get("buzz") ? ", buzz" : "");
            System.out.print(props.get("duck") ? ", duck" : "");
            System.out.print(props.get("palindromic") ? ", palindromic" : "");
            System.out.print(props.get("gapful") ? ", gapful" : "");
            System.out.print(props.get("spy") ? ", spy" : "");
            System.out.print(props.get("square") ? ", square" : "");
            System.out.println(props.get("sunny") ? ", sunny" : "");
        }
    }

    public void findNumbersByProp(long num, long count, String prop) {
        long counter = count;
        for (long i = num; counter > 0; i++) {
            addProps(i);
            if (props.get(prop.toLowerCase()) == null) {
                System.out.println("The property [" + prop.toUpperCase() + "] is wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
                return;
            }
            if (props.get(prop.toLowerCase())) {
                listProps(i, 1);
                counter--;
            }
        }
    }

    public void findNumbersBy2props(long num, long count, String prop1, String prop2) {
        addProps(num);
        if (!props.containsKey(prop1.toLowerCase()) && !props.containsKey(prop2.toLowerCase())) {
            System.out.println("The properties [" + prop1.toUpperCase() + "], [" + prop2.toUpperCase() + "] are wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            return;
        } else if (!props.containsKey(prop1.toLowerCase())) {
            System.out.println("The property [" + prop1.toUpperCase() + "] is wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            return;
        } else if (!props.containsKey(prop2.toLowerCase())) {
            System.out.println("The property [" + prop2.toUpperCase() + "] is wrong.");
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");
            return;
        }
        boolean invalid1 = prop1.equalsIgnoreCase("sunny") && prop2.equalsIgnoreCase("square");
        boolean invalid2 = prop1.equalsIgnoreCase("square") && prop2.equalsIgnoreCase("sunny");
        boolean invalid3 = prop1.equalsIgnoreCase("even") && prop2.equalsIgnoreCase("odd");
        boolean invalid4 = prop1.equalsIgnoreCase("odd") && prop2.equalsIgnoreCase("even");
        boolean invalid5 = prop1.equalsIgnoreCase("duck") && prop2.equalsIgnoreCase("spy");
        boolean invalid6 = prop1.equalsIgnoreCase("spy") && prop2.equalsIgnoreCase("duck");
        if (invalid1 || invalid2 || invalid3 || invalid4 || invalid5 || invalid6) {
            System.out.println("The request contains mutually exclusive properties: [" + prop1.toUpperCase() + ", " + prop2.toUpperCase() + "]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        long counter = count;
        for (long i = num; counter > 0; i++) {
            addProps(i);
            if (props.get(prop1.toLowerCase()) && props.get(prop2.toLowerCase())) {
                listProps(i, 1);
                counter--;
            }
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
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
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
            } else if (input.length == 2) {
                obj.listProps(Long.parseLong(input[0]), Long.parseLong(input[1]));
            } else if(input.length == 3) {
                obj.findNumbersByProp(Long.parseLong(input[0]), Long.parseLong(input[1]), input[2]);
            } else {
                obj.findNumbersBy2props(Long.parseLong(input[0]), Long.parseLong(input[1]), input[2], input[3]);
            }
        }
    }
}
