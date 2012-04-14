package campfire4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class HttpConnection {
	
	private final URL url;
	private HttpURLConnection connection;
	private OutputStream out;

	private HttpConnection(URL url) {
		this.url = url;
	}

	public static final HttpConnection open (String url) throws IOException {
		HttpConnection httpConnection = new HttpConnection(new URL(url));
		httpConnection.open();
		return httpConnection;
	}
	
	private void open() throws IOException {
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		connection.setDoOutput(true);
	}

	public final HttpConnection auth (String authKey) {
		byte[] authKeyBytes = authKey.getBytes();
		connection.setRequestProperty("Authorization", "Basic " + Base64.encodeBase64String(authKeyBytes));
		return this;
	}
	
	public final HttpConnection send (String message) throws IOException {
		byte[] mesajBytes = message.getBytes("utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(mesajBytes.length));
        out = connection.getOutputStream();
        out.write(mesajBytes);
        out.flush();
        
        int responseCode = connection.getResponseCode();
        System.out.println("response code " + responseCode);
		return this;
	}
	
	public final void close () {
		try {
			connection.disconnect();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
