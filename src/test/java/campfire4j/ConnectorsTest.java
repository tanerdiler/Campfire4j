package campfire4j;

import junit.framework.Assert;

import org.junit.Test;

public class ConnectorsTest {
	
	@Test
	public void createHttpConnectionImplAsDefault() {
		HttpConnection connection = Connectors.connector(null);
		Assert.assertNotNull(connection);
		Assert.assertTrue(connection instanceof HttpConnectionImpl);
	}

	@Test
	public void createConnectionSpecifiedAsClassName() {
		HttpConnection connection = Connectors
				.connector("campfire4j.StubHttpConnection");
		Assert.assertNotNull(connection);
		Assert.assertTrue(connection instanceof StubHttpConnection);
	}
}
