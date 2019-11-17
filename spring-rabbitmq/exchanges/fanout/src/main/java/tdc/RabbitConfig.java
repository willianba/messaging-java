package tdc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(env.getProperty("fanout.exchange.name"));
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
  public Binding firstBinding(FanoutExchange fanout, Queue firstQueue) {
    return BindingBuilder.bind(firstQueue).to(fanout);
  }

  @Bean
  public Binding secondBinding(FanoutExchange fanout, Queue secondQueue) {
    return BindingBuilder.bind(secondQueue).to(fanout);
  }
}
