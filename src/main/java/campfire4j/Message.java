package campfire4j;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class Message {
	private String message;
	private LoggingEvent logEvent;
	private Layout layout;

	private Message(LoggingEvent logEvent) {
		this.logEvent = logEvent;
	}

	public String toString () {
		if (layout != null) {
			message = layout.format(logEvent);
		} else if (logEvent != null) {
			message = logEvent.getRenderedMessage();
		}
		String escapedMessage = StringEscapeUtils.escapeXml(message);
		return "<message><body>"+escapedMessage+"</body></message>";
	}

	public static Message wrap(LoggingEvent logEvent) {
		return new Message(logEvent);
	}

	public Message layout(Layout layout) {
		this.layout = layout;
		return this;
	}
}
