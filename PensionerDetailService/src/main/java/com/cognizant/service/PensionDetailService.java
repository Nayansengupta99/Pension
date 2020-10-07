package com.cognizant.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.model.PensionerDetail;
import com.cognizant.parse.PensionDetailCsvParser;

@Service
public class PensionDetailService {

	public ResponseEntity<PensionerDetail> getAllPensionerDetails(Long adhaarNumber)
			throws IOException, ParseException {

		PensionDetailCsvParser p = new PensionDetailCsvParser();
		List<PensionerDetail> savedPensionDetails = p.readFromCsv("/Users/nayan/Desktop/PensionDetailsCsv1.csv");
		PensionerDetail pensionDetail = new PensionerDetail();
		for (PensionerDetail d : savedPensionDetails) {
			if (d.getAdhaarNumber().equals(adhaarNumber)) {
				PensionerDetail pensionDetails = new PensionerDetail(d.getName(), d.getDateOfBirth(), d.getPanNumber(),
						d.getAdhaarNumber(), d.getSalaryEarned(), d.getAllowance(), d.getPensionGroup(),
						d.getBankName(), d.getAccountNumber(), d.getBankType());

				pensionDetail = pensionDetails;
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(pensionDetail);

	}

}
