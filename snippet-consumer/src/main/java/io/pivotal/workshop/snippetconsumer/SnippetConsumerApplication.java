package io.pivotal.workshop.snippetconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnippetConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetConsumerApplication.class, args);
	}

	@RabbitListener(queues = "spring-boot")
	public void receiveMessage(SnippetNotifier snippetNotifier){
		System.out.println("Received <" + snippetNotifier.getTitle() + " on " + snippetNotifier.getCreated() + ">");
	}
}
