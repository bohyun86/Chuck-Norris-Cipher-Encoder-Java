package chucknorris;

public class Main {

    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        CheckValid checkValid = new CheckValid();

        Operator operator = new Operator(encoder, decoder, checkValid);
        operator.start();
    }
}