package batch.internal.support;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import batch.internal.FieldSet;
import batch.internal.InputSource;

/**
 */
public class ResourceFieldSetReader implements InputSource<FieldSet> {

	private final ResourceLineReader lineReader;

	private final DelimitedLineTokenizer tokenizer;

	public ResourceFieldSetReader(Resource resource, char delimiter) throws IOException {
		super();
		Assert.notNull(resource, "'resource' cannot be null.");
		this.lineReader = new ResourceLineReader(resource);
		this.tokenizer = new DelimitedLineTokenizer(delimiter);
	}

	public ResourceFieldSetReader(Resource resource) throws IOException {
		this(resource, DelimitedLineTokenizer.DELIMITER_COMMA);
	}

	public FieldSet read() {
		String line = (String) lineReader.read();
		if (line == null) {
			return null;
		}
		String[] result = tokenizer.tokenize(line);
		for (int i = 0; i < result.length; i++) {
			result[i] = result[i].trim();
		}
		return new FieldSet(result);
	}
}
