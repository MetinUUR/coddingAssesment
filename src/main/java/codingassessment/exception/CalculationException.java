package codingassessment.exception;

public class CalculationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3314589846836005832L;

	private String message;
	private long number;

	public CalculationException(String message, long number) {

		this.message = message;
		this.number=number;
	}

	public String getMessage() {
		return message;
	}

	public long getNumber() {
		return number;
	}
	

}
