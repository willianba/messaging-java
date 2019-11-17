package tdc;

import java.util.Random;

import org.springframework.amqp.core.FanoutExchange;
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
  private FanoutExchange fanoutExchange;

  @Scheduled(fixedRate = 2000)
  public void send() {
    rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", String.valueOf(random.nextInt(100)));
  }
}
