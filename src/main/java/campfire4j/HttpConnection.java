package campfire4j;

import java.io.IOException;

public interface HttpConnection {

	public abstract HttpConnection send(String message) throws IOException;

	public abstract HttpConnection auth(String authKey);

	public abstract void close();

	public abstract HttpConnection open(String url) throws IOException;

}
