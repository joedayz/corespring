package batch;

public class InvalidBatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045580134510251734L;

	public InvalidBatchException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InvalidBatchException(String msg) {
		super(msg);
	}

}
