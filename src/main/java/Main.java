import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {

    private static LoanBrokerSender lbs;

    public Main() throws IOException, TimeoutException {

    }

    public static void main(String[] args) throws IOException, TimeoutException {
        lbs = new LoanBrokerSender();
        new Receiver().start();
        lbs.sendMessage("123456-6543", 1234567.0, "100");
    }



}
