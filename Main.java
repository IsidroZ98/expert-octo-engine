package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
        while(true) {
            System.out.print("\nEnter a request:");
            String in = sc.nextLine();
            if(in.equals("0")) {
                System.out.println("Goodbye!");
                break;
            }
            try{
                String[] g = in.split(" ");
                long start = Long.parseLong(g[0]);
                if(start < 0) {
                    throw new NumberFormatException();
                }
                int i = g.length > 1 ? Integer.parseInt(g[1]) : 1;
                if(i < 0){
                    System.out.println("second parameter should be a natural number");
                }
                if(i > 1){
                    for(int k=0; k<i; k++){
                        //call check many times start++;
                        check(start++, false);
                    }
                }else if(i == 1){
                    //call check once start
                    check(start, true);
                }
            }catch(NumberFormatException e){
                System.out.println("The first parameter should be a natural number or zero.");
            }
        }

    }

    static void check(long n, boolean printVertical){
        boolean isEven = n % 2 == 0;
        long l = n%10;
        long rest = n/10;
        boolean isBuzz = (rest - (l*2))%7 == 0 || l == 7;
        String r = String.valueOf(n);
        r = r.charAt(0) == '0'?r.replace('0','1') : r;
        boolean isduck = r.contains("0");
        var p = new StringBuilder(); //palindrome test number p
        for(char c: String.valueOf(n).toCharArray()){
            p.append(c);
        }
        boolean isPl = p.reverse().toString().equals(String.valueOf(n));
        boolean isGap = p.length() >= 3;
        if(isGap){
            String s = String.valueOf(n);
            int diviser = Integer.parseInt(String.valueOf(s.charAt(0))+s.charAt(s.length()-1));
            isGap = n%diviser == 0;
        }
        if(printVertical){
            System.out.printf("Properties of %d\n",n);
            System.out.printf("\tbuzz: %b\n",isBuzz);
            System.out.printf("\tduck: %b\n",isduck);
            System.out.printf("\tpalindromic: %b\n",isPl);
            System.out.printf("\tgapful: %b\n",isGap);
            System.out.printf("\teven: %b\n",isEven);
            System.out.printf("\todd: %b\n",!isEven);
        }else{
            System.out.printf("%d is "+
                    (isBuzz?"buzz, ":"")+
                    (isduck?"duck, ":"")+
                    (isPl?"palindromic, ":"")+
                    (isGap?"gapful, ":"")+
                    (isEven?"even\n":"odd\n"), n
            );
        }
    }
}
