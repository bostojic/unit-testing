package rs.itekako.unittesting.exception;

public class RefillingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8726703257686857553L;

	public RefillingException() {
		super();
	}
	
	public RefillingException(String message) {
		super(message);
	}
	
	public RefillingException(String message, Throwable t) {
		super(message, t);
	}
	
}
