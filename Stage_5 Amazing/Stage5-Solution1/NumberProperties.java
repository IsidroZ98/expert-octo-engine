package numbers;

public class NumberProperties {

    //return a string of all properties a number has
    public String getProperties(long num) {
        StringBuilder holder = new StringBuilder();
        holder.append("\t\t").append(num).append(" is ");

        if (isEven(num)) {
            holder.append("even, ");
        }

        if (!isEven(num)) {
            holder.append("odd, ");
        }

        if (isBuzzNumber(num)) {
            holder.append("buzz, ");
        }

        if (isDuckNumber(num)) {
            holder.append("duck, ");
        }

        if (isPalindromicNumber(num)) {
            holder.append("palindromic, ");
        }

        if (isGapfulNumber(num)) {
            holder.append("gapful, ");
        }

        if (isSpyNumber(num)) {
            holder.append("spy, ");
        }

        //remove last , from holder
        holder.setLength(holder.length() - 2);

        return holder.toString();
    }

    //checks if even
    public boolean isEven(long num) {
        return num % 2 == 0;
    }

    //buzz numbers are divisible by 7 or end with 7.
    public boolean isBuzzNumber(long num) {
        boolean isDivisibleBy7 = num % 7 == 0;
        boolean endsIn7 = num % 10 == 7;

        return isDivisibleBy7 || endsIn7;
    }

    //A Duck number is a positive number that contains zeroes
    public boolean isDuckNumber(long num) {
        //loop through and check if a digit is 0
        while (num > 0) {
            long lastDigit = num % 10;
            if (lastDigit == 0 && num > 9) return true;
            num /= 10;
        }

        return false;
    }

    //A Palindromic number is symmetrical; in other words, it stays the same regardless of
    // whether we read it from left or right
    public boolean isPalindromicNumber(long num) {
        char[] holder = String.valueOf(num).toCharArray();
        int start = 0;
        int end = holder.length - 1;

        while (start < end) {
            if (holder[start] != holder[end]) return false;

            start++;
            end--;
        }

        return true;
    }

    //Gapful numbers. It is a number that contains at least 3 digits and is divisible by
    //the concatenation of its first and last digit without a remainder
    public boolean isGapfulNumber(long num) {
        String[] strArray = String.valueOf(num).split("");

        if (strArray.length < 3) {
            return false;
        }

        String firstDigit = strArray[0];
        String lastDigit = strArray[strArray.length - 1];
        String concatenation = firstDigit + lastDigit;
        long toDivideBy = Long.parseLong(concatenation);

        return num % toDivideBy == 0;
    }

    //A number is said to be Spy if the sum of all digits is equal to the product of all digits.
    public boolean isSpyNumber(long num) {
        String[] numArray = String.valueOf(num).split("");

        int product = 1;
        int sum = 0;

        //get product/sum of digits
        for (String strDigit : numArray) {
            int digit = Integer.parseInt(strDigit);
            product *= digit;
            sum += digit;
        }

        return sum == product;
    }

}