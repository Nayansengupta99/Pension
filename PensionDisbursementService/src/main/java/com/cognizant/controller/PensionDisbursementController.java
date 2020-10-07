package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.ProcessPensionInput;
import com.cognizant.service.PensionDisbursementService;

@RestController
@RequestMapping("/pensiondisbursement")
public class PensionDisbursementController {

	@Autowired
	PensionDisbursementService service;
    
	@PostMapping("/disbursepension")
	public int ProcessPensionResponse(@RequestBody ProcessPensionInput processPensionInput) {
		return service.ProcessPensionResponse(processPensionInput);
	}

}
