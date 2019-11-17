package tdc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
  public DirectExchange directExchange() {
    return new DirectExchange(env.getProperty("direct.exchange.name"));
  }

  @Bean
  public Queue firstQueue() {
    return new Queue(env.getProperty("first.queue.name"));
  }

  @Bean
  public Queue secondQueue() {
    return new Queue(env.getProperty("second.queue.name"));
  }

  @Bean
  public Binding firstHighBinding(DirectExchange direct, Queue firstQueue) {
    return BindingBuilder.bind(firstQueue).to(direct).with(env.getProperty("high.binding"));
  }

  @Bean
  public Binding firstMediumBinding(DirectExchange direct, Queue firstQueue) {
    return BindingBuilder.bind(firstQueue).to(direct).with(env.getProperty("medium.binding"));
  }

  @Bean
  public Binding secondMediumBinding(DirectExchange direct, Queue secondQueue) {
    return BindingBuilder.bind(secondQueue).to(direct).with(env.getProperty("medium.binding"));
  }

  @Bean
  public Binding secondLowBinding(DirectExchange direct, Queue secondQueue) {
    return BindingBuilder.bind(secondQueue).to(direct).with(env.getProperty("low.binding"));
  }
}
