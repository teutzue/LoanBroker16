package rest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import core.LoanBrokerSender;
import core.Sender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrokerController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    LoanBrokerSender sender = new LoanBrokerSender();

    public BrokerController() throws IOException, TimeoutException {
    }

    @RequestMapping("/s/{s1}/{s2}")
    public String greeting2(@PathVariable(value="s1") String s1,
                            @PathVariable(value="s2") String s2){
        return "halloj "+ s1 +s2;
    }

    @RequestMapping("/broker/{ssn}/{loanAmount}/{loanDuration}")
    public String greeting(
            @PathVariable(value="ssn") String ssn,
            @PathVariable(value="loanAmount") Double loanAmount,
            @PathVariable(value="loanDuration") String loanDuration) {


        try {
            sender.sendMessage(ssn,loanAmount,loanDuration);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return "halloj";
    }
}
