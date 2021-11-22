package Numbers;

import java.util.*;

public class Main extends Notice {
    static boolean isExit = false;
    static String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY"};
    static int invalidProperties;
    static boolean incompatibleProperties = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!" + "\n");
        printInstructions();

        while (!isExit) {
            invalidProperties = 0;
            System.out.print("\n" + "Enter a request: ");

            String string = scanner.nextLine();
            String[] inputData = string.split(string.replace(" ", "").isEmpty() ? "" : "\\s+");

            long number = getNumeric(inputData[0]);
            int lengthList = (int) getNumeric(inputData.length > 1 ? inputData[1] : "0");

            String[] enteredProperties = new String[inputData.length < 2 ? 0 : inputData.length - 2];
            int[] indexesProperties = new int[enteredProperties.length];

            for (int i = 0; i < enteredProperties.length; i++) {
                enteredProperties[i] = inputData[i + 2].toUpperCase();
                indexesProperties[i] = getIndexOf(properties, enteredProperties[i], string);
            }
            incompatibleProperties = string.toUpperCase().contains(properties[0]) && string.toUpperCase().contains(properties[1])
                    || string.toUpperCase().contains(properties[3]) && string.toUpperCase().contains(properties[6])
                    || string.toUpperCase().contains(properties[7]) && string.toUpperCase().contains(properties[8]);
            inputProcessing(inputData, number, lengthList, enteredProperties, indexesProperties);
        }
        scanner.close();
    }

    private static void inputProcessing(String[] inputData, long number, int lengthList, String[] enteredProperties, int[] indexesProperties) {
        System.out.println();
        if (number == 0) {
            System.out.println("Goodbye!");
            isExit = true;
        } else if (inputData[0].isEmpty()) {
            printInstructions();
        } else if (number < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else if (lengthList < 0) {
            System.out.println("The second parameter should be a natural number.");
        } else if (enteredProperties.length != 0) {
            if (invalidProperties > 0 || incompatibleProperties) {
                printErrorMessages(enteredProperties, indexesProperties);
            } else {
                long[] listNumbers = getNumbersWithGivenProperties(number, lengthList, indexesProperties);
                for (long listNumber : listNumbers) {
                    printProperties(listNumber, lengthList);
                }
            }
        } else if (lengthList > 0) {
            for (long i = number; i < number + lengthList; i++) {
                printProperties(i, lengthList);
            }
        } else {
            printProperties(number, lengthList);
        }
    }

    public static long getNumeric(String string) {
        long number;
        try {
            number = Long.parseLong(string);
        } catch (Exception e) {
            number = -1;
        }
        return number;
    }

    public static int getIndexOf(String[] properties, String enteredProperty, String string) {
        int indexPropertyName = properties.length;

        for (int i = 0; i < properties.length; i++) {
            indexPropertyName = enteredProperty.equals(properties[i]) ? i : indexPropertyName;
        }


        invalidProperties = indexPropertyName != properties.length ? invalidProperties : invalidProperties + 1;
        return indexPropertyName;
    }

    public static long[] getNumbersWithGivenProperties(long number, int lengthList, int[] indexesProperties) {
        long[] numbers = new long[lengthList];

        for (int i = 0; i < lengthList;) {
            boolean[] propertiesValues = {isEven(number), !isEven(number), isBuzz(number), isDuck(number),
                    isPalindrome(number), isGapful(number), isSpy(number), isSquare(number), isSunny(number)};
            boolean checkProperty = true;
            for (int index : indexesProperties) {
                if (!propertiesValues[index]) {
                    checkProperty = false;
                    break;
                }
            }
            if (checkProperty) {
                numbers[i] = number;
                i++;
            }
            number++;
        }
        return numbers;
    }
}


class NumberProperties {
    protected static boolean isPalindrome(long number) {
        StringBuilder sb = new StringBuilder(String.valueOf(number));
        return sb.substring(0, sb.length() / 2).equals(sb.reverse().substring(0, sb.length() / 2));
    }

    public static boolean isGapful(long number) {
        String[] str = String.valueOf(number).split("");
        return number / 100 > 0 && number % Integer.parseInt(str[0] + str[str.length - 1]) == 0;
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

    public static boolean isSpy(long number) {
        String[] str = String.valueOf(number).split("");
        int nSum = 0;
        int nProd = 1;
        for (String s: str) {
            nSum += Integer.parseInt(s);
            nProd *= Integer.parseInt(s);
        }
        return nSum == nProd;
    }

    public static boolean isSquare(long number) {
        return number % Math.sqrt(number) == 0;
    }

    public static boolean isSunny(long number) {
        return (number + 1) % Math.sqrt(number + 1) == 0;
    }
}




class Notice extends NumberProperties {
    public static void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;\s
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }

    public static void printProperties(long number, int lengthList) {
        boolean[] propertiesValues = {isEven(number), !isEven(number), isBuzz(number), isDuck(number),
                isPalindrome(number), isGapful(number), isSpy(number), isSquare(number), isSunny(number)};
        if(lengthList == 0) {
            System.out.println("Properties of " + number);
            for (int i = 0; i < Main.properties.length; i++) {
                System.out.printf("%12s: %b%n", Main.properties[i].toLowerCase(), propertiesValues[i]);
            }
        } else {
            StringBuilder properties = new StringBuilder();
            for (int i = 0; i < Main.properties.length; i++) {
                properties.append(propertiesValues[i] ? Main.properties[i].toLowerCase() + ", " : "");
            }
            System.out.printf("%16d is %s%n", number, properties.substring(0, properties.length() - 2));
        }
    }

    public static void printErrorMessages(String[] enteredProperties, int[] indexesProperties) {
        for (int i = 0; i < enteredProperties.length; i++) {
            if (Main.incompatibleProperties) {
                System.out.printf("The request contains mutually exclusive properties: %s\nThere are no numbers with these properties.\n",
                        Arrays.toString(enteredProperties));
                break;
            } else if (indexesProperties[i] == Main.properties.length && Main.invalidProperties < 2) {
                System.out.printf("The property [%s] is wrong.\nAvailable properties: %s\n",
                        enteredProperties[i], Arrays.toString(Main.properties));
            } else if (Main.invalidProperties > 1) {
                System.out.printf("The properties %s are wrong.\nAvailable properties: %s\n",
                        Arrays.toString(enteredProperties), Arrays.toString(Main.properties));
                break;
            }
        }
    }
}