package campfire4j;

import java.io.IOException;

public class StubHttpConnection implements HttpConnection {

	public String message;
	public String url;
	public String authKey;

	public static StubHttpConnection instance = null;

	public StubHttpConnection() {
		instance = this;
	}

	public final HttpConnection open(String url) throws IOException {
		this.url = url;
		return this;
	}

	@Override
	public HttpConnection send(String message) throws IOException {
		this.message = message;
		return this;
	}

	@Override
	public HttpConnection auth(String authKey) {
		this.authKey = authKey;
		return this;
	}

	@Override
	public void close() {
	}

}
