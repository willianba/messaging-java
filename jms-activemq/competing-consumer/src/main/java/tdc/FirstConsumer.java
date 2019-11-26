package tdc;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FirstConsumer extends AbstractConsumer {
	private final Logger logger = LoggerFactory.getLogger(FirstConsumer.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
