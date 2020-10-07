package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.exception.InvalidPensionDetailException;
import com.cognizant.feign.PensionDisbursementClient;
import com.cognizant.feign.PensionerDetailClient;
import com.cognizant.model.PensionDetail;
import com.cognizant.model.PensionerDetail;
import com.cognizant.model.PensionerInput;
import com.cognizant.model.ProcessPensionInput;

@Service
public class ProcessPensionService {

	@Autowired
	PensionerDetailClient client;
	@Autowired
	PensionDisbursementClient disbursementClient;

	public PensionerInput getPensionDetailsFromUI(PensionerInput pensionerInput) {

		PensionerInput pensionInputs = new PensionerInput(pensionerInput.getName(), pensionerInput.getDateOfBirth(),
				pensionerInput.getPanNumber(), pensionerInput.getAdhaarNumber(), pensionerInput.getPensionGroup());

		return pensionInputs;

	}

	public ResponseEntity<PensionDetail> getCalculatedPension(PensionerInput pensionerInput)
			throws InvalidPensionDetailException {

		PensionerDetail pensionerDetail = client.getAllPensionDetailByAdhaarNumber(pensionerInput.getAdhaarNumber())
				.getBody();
		PensionerInput pensionInput = getPensionDetailsFromUI(pensionerInput);
		Double pensionAmount = 0.00;

		if (pensionInput.getName().equals(pensionerDetail.getName())
				&& pensionInput.getDateOfBirth().equals(pensionerDetail.getDateOfBirth())) {
			if (pensionerDetail.getPensionGroup().equalsIgnoreCase("Self")) {
				pensionAmount = pensionerDetail.getSalaryEarned() * 0.8 + pensionerDetail.getAllowance();
				return ResponseEntity.status(HttpStatus.OK)
						.body(new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth(),
								pensionerDetail.getPanNumber(), pensionerDetail.getPensionGroup(), pensionAmount));
			} else {
				pensionAmount = pensionerDetail.getSalaryEarned() * 0.5 + pensionerDetail.getAllowance();
				return ResponseEntity.status(HttpStatus.OK)
						.body(new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth(),
								pensionerDetail.getPanNumber(), pensionerDetail.getPensionGroup(), pensionAmount));
			}

		} else {
			throw new InvalidPensionDetailException();
		}

	}

	public double getPensionDeductedCharge(ProcessPensionInput processPensionInput)
			throws InvalidPensionDetailException {
		double bankcharge = 0;
		PensionerDetail pensionerDetail = client
				.getAllPensionDetailByAdhaarNumber(processPensionInput.getAdhaarNumber()).getBody();
		ResponseEntity<PensionDetail> calculatedPension = getCalculatedPension(new PensionerInput(
				pensionerDetail.getName(), pensionerDetail.getDateOfBirth(), pensionerDetail.getPanNumber(),
				processPensionInput.getAdhaarNumber(), pensionerDetail.getBankType()));
		if (pensionerDetail.getBankType().equalsIgnoreCase("Private")) {
			bankcharge = calculatedPension.getBody().getPensionAmount() - 550;
		} else if (pensionerDetail.getBankType().equalsIgnoreCase("Public")) {
			bankcharge = calculatedPension.getBody().getPensionAmount() - 500;
		} else
			bankcharge = calculatedPension.getBody().getPensionAmount();

		return bankcharge;

	}

	public String getStatusMessage(ProcessPensionInput processPensionInput) {
		int code = disbursementClient.ProcessPensionResponse(processPensionInput);
		if (code == 10)
			return "Pension disbursement Success";
		else
			return "Pension amount calculated is wrong, Please redo the calculation.";
	}

}
