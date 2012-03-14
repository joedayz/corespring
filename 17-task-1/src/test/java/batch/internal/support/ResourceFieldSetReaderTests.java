package batch.internal.support;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import batch.internal.FieldSet;

/**
 */
public class ResourceFieldSetReaderTests extends TestCase {

	public void testRead() throws Exception {
		Resource resource = new InputStreamResource(
				new ByteArrayInputStream("foo,bar,spam\nbucket,crap,man".getBytes()));
		ResourceFieldSetReader reader = new ResourceFieldSetReader(resource);
		int count = 0;
		FieldSet fieldSet;
		while ((fieldSet = reader.read()) != null) {
			count++;
			assertNotNull(fieldSet);
			assertEquals(3, fieldSet.getFieldCount());
		}
		assertEquals(2, count);
	}

	public void testDelimiter() throws Exception {
		Resource resource = new InputStreamResource(new ByteArrayInputStream("foo.bar.spam".getBytes()));
		ResourceFieldSetReader reader = new ResourceFieldSetReader(resource, '.');
		int count = 0;
		FieldSet fieldSet;
		while ((fieldSet = reader.read()) != null) {
			count++;
			assertNotNull(fieldSet);
			assertEquals(3, fieldSet.getFieldCount());
			assertEquals("foo", fieldSet.readString(0));
		}
		assertEquals(1, count);
	}

	public void testWhitespace() throws Exception {
		Resource resource = new InputStreamResource(new ByteArrayInputStream(" foo ,bar,spam".getBytes()));
		ResourceFieldSetReader reader = new ResourceFieldSetReader(resource);
		FieldSet fieldSet = reader.read();
		assertNotNull(fieldSet);
		assertEquals("foo", fieldSet.readString(0));
	}

	public void testQuotes() throws Exception {
		Resource resource = new InputStreamResource(new ByteArrayInputStream("\" foo \",bar,\"spam\"".getBytes()));
		ResourceFieldSetReader reader = new ResourceFieldSetReader(resource);
		FieldSet fieldSet = reader.read();
		assertNotNull(fieldSet);
		// TODO: maybe this should be with the whitespace still?
		assertEquals("foo", fieldSet.readString(0));
		assertEquals("spam", fieldSet.readString(2));
	}

	public void testMultiLineField() throws Exception {
		Resource resource = new InputStreamResource(new ByteArrayInputStream("foo,bar,\"s\npam\"".getBytes()));
		ResourceFieldSetReader reader = new ResourceFieldSetReader(resource);
		FieldSet fieldSet = reader.read();
		assertNotNull(fieldSet);
		assertEquals("foo", fieldSet.readString(0));
		System.err.println(fieldSet);
		assertEquals("s\npam", fieldSet.readString(2));
	}
}
