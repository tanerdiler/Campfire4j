package campfire4j;

import junit.framework.Assert;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

public class MessageTest {
	
	@Test
	public void escapeMessage () {
		LoggingEvent logEvent = logEvent("Hello & World");
		String campfireMessage = Message.wrap(logEvent).toString();
		Assert.assertEquals("<message><body>Hello &amp; World</body></message>", campfireMessage);
	}
	
	@Test
	public void wrapsLogEventWithCampfireXML () {
		LoggingEvent logEvent = logEvent("Hello World");
		String campfireMessage = Message.wrap(logEvent).toString();
		Assert.assertEquals("<message><body>Hello World</body></message>", campfireMessage);
	}
	
	@Test
	public void wrapsPatternImplementedLogEventWithCampfireXML () {
		LoggingEvent logEvent = logEvent("Hello World");
		PatternLayout layout = new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN);
		String campfireMessage = Message.wrap(logEvent).layout(layout).toString();
		Assert.assertTrue(campfireMessage.indexOf("[main] DEBUG campfire4j.MessageTest  - Hello World") >= 0);
	}

	private LoggingEvent logEvent(String message) {
		Logger logger = Logger.getLogger(MessageTest.class);
		LoggingEvent logEvent = 
				new LoggingEvent("org.apache.log4j.Logger"
						, logger, Level.DEBUG, message, new Throwable());
		return logEvent;
	}
}
