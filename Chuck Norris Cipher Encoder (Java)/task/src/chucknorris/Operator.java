package chucknorris;


public class Operator {

    Selector selector;
    Encoder encoder = new Encoder();
    Decoder decoder = new Decoder();
    CheckValid checkValid = new CheckValid();

    public void start() {

        while (true) {
            String operation = checkValid
                    .checkInputOperation("Please input operation (encode/decode/exit):");

            if (operation.equals("exit")) {
                System.out.println("Bye!");
                return;
            }

            setSelector(operation);

            String result = selector.action();

            if (result.isEmpty() || result.isBlank()) {
                System.out.println("Encoded string is not valid.");
                continue;
            }

            selector.printResult();
            System.out.println(result);
        }

    }

    public void setSelector(String selector) {
        if (selector.equals("encode")) {
            this.selector = encoder;
        } else {
            this.selector = decoder;
        }
    }
}
