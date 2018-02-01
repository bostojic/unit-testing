package rs.itekako.unittesting.exception;

public class BrewingException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8726703257686857553L;

	public BrewingException() {
		super();
	}
	
	public BrewingException(String message) {
		super(message);
	}
	
	public BrewingException(String message, Throwable t) {
		super(message, t);
	}
	
}
