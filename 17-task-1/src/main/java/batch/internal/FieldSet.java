package batch.internal;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 */
public final class FieldSet {

	/**
	 * The fields wrapped by this '<code>FieldSet</code>' instance.
	 */
	private final String[] fields;

	/**
	 * Create a new instance of the <code>FieldSet</code> class wrapping the supplied <code>fields</code>.
	 * 
	 * @param fields the fields to be wrapped.
	 * @throws IllegalArgumentException if the supplied <code>fields</code> array is <code>null</code>.
	 */
	public FieldSet(String[] fields) {
		Assert.notNull(fields, "'fields' cannot be null.");
		this.fields = (String[]) fields.clone();
	}

	/**
	 * Read the {@link String} value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public String readString(int index) {
		if (index < 0 || index > fields.length - 1) {
			throw new IndexOutOfBoundsException("No field at index '" + index + "'");
		}
		return this.fields[index];
	}

	/**
	 * Read the {@link String} value at index '<code>index</code>' and remove leading white space. <p/>
	 * <p>
	 * This will never return <code>null</code>; if the field is <code>null</code> an empty string will be
	 * returned.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public String readStringAndTrim(int index) {
		String value = readString(index);
		return value == null ? "" : value.trim();
	}

	/**
	 * Read the '<code>boolean</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public boolean readBoolean(int index) {
		return Boolean.parseBoolean(readAndTrim(index));
	}

	/**
	 * Read the '<code>boolean</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @param trueValue the value that signifies {@link Boolean#TRUE true}; case-sensitive.
	 * @throws IndexOutOfBoundsException if the index is out of bounds, or if the supplied <code>trueValue</code> is
	 * <code>null</code>.
	 */
	public boolean readBoolean(int index, String trueValue) {
		Assert.notNull(trueValue, "'trueValue' cannot be null.");
		String value = readAndTrim(index);
		return (trueValue.equals(value) ? Boolean.TRUE : Boolean.FALSE).booleanValue();
	}

	/**
	 * Read the '<code>char</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public char readChar(int index) {
		String value = readAndTrim(index);
		if (value.length() != 1) {
			throw new IllegalArgumentException("Cannot convert field value '" + value + "' to char.");
		}
		return value.charAt(index);
	}

	/**
	 * Read the '<code>byte</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public byte readByte(int index) {
		return Byte.parseByte(readAndTrim(index));
	}

	/**
	 * Read the '<code>short</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public short readShort(int index) {
		return Short.parseShort(readAndTrim(index));
	}

	/**
	 * Read the '<code>int</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public int readInt(int index) {
		return Integer.parseInt(readAndTrim(index));
	}

	/**
	 * Read the '<code>int</code>' value at index '<code>index</code>', using the supplied
	 * <code>defaultValue</code> if the field value is blank.
	 * 
	 * @param index the field index..
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public int readInt(int index, int defaultValue) {
		String value = readAndTrim(index);
		return StringUtils.hasLength(value) ? Integer.parseInt(value) : defaultValue;
	}

	/**
	 * Read the '<code>long</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public long readLong(int index) {
		return Long.parseLong(readAndTrim(index));
	}

	/**
	 * Read the '<code>long</code>' value at index '<code>index</code>', using the supplied
	 * <code>defaultValue</code> if the field value is blank.
	 * 
	 * @param index the field index..
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public long readLong(int index, long defaultValue) {
		String value = readAndTrim(index);
		return StringUtils.hasLength(value) ? Long.parseLong(value) : defaultValue;
	}

	/**
	 * Read the '<code>float</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public float readFloat(int index) {
		return Float.parseFloat(readAndTrim(index));
	}

	/**
	 * Read the '<code>double</code>' value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public double readDouble(int index) {
		return Double.parseDouble(readAndTrim(index));
	}

	/**
	 * Read the {@link java.math.BigDecimal} value at index '<code>index</code>'.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public BigDecimal readBigDecimal(int index) {
		return readBigDecimal(index, null);
	}

	/**
	 * Read the {@link BigDecimal} value at index '<code>index</code>', returning the supplied
	 * <code>defaultValue</code> if the trimmed string value at index '<code>index</code>' is blank.
	 * 
	 * @param index the field index.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public BigDecimal readBigDecimal(int index, BigDecimal defaultValue) {
		String candidate = readAndTrim(index);
		try {
			return candidate.length() != 0 ? new BigDecimal(candidate) : defaultValue;
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("'" + candidate + "' | '" + candidate.length() + "', '" + defaultValue
					+ "'", ex);
		}
	}

	/**
	 * Return the number of fields in this '<code>FieldSet</code>'.
	 */
	public int getFieldCount() {
		return this.fields.length;
	}

	/**
	 * Read and trim the {@link String} value at '<code>index</code>'.
	 * 
	 * @throws NullPointerException if the field value is <code>null</code>.
	 */
	private String readAndTrim(int index) {
		String value = readString(index);
		if (value == null) {
			throw new NullPointerException("Unable to convert field at index '" + index + "' - value is null.");
		}
		return value.trim();
	}

	public String toString() {
		return Arrays.asList(this.fields).toString();
	}
}
