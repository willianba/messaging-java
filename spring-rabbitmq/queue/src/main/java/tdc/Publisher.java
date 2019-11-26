package tdc;

import java.util.Random;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
  private final Random random = new Random();

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private Queue queue;

  @Scheduled(fixedRate = 2000)
  public void send() {
    rabbitTemplate.convertAndSend(queue.getName(), Integer.toString(random.nextInt(100)));
  }
}
