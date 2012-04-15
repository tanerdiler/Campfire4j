package campfire4j;

import static java.lang.String.format;

import java.io.IOException;

public class Campfire
{
	private static final String CAMPFIRE_URL = "https://%s.campfirenow.com/room/%s/speak.xml";
	
	private String authToken;
	private Application application;
	private Room room;

	private HttpConnection connection;

	private Campfire(Application application) {
		this.application = application;
	}
	
	public static final Campfire app (Application application) {
		return new Campfire(application);
	}

	public final Campfire login (String authToken)
	{
		this.authToken = authToken+":X";
		return this;
	}
	
	public final void write (Message message) {
		String url = format(CAMPFIRE_URL, application.name, room.id);
		try {
			if (connection == null) {
				connection = HttpConnection.open(url).auth(authToken);
			}
			connection.send(message.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final Campfire to (Room room) {
		this.room = room;
		return this;
	}
	
	public final void close () {
		if (connection != null) {
			connection.close();
		}
	}
}
