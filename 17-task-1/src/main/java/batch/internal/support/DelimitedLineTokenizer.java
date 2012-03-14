package batch.internal.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 */
public class DelimitedLineTokenizer {

	/**
	 * Convenient constant for the common case of a tab delimiter.
	 */
	public static final char DELIMITER_TAB = '\t';

	/**
	 * Convenient constant for the common case of a comma delimiter.
	 */
	public static final char DELIMITER_COMMA = ',';

	/**
	 * Value returned for empty or null input.
	 */
	private static final String[] EMPTY_STRINGS = new String[] {};

	/**
	 * Convenient constant for the common case of a " escape character.
	 */
	public static final char DEFAULT_QUOTE_CHARACTER = '"';

	/**
	 * The delimiter character used when reading input.
	 */
	private final char delimiter;

	/**
	 * Create a new instance of the {@link DelimitedLineTokenizer} class for the common case where the delimiter is a
	 * {@link #DELIMITER_COMMA comma}.
	 * 
	 * @see #DelimitedLineTokenizer(char)
	 * @see #DELIMITER_COMMA
	 */
	public DelimitedLineTokenizer() {
		this(DELIMITER_COMMA);
	}

	/**
	 * Create a new instance of the {@link DelimitedLineTokenizer} class.
	 * 
	 * @param delimiter the desired delimiter
	 */
	public DelimitedLineTokenizer(char delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * Yields the tokens resulting from the splitting of the supplied <code>line</code> along the configured
	 * delimiter.
	 * 
	 * <p>
	 * Does not include the delimiter in the returned token array.
	 * 
	 * <p>
	 * Empty tokens are returned as empty strings, never <code>null</code>.
	 * 
	 * @param line the line to be tokenised (can be <code>null</code>)
	 * @return the resulting tokens; an empty <code>String[]</code> if no delimiter was found or if the supplied
	 * <code>line</code> is <code>null</code> or zero length
	 */
	public String[] tokenize(String line) {
		if (!StringUtils.hasLength(line)) {
			return EMPTY_STRINGS;
		}
		char[] chars = line.toCharArray();
		List<String> tokens = new ArrayList<String>();
		boolean inQuoted = false;
		int lastCut = 0;
		char lastChar = 0;
		int length = chars.length;
		for (int i = 0; i < length; i++) {
			char currentChar = chars[i];
			boolean isEnd = (i == length - 1);
			if ((isDelimiterCharacter(currentChar) && !inQuoted) || isEnd) {
				int endPosition = (isEnd ? length - lastCut : i - lastCut);
				if (isEnd && isDelimiterCharacter(currentChar)) {
					endPosition--;
				}
				if (isQuoteCharacter(lastChar) || isQuoteCharacter(currentChar)) {
					tokens.add(new String(chars, lastCut + 1, endPosition - 2));
				}
				// handle case where delimiter is last character in the line
				else if (isEnd && (isDelimiterCharacter(currentChar))) {
					tokens.add(new String(chars, lastCut, endPosition));
					tokens.add("");
				} else {
					tokens.add(new String(chars, lastCut, endPosition));
				}
				lastCut = i + 1;
			} else if (isQuoteCharacter(currentChar)) {
				inQuoted = !inQuoted;
			}
			lastChar = currentChar;
		}
		return tokens.toArray(new String[tokens.size()]);
	}

	/**
	 * Is the supplied character the delimiter character?
	 * 
	 * @param c the character to be checked
	 * @return <code>true</code> if the supplied character is the delimiter character
	 * @see DelimitedLineTokenizer#DelimitedLineTokenizer(char)
	 */
	private boolean isDelimiterCharacter(char c) {
		return c == this.delimiter;
	}

	/**
	 * Is the supplied character a quote character?
	 * 
	 * @param c the character to be checked
	 * @return <code>true</code> if the supplied character is an quote character
	 * @see #DEFAULT_QUOTE_CHARACTER
	 */
	protected boolean isQuoteCharacter(char c) {
		return c == DEFAULT_QUOTE_CHARACTER;
	}
}
