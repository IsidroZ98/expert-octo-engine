package numbers;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberProperties properties = new NumberProperties();
        NumberController controller = new NumberController(properties, scanner);

        controller.run();
    }


}