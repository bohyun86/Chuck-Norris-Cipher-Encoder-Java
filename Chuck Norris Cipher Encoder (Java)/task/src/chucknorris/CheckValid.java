package chucknorris;

import java.util.Scanner;

public class CheckValid {

    public String checkInputOperation(String comment) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(comment);
        String input = scanner.nextLine();
        if (input.equals("encode") || input.equals("decode") || input.equals("exit")) {
            return input;
        } else {
            System.out.printf("There is no '%s' operation\n", input);
            return checkInputOperation(comment);
        }
    }

    public boolean checkEncodedString1(String input) {
        return input.matches("0{1,2}");
    }

    public boolean checkEncodedString2(String input) {
        return input.matches("0+");
    }
}
