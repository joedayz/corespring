package batch.internal.support;

import batch.internal.support.DelimitedLineTokenizer;
import junit.framework.TestCase;

public class DelimitedLineTokenizerTests extends TestCase {

	public void testDelimitedLineTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		String[] line = tokenizer.tokenize("a,b,c");
		assertEquals(3, line.length);
	}

	public void testDelimitedLineTokenizerChar() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(' ');
		String[] line = tokenizer.tokenize("a b c");
		assertEquals(3, line.length);
	}

	public void testTokenizeWithQuotes() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		String[] line = tokenizer.tokenize("a,b,\"c\"");
		assertEquals(3, line.length);
		assertEquals("c", line[2]);
	}

	public void testTokenizeWithDelimiterAtEnd() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		String[] line = tokenizer.tokenize("a,b,c,");
		assertEquals(4, line.length);
		assertEquals("c", line[2]);
		assertEquals("", line[3]);
	}

	public void testEmptyLine() throws Exception {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		String[] line = tokenizer.tokenize("");
		assertEquals(0, line.length);
		line = tokenizer.tokenize("  ");
		// whitespace counts as text
		assertEquals(1, line.length);
		line = tokenizer.tokenize(null);
		// null doesn't...
		assertEquals(0, line.length);
	}

	public void testMultiLineField() throws Exception {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		String[] line = tokenizer.tokenize("a,b,c\nrap");
		assertEquals(3, line.length);
		assertEquals("c\nrap", line[2]);

	}

	public void testMultiLineFieldWithQuotes() throws Exception {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		String[] line = tokenizer.tokenize("a,b,\"c\nrap\"");
		assertEquals(3, line.length);
		assertEquals("c\nrap", line[2]);

	}
}
