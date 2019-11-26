package tdc;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
  private final Random random = new Random();
  private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private Queue queue;

  @Scheduled(fixedRate = 2000)
  public void send() {
    String value = Integer.toString(random.nextInt(100));
    LOGGER.info("Publishing value: {}", value);
    rabbitTemplate.convertAndSend(queue.getName(), value);
  }
}
