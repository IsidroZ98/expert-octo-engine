package numbers;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        info();
        while (1 > 0) {
            System.out.print("Enter a request:");
            Scanner sc = new Scanner(System.in);
            String[] nn;
            nn = sc.nextLine().split(" ");
            String ns = nn[0];
            long n = Long.parseLong(ns);
            if(checkErr(n)) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (n == 0) {
                System.out.println("Goodbye!");
                return;
            }
            if (nn.length == 1) {
               one(n,ns);
               continue;
            }
            if (nn.length ==2){
                String nd = nn[1];
                long n1 = Long.parseLong(nd);
                if(checkErr(n1)) {
                    System.out.println("The second parameter should be a natural number or zero.");
                    continue;
                }
                two(n, n1);
            }
            if(nn.length ==3){
                String nd = nn[1];
                long n1 = Long.parseLong(nd);
                String nb =nn[2].toLowerCase();

                    if (nb.equals("buzz") ||
                            nb.equals("duck") ||
                            nb.equals("palindromic") ||
                            nb.equals("gapful") ||
                            nb.equals("spy") ||
                            nb.equals("even") ||
                            nb.equals("odd")) {
                        three(n,n1,nb);
                        continue;
                }System.out.println("The property [" + nb + "] is wrong. " +
                        "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
                continue;

            }
        }
    }
    private static void one(long n,String ns) {
        System.out.println("Properties of " + n);
        System.out.println("buzz: " + buzz(n));
        System.out.println("duck: " + duck(ns));
        System.out.println("spy: " + spy(ns));
        System.out.println("gapful: " + gapful(ns));
        System.out.println("palindromic: " + pld(n));
        System.out.println("even: " + even(n));
        System.out.println("odd: " + odd(n));
        System.out.println();
    }
    private static void two(long a, long b) {
        for (int i = 0; i < b; i++) {
            System.out.print(a + " is ");
            if (buzz(a)) System.out.print("buzz, ");
            String aa = String.valueOf(a);
            if (duck(aa)) System.out.print("duck, ");
            if(gapful(aa)) System.out.print("gapful, ");
            if(spy(aa)) System.out.print("spy, ");
            if (pld(a)) System.out.print("palindromic, ");
            if (even(a)) System.out.print("even");
            if (odd(a)) System.out.print("odd");
            System.out.println();
            a++;
        }
    }

    private static void three(long a, long b,String c) {
        for (int i = 0; i < b;) {
            String res =" ";
            if (buzz(a)) res += "buzz, ";
            String aa = String.valueOf(a);
            if (duck(aa)) res += "duck, ";
            if(gapful(aa)) res += "gapful, ";
            if(spy(aa)) res += "spy, ";
            if (pld(a)) res += "palindromic, ";
            if (even(a)) res += "even";
            if (odd(a)) res += "odd";
            if(res.contains(c)) {
                System.out.println(a + " is" + res);
                i++;
            }
            a++;
        }
    }

    private static boolean even(long num) {
        if (num % 2 == 0) return true;
        else return false;
    }
    private static boolean odd(long num) {
        if (num % 2 != 0) return true;
        else return false;
    }
    private static boolean pld(long num) {
        long a, b = 0, temp;
        temp = num;
        while (num != 0) {
            a = num % 10;
            b = b * 10 + a;
            num = num / 10;
        }
        if (temp == b) return true;
        else return false;
    }
    private static boolean buzz(long num) {
        if ((num % 7) == 0 || (num % 10) == 7) return true;
        else return false;
    }
    private static boolean duck(String num) {
        String a = num;
        boolean flag = false;
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                flag = true;
                break;
            }
        }
        return flag;

    }
    private static void info() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:" +
                "\n- enter a natural number to know its properties;" +
                "\n- enter two natural numbers to obtain the properties of the list:" +
                "\n* the first parameter represents a starting number;" +
                "\n* the second parameter show how many consecutive numbers are to be processed;" +
                "\n- two natural numbers and a property to search for;" +
                "\n- separate the parameters with one space;" +
                "\n- enter 0 to exit.");
    }
    private static boolean gapful(String a) {
        char check[] = { a.charAt(0),a.charAt(a.length()-1)};
        long ch = Long.parseLong(String.valueOf(check));
        long aa = Long.parseLong(a);

        if(aa>99 && (aa%ch)==0) return true;
        return false;
    }
    private static boolean spy(String  a) {
        char[] b = a.toCharArray();
        long sum = 0;
        long prod= 1;
        for(int i=0;i<b.length;i++) {
            sum += Character.getNumericValue(b[i]);
            prod *= Character.getNumericValue(b[i]);
        }
        if(sum==prod) return true;
        return false;

    }
    private static boolean checkErr(long a) {
        if (a < 0) {
            return true;
        }return false;
    }
}
