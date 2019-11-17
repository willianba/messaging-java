package tdc;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitConfig {
  @Autowired
  Environment env;

  @Bean
  public Queue valuesQueue() {
    return new Queue(env.getProperty("queue.name"));
  }
}
