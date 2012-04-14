package campfire4j;

public class Message {
	private final String message;

	private Message(String message) {
		this.message = message;
	}

	public static final Message aNew (String message) {
		return new Message(message);
	}
	
	public String toString () {
		return "<message><body>"+message+"</body></message>";
	}
}
