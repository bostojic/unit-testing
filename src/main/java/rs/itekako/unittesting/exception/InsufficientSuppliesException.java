package rs.itekako.unittesting.exception;

public class InsufficientSuppliesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8726703257686857553L;

	public InsufficientSuppliesException() {
		super();
	}
	
	public InsufficientSuppliesException(String message) {
		super(message);
	}
	
	public InsufficientSuppliesException(String message, Throwable t) {
		super(message, t);
	}
	
}
