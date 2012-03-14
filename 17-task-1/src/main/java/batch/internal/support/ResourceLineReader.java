package batch.internal.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import batch.InvalidBatchException;
import batch.internal.InputSource;

/**
 * An {@link InputSource} that reads lines one by one from a resource. A line is either terminated by a newline (as per
 * {@link BufferedReader#readLine()}), or can be continued onto the next line if a field surrounded by quotes (\")
 * contains a newline.
 */
@SuppressWarnings("rawtypes")
public class ResourceLineReader implements InputSource {
	private static final String QUOTE = "\"";

	private final BufferedReader reader;

	private final Resource resource;

	public ResourceLineReader(Resource resource) throws IOException {
		Assert.notNull(resource, "'resource' cannot be null.");
		this.resource = resource;
		this.reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
	}

	public Object read() {
		try {
			String line = this.reader.readLine();
			String result = line;
			while (StringUtils.countOccurrencesOf(result, QUOTE) % 2 != 0 && line != null) {
				StringBuffer buffer = new StringBuffer(result);
				buffer.append("\n" + (line = this.reader.readLine()));
				result = buffer.toString();
			}
			return result;
		} catch (IOException e) {
			throw new InvalidBatchException("Unable to read from resource '" + resource + "'.", e);
		}
	}
}
