package tdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"${second.queue.name}"})
public class SecondConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(SecondConsumer.class);

  @RabbitHandler
  public void receiveMessage(String value) {
    LOGGER.info("Value: " + value);
  }
}
