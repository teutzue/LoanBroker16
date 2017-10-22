package rest;

import core.Receiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		new Receiver().start();

		SpringApplication.run(Application.class, args);
		System.out.println("-------------------Application has started-----------------------");
	}
}
