package codingassessment.service;

import codingassessment.dto.NumInEnglishDTO;
import codingassessment.dto.NumberDTO;

public interface ConvertNumberToTextService {
	
	public NumInEnglishDTO ConvertNumberToText(NumberDTO numberDTO); 

}
