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
public class Consumer implements Runnable {
  @Inject
  ConnectionFactory connectionFactory;
  private final ExecutorService scheduler = Executors.newSingleThreadExecutor();
  private final Logger logger = LoggerFactory.getLogger(Consumer.class);

  public void onStart(@Observes StartupEvent ev) {
    scheduler.submit(this);
  }

  public void onStop(@Observes ShutdownEvent ev) {
    scheduler.shutdown();
  }

  @Override
  public void run() {
    try {
      JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
      JMSConsumer consumer = context.createConsumer(context.createQueue("values-queue"));
      while (true) {
        Message message = consumer.receive();
        if (message == null) {
          return;
        }
        logger.info("Value: " + message.getBody(String.class));
      }
    } catch (JMSException e) {
      throw new RuntimeException();
    }
  }
}
