package tdc;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SecondConsumer extends AbstractConsumer {
  private final Logger logger = LoggerFactory.getLogger(SecondConsumer.class);

  @Override
  public void run() {
    try {
      JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
      JMSConsumer consumer = context.createConsumer(context.createQueue("values-queue"));
      while (true) {
        Message message = consumer.receive();
        logger.info("Value: " + message.getBody(String.class));
      }
    } catch (JMSException e) {
      throw new RuntimeException();
    }
  }
}
