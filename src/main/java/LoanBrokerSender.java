import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LoanBrokerSender {

    private final static String QUEUE_NAME = "client";
    ConnectionFactory factory = new ConnectionFactory();
    public LoanBrokerSender() throws IOException, TimeoutException {


    }

    public void sendMessage(String ssn, Double loanAmount, String loanDuration) throws IOException, TimeoutException {
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //JSONify the loan request

        LoanRequest loan_request = new LoanRequest(ssn, loanAmount, loanDuration);
        String loan_request_Json = loan_request.toJSON();

        System.out.println("Loan request in JSON format sent: " + loan_request_Json);

        //convert the object to string because this is the only way you can send a byte array
        channel.basicPublish("", QUEUE_NAME, null, loan_request_Json.toString().getBytes("UTF-8"));
        System.out.println(" [x] Sent from Get Credit Score '" + loan_request_Json + "'");

        channel.close();
        connection.close();
    }

}
