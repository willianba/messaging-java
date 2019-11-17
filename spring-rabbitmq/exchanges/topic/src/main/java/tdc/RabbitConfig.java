package tdc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
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
  public TopicExchange topicExchange() {
    return new TopicExchange(env.getProperty("topic.exchange.name"));
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
  public Binding firstRabbitBinding(TopicExchange topic, Queue firstQueue) {
    return BindingBuilder.bind(firstQueue).to(topic).with(env.getProperty("rabbit.binding"));
  }

  @Bean
  public Binding firstLazyBinding(TopicExchange topic, Queue firstQueue) {
    return BindingBuilder.bind(firstQueue).to(topic).with(env.getProperty("lazy.binding"));
  }

  @Bean
  public Binding secondOrangeBinding(TopicExchange topic, Queue secondQueue) {
    return BindingBuilder.bind(secondQueue).to(topic).with(env.getProperty("orange.binding"));
  }
}
