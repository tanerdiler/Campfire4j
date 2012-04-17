package campfire4j;

import static java.lang.String.format;

import java.io.IOException;

public class Campfire
{
	private static final String CAMPFIRE_URL = "https://%s.campfirenow.com/room/%s/speak.xml";
	
	private String authToken;
	private Application application;
	private Room room;

	private HttpConnection connector;

	private Campfire(HttpConnection connector) {
		this.connector = connector;
	}
	
	public static Campfire use(HttpConnection connector) {
		return new Campfire(connector);
	}
	
	public final Campfire app (Application application) {
		this.application = application;
		return this;
	}

	public final Campfire login (String authToken)
	{
		this.authToken = authToken+":X";
		return this;
	}
	
	public final void write (Message message) {
		String url = format(CAMPFIRE_URL, application.name, room.id);
		try {
			connector.open(url).auth(authToken).send(message.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public final Campfire to (Room room) {
		this.room = room;
		return this;
	}
	
	public final void close () {
		if (connector != null) {
			connector.close();
		}
	}

}
