package tdc;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class Producer implements Runnable {
  @Inject
  ConnectionFactory connectionFactory;
  private final Random random = new Random();
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

  public void onStart(@Observes StartupEvent ev) {
    scheduler.scheduleWithFixedDelay(this, 0L, 2L, TimeUnit.SECONDS);
  }

  public void onStop(@Observes ShutdownEvent ev) {
    scheduler.shutdown();
  }

  @Override
  public void run() {
    try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
      context.createProducer().send(context.createQueue("values-queue"), Integer.toString(random.nextInt(100)));
    }
  }
}
