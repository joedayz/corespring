package batch.internal.support;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import batch.InvalidBatchException;
import batch.internal.support.ResourceLineReader;

/**
 */
public class ResourceLineReaderTests extends TestCase {

	public void testBadResource() throws Exception {
		ResourceLineReader reader = new ResourceLineReader(new InputStreamResource(new InputStream() {
			public int read() throws IOException {
				throw new IOException("Foo");
			}
		}));
		try {
			reader.read();
			fail("Expected InvalidBatchException");
		} catch (InvalidBatchException e) {
			// expected
			assertTrue(e.getMessage().startsWith("Unable to read"));
		}
	}

	public void testRead() throws Exception {
		Resource resource = new InputStreamResource(new ByteArrayInputStream("a,b,c\n1,2,3".getBytes()));
		ResourceLineReader reader = new ResourceLineReader(resource);
		int count = 0;
		String line;
		while ((line = (String) reader.read()) != null) {
			count++;
			assertNotNull(line);
		}

		assertEquals(2, count);
	}
}
