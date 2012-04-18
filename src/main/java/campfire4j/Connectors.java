package campfire4j;


public class Connectors {
	
	public static HttpConnection connector (String className) {
		try {
			if (className == null) {
				return new HttpConnectionImpl();
			}
			return (HttpConnection) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
