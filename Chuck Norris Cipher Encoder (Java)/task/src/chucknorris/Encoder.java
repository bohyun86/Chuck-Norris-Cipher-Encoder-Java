package chucknorris;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Encoder implements Selector {

    Function<String, String[]> inputToChar = input -> input.split("");

    Function<String[], String[]> charToBinary = divided -> Arrays.stream(divided)
            .map(s -> String.format("%07d", Integer.parseInt(Integer.toBinaryString(s.charAt(0)))))
            .toArray(String[]::new);

    Function<String[], String> binaryToChuckNorris = binaryArray -> {
        StringBuilder result = new StringBuilder();
        String binaries = String.join("", binaryArray);
        int curser = 0;

        while (curser < binaries.length()) {
            char currentChar = binaries.charAt(curser);
            int count = 1;
            while (curser < binaries.length() - 1 && currentChar == binaries.charAt(curser + 1)) {
                count++;
                curser++;
            }
            result.append(currentChar == '1' ? "0 " : "00 ");
            for (int i = 0; i < count; i++) {
                result.append("0");
            }
            if (curser < binaries.length()) {
                result.append(" ");
            }
            curser++;
        }
        return result.toString();
    };

    public String encode(String input) {
        return inputToChar
                .andThen(charToBinary)
                .andThen(binaryToChuckNorris)
                .apply(input);
    }

    @Override
    public void printStart() {
        System.out.println("Input string:");
    }

    public void printResult() {
        System.out.println("Encoded string:");
    }

    @Override
    public String action() {
        Scanner scanner = new Scanner(System.in);
        printStart();
        String input = scanner.nextLine();
        if (input.isEmpty() || input.isBlank()) {
            return "";
        }
        return encode(input);
    }
}
