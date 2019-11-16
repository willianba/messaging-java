package tdc;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Publisher {
  private final Random random = new Random();

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Value("${queue.name}")
  private String ROUTING_KEY;

  @Scheduled(fixedRate = 2000)
  public void send() {
    rabbitTemplate.convertAndSend(ROUTING_KEY, String. valueOf(random.nextInt(100)));
  }
}
