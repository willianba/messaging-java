package tdc;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SecondConsumer extends AbstractConsumer {
	private final Logger logger = LoggerFactory.getLogger(SecondConsumer.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
