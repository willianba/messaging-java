package tdc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class Consumer {
	@Inject
	ConnectionFactory connectionFactory;
	private final ExecutorService scheduler = Executors.newSingleThreadExecutor();
	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	public void onStart(@Observes StartupEvent ev) {
		scheduler.submit(() -> {
			consumeMessage();
		});
	}

	public void onStop(@Observes ShutdownEvent ev) {
		scheduler.shutdown();
	}

	private void consumeMessage() {
		try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
				JMSConsumer consumer = context.createConsumer(context.createQueue("values-queue"));) {
			while (true) {
				Message message = consumer.receive();
				logger.info("Value: " + message.getBody(String.class));
			}
		} catch (JMSException e) {
			throw new RuntimeException();
		}
	}
}
