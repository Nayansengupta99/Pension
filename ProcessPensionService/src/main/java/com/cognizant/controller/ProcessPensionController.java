package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.InvalidPensionDetailException;
import com.cognizant.feign.PensionerDetailClient;
import com.cognizant.model.PensionDetail;
import com.cognizant.model.PensionerInput;
import com.cognizant.model.ProcessPensionInput;
import com.cognizant.service.ProcessPensionService;

@RestController
@RequestMapping("processpension")
public class ProcessPensionController {
	@Autowired
	ProcessPensionService pensionService;
	@Autowired
	PensionerDetailClient client;

	@PostMapping("/pensiondetail")
	public PensionerInput getDetailsFromUI(@RequestBody PensionerInput pensionerInput) {
		return pensionService.getPensionDetailsFromUI(pensionerInput);
	}

	@GetMapping("/pensionamount")
	public ResponseEntity<PensionDetail> getPensionAmount(@RequestBody PensionerInput pensionerInput)
			throws InvalidPensionDetailException {
		return pensionService.getCalculatedPension(pensionerInput);
	}

	@PostMapping("/bankcharge")

	public double getPensionDeductedCharge(@RequestBody ProcessPensionInput processPensionInput)
			throws InvalidPensionDetailException {
		return pensionService.getPensionDeductedCharge(processPensionInput);

	}

	@PostMapping("/processcode")
	public String getStatusMessage(@RequestBody ProcessPensionInput processPensionInput) {
		return pensionService.getStatusMessage(processPensionInput);
	}

}
