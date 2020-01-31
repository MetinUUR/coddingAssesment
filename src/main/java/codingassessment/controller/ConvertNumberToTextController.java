package codingassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import codingassessment.request.NumberRequest;
import codingassessment.response.NumberResponse;
import codingassessment.service.ConvertNumberToTextService;

@RestController
public class ConvertNumberToTextController {

	@Autowired
	ConvertNumberToTextService service;

	@RequestMapping(value = "num_to_english", method = RequestMethod.GET)
	public NumberResponse getEnglishDescriptionFromNumber(@RequestBody NumberRequest request) {

		NumberResponse response = new NumberResponse();
		response.setNumInEnglishDTO(service.ConvertNumberToText(request.getNumberDTO()));

		return response;
	}

}
