package campfire4j;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;


public class CampfireAppenderTest {
	
	@Test
	public void createHttpConnectionIfSpecifiedConfiguration () {
		Assert.assertNotNull(StubHttpConnection.instance);
	}
	
	@Test
	public void putsXasPasswordEndOfAuthKey () {
		Assert.assertEquals("213412341234:X", StubHttpConnection.instance.authKey);
	}
	
	@Test
	public void buildCampfireCommunicationUrl () {
		Assert.assertEquals("https://testapp.campfirenow.com/room/123/speak.xml", StubHttpConnection.instance.url);
	}
	
	@Test
	public void convertLogMessageToCampfireXml () {
		Assert.assertEquals("<message><body>DEBUG campfire4j.CampfireAppenderTest:34 - deneme\n</body></message>", StubHttpConnection.instance.message);
	}
	
	@Before
	public void before() {
		Logger logger = Logger.getLogger(CampfireAppenderTest.class);
		logger.debug("deneme");
	}
}
