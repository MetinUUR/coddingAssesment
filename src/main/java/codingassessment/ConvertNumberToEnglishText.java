package codingassessment;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import codingassessment.exception.CalculationException;

/**
 *In this class, the English word equivalent of the long type input is produced. 
 * For the numbers that cannot be produced in English words, custom checked CalculationException is thrown.
 * 
 * @author Metin Ugur
 */
@Component
public class ConvertNumberToEnglishText {

	static HashMap<Long, String> unary = new HashMap<>();
	static HashMap<Long, String> teens = new HashMap<>();
	static HashMap<Long, String> tens = new HashMap<>();
	static HashMap<Integer, String> specialNames = new HashMap<>();

	static {

		// For irregular numbers, maps are created.

		unary.put(0l, "");
		unary.put(1l, " one");
		unary.put(2l, " two");
		unary.put(3l, " three");
		unary.put(4l, " four");
		unary.put(5l, " five");
		unary.put(6l, " six");
		unary.put(7l, " seven");
		unary.put(8l, " eight");
		unary.put(9l, " nine");
		unary.put(10l, " ten");

		teens.put(11l, " eleven");
		teens.put(12l, " twelve");
		teens.put(13l, " thirteen");
		teens.put(14l, " fourteen");
		teens.put(15l, " fifteen");
		teens.put(16l, " sixteen");
		teens.put(17l, " seventeen");
		teens.put(18l, " eighteen");
		teens.put(19l, " nineteen");

		tens.put(20l, " twenty");
		tens.put(30l, " thirty");
		tens.put(40l, " fourty");
		tens.put(50l, " fifty");
		tens.put(60l, " sixty");
		tens.put(70l, " seventy");
		tens.put(80l, " eighty");
		tens.put(90l, " ninety");

		specialNames.put(1, " thousand");
		specialNames.put(2, " million");
		specialNames.put(3, " billion");
		specialNames.put(4, " trillion");
		specialNames.put(5, " quadrillion");
		specialNames.put(6, " quintillion");
	}

	public String generalConverter(long number) throws CalculationException {

		if (number == 0) {
			return "zero";
		}

		long minusNumber = number;
		number = Math.abs(number);
		int decimal = 0;
		String stringNumber = Long.toString(number);
		long tempNumber = number;

		while (tempNumber > 0) {

			// decimal keeps number of per three digits.
			decimal++;
			tempNumber = tempNumber / 1000;
		}

		long n = number;
		String temp = "", content = "", parserInitializer = "";

		while (decimal > 0) {

			if (decimal > 1 && n != 0) {
				temp = specialNames.get(decimal - 1);
				n = (long) (n / (Math.pow(1000, decimal - 1)));

				// when we parse our number to per triple digits, parserInitializer gets value which starts the greatest digit. It should be less than 1000 and bigger than 0.
				parserInitializer = Long.toString(n);

				// Every numbers which are bigger than thousand, have same behaviour.So deepConverter method called for all numbers bigger than thousand.
				content = content + deepConverter(n) + temp;

				// stringNumber determined for obtaining remain value after deducted first greater digits. Digits number is determined by parserInitializer before.
				stringNumber = stringNumber.substring(parserInitializer.length(), stringNumber.length());

				// Regex was added to remove leading zeros.
				stringNumber = stringNumber.replaceFirst("^0+(?!$)", "");

				n = Long.parseLong(stringNumber);
			} else {
				content = content + deepConverter(n);
				break;
			}

			decimal--;
		}

		if (minusNumber < 0) {
			return "Minus " + content;
		}
		
		if (content.isEmpty()) {

			throw new CalculationException("input can not be calculated for:",number);
		}

		return content;

	}

	private String deepConverter(long number) {

		if (number < 11) {
			return unary.get(number);
		} else if (number < 20) {
			return teens.get(number);
		} else if (number < 100) {
			long tensNum = number / 10;
			long remainsNum = number % 10;
			if (remainsNum != 0) {
				return tens.get(tensNum * 10) + " " + unary.get(remainsNum);
			} else {
				return tens.get(tensNum * 10);
			}
		} else if (number < 1000) {
			long hundereds = number / 100;
			long remainsNum = number % 100;

			// recursion was used for analysis until the last step.
			return unary.get(hundereds) + " hundred" + deepConverter(remainsNum);
		}

		return null;

	}
}