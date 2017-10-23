package rest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import core.LoanBrokerSender;
import logic.SingeltonHolder;
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


        return waitAndGetBestQuote(6);
        //return "halloj";
    }

    private String waitAndGetBestQuote(int time){
        for (int i=0;i<time;i++)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result=SingeltonHolder.getBestQuote();
            if (result!=null) return result;
        }
        return "Best quote does not exist :). Please check out input in the url: {String}/{Double}/{String} + \n" +
                "Smaple URL: http://localhost:8090/broker/123456-6543/1234567.0/789 \n" +
                "Make sure that all your components are up and running :). ";
    }
}
