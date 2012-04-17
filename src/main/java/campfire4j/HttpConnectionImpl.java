package campfire4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class HttpConnectionImpl implements HttpConnection {
	
	private URL url;
	private HttpURLConnection connection;
	private OutputStream out;

	public HttpConnectionImpl() {
	}
	
	@Override
	public HttpConnection open(String url) throws IOException {
		this.url = new URL(url);
		
		connection = (HttpURLConnection) this.url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		connection.setDoOutput(true);
		
		return this;
	}

	@Override
	public final HttpConnection auth (String authKey) {
		byte[] authKeyBytes = authKey.getBytes();
		connection.setRequestProperty("Authorization", "Basic " + Base64.encodeBase64String(authKeyBytes));
		return this;
	}
	
	@Override
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
	
	@Override
	public final void close () {
		try {
			connection.disconnect();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
