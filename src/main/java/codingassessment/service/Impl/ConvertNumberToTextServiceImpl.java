package codingassessment.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codingassessment.ConvertNumberToEnglishText;
import codingassessment.dto.NumInEnglishDTO;
import codingassessment.dto.NumberDTO;
import codingassessment.exception.CalculationException;
import codingassessment.exception.OverFlowException;
import codingassessment.service.ConvertNumberToTextService;

@Service
public class ConvertNumberToTextServiceImpl implements ConvertNumberToTextService {

	@Autowired
	ConvertNumberToEnglishText convertNumberToEnglishText;

	@Override
	public NumInEnglishDTO ConvertNumberToText(NumberDTO numberDTO) {

		long number = 0;
		int digitLength = 0;
		NumInEnglishDTO numInEnglishDTO = new NumInEnglishDTO();
		try {
			// Regex was added to remove leading zeros.
			String inputNumber = numberDTO.getNumber().trim().replaceFirst("^0+(?!$)", "");
			digitLength = inputNumber.length();

			if (Math.pow(10, digitLength) > Long.MAX_VALUE) {
				throw new OverFlowException("invalid input size");
			}
			number = Long.parseLong(inputNumber);

			numInEnglishDTO.setNumInEnglish(convertNumberToEnglishText.generalConverter(number));
			numInEnglishDTO.setStatus("ok");

		} catch (NumberFormatException e) {
			
			numInEnglishDTO.setStatus("invalid input format");
			
		} catch (CalculationException e) {

			numInEnglishDTO.setStatus(e.getMessage()+e.getNumber());
			
		} catch (OverFlowException e) {

			numInEnglishDTO.setStatus(e.getMessage());

		} catch (Exception e) {

			numInEnglishDTO.setStatus(e.getMessage());
		}

		return numInEnglishDTO;
	}

}
