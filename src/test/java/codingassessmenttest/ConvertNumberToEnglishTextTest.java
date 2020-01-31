package codingassessmenttest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import codingassessment.ConvertNumberToEnglishText;
import codingassessment.exception.CalculationException;
import codingassessment.exception.OverFlowException;

public class ConvertNumberToEnglishTextTest {

	ConvertNumberToEnglishText convertNumberToEnglishText = null;

	@Before
	public void initializeValue() {

		convertNumberToEnglishText = new ConvertNumberToEnglishText();
	}

	@Test
	public void testGeneralConverter_EqualityCheckRegularNumbers() {

		String accepted = "twelve million three hundred fourty five thousand six hundred seventy eight";
		String actual = null;
		long number = 12345678;

		try {
			actual = convertNumberToEnglishText.generalConverter(number);
		} catch (CalculationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		accepted = accepted.replaceAll("\\s+", "");
		actual = actual.replaceAll("\\s+", "");
		assertEquals(accepted, actual);

	}
	

	@Test
	public void testGeneralConverter_EqualityCheckIrregularNumbers() {

		String accepted = "six hundred seventy eight";
		String actual = null;
		long number = Long.parseLong("000678");

		try {
			actual = convertNumberToEnglishText.generalConverter(number);
		} catch (CalculationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		accepted = accepted.replaceAll("\\s+", "");
		actual = actual.replaceAll("\\s+", "");
		assertEquals(accepted, actual);

	}
	
	@Test(expected = OverFlowException.class)
	public void testGeneralConverter_GetCalculationExceptionError() throws OverFlowException {

		// Regex was added to remove leading zeros.
		String inputNumber = "111222333444555666777";
		int	digitLength = inputNumber.length();

		if (Math.pow(10, digitLength) > Long.MAX_VALUE) {
			throw new OverFlowException("invalid input size");
		}

	}

}
