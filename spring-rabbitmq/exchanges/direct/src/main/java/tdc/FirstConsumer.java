package tdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"${first.queue.name}"})
public class FirstConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(FirstConsumer.class);

  @RabbitHandler
  public void receiveMessage(String value) {
    LOGGER.info("Value: " + value);
  }
}
