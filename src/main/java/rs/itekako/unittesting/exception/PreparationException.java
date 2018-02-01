package rs.itekako.unittesting.exception;

public class PreparationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8726703257686857553L;

	public PreparationException() {
		super();
	}
	
	public PreparationException(String message) {
		super(message);
	}
	
	public PreparationException(String message, Throwable t) {
		super(message, t);
	}
	
}
