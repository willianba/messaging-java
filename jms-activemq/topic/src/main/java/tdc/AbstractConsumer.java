package tdc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

public abstract class AbstractConsumer implements Runnable {
  @Inject
  ConnectionFactory connectionFactory;
  protected final ExecutorService scheduler = Executors.newSingleThreadExecutor();

  public void onStart(@Observes StartupEvent ev) {
    scheduler.submit(this);
  }

  public void onStop(@Observes ShutdownEvent ev) {
    scheduler.shutdown();
  }
}
