package codingassessment.exception;

public class OverFlowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2142484547433916185L;

	private String message;

	public OverFlowException(String message) {

		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}