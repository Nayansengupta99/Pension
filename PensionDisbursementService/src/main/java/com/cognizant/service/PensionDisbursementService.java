package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.feign.PensionerDetailClient;
import com.cognizant.model.PensionerDetail;
import com.cognizant.model.ProcessPensionInput;

@Service
public class PensionDisbursementService {

	@Autowired
	PensionerDetailClient pensionerClient;

	public int ProcessPensionResponse(ProcessPensionInput processPensionInput) {
		PensionerDetail pensionerDetail = pensionerClient
				.getAllPensionDetailByAdhaarNumber(processPensionInput.getAdhaarNumber()).getBody();
		if (pensionerDetail.getBankType().equalsIgnoreCase("Public")
				|| pensionerDetail.getBankType().equalsIgnoreCase("Private")) {
			return 10;
		} else {
			return 21;
		}
	}

}
