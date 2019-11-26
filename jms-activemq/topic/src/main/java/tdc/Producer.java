package tdc;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class Producer {
	@Inject
	ConnectionFactory connectionFactory;
	private final Random random = new Random();
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private final Logger logger = LoggerFactory.getLogger(Producer.class);

	public void onStart(@Observes StartupEvent ev) {
		scheduler.scheduleWithFixedDelay(() -> {
			publishMessage();
		}, 0L, 2L, TimeUnit.SECONDS);
	}

	public void onStop(@Observes ShutdownEvent ev) {
		scheduler.shutdown();
	}

	public void publishMessage() {
		String value = Integer.toString(random.nextInt(100));
		try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
			logger.info("Publishing value: " + value);
			context.createProducer().send(context.createTopic("values-topic"), value);
		}
	}
}
