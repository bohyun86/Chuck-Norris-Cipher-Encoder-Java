package chucknorris;

import java.util.Scanner;
import java.util.function.Function;

public class Decoder implements Selector {

    CheckValid checkValid = new CheckValid();

    Function<String, String[]> inputToArray = input -> input.split(" ");

    Function<String[], StringBuilder> inputToBinaryArray = divided -> {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < divided.length; i += 2) {
            String num = divided[i].equals("0") ? "1" : "0";
            for (int j = 0; j < divided[i + 1].length(); j++) {
                binary.append(num);
            }
        }
        return binary;
    };

    Function<StringBuilder, String[]> binaryToChar = binary -> {
        String[] divided = new String[binary.length() / 7];
        for (int i = 0; i < binary.length(); i += 7) {
            divided[i / 7] = binary.substring(i, i + 7);
        }
        return divided;
    };

    Function<String[], String> charToString = divided -> {
        StringBuilder result = new StringBuilder();
        for (String s : divided) {
            result.append((char) Integer.parseInt(s, 2));
        }
        return result.toString();
    };

    public String decode(String[] input) {
        return inputToBinaryArray
                .andThen(binaryToChar)
                .andThen(charToString)
                .apply(input);
    }

    @Override
    public void printStart() {
        System.out.println("Input encoded string:");
    }

    public void printResult() {
        System.out.println("Decoded string:");
    }

    @Override
    public String action() {
        Scanner scanner = new Scanner(System.in);
        printStart();
        String input = scanner.nextLine();

        if (input.isEmpty() || input.isBlank() || input.length() == 1) {
            return "";
        }

        String[] divided = inputToArray.apply(input);

        int countLength = 0;
        for (int i = 0; i < divided.length; i += 2) {
            if (!checkValid.checkEncodedString1(divided[i]) || !checkValid.checkEncodedString2(divided[i + 1])) {
                return "";
            }
            countLength += divided[i + 1].length();
        }
        if (countLength % 7 != 0) {
            return "";
        }
        return decode(divided);
    }
}
