package br.com.korturl.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UUIDUtilTest {
	
	@Test
	public void testValidUUID() throws Exception {
		/* UUID */
		assertNotNull(UUIDUtils.getUUID());
	}
	
	@Test
	public void testValidUUIDBase64() throws Exception {
		/* Short UUID */
		assertNotNull(UUIDUtils.getShortUUID());
	}
}
