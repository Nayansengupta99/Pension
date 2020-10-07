package com.cognizant.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.PensionerDetail;
import com.cognizant.service.PensionDetailService;

@RestController
@RequestMapping("pensionerdetailbyadhaar")
public class PensionerDetailController {

	@Autowired
	PensionDetailService pensionDetailService;

	@GetMapping("/{adhaarNumber}")
	public ResponseEntity<PensionerDetail> getAllPensionDetailByAdhaarNumber(
			@PathVariable("adhaarNumber") Long adhaarNumber) throws IOException, ParseException {
		return pensionDetailService.getAllPensionerDetails(adhaarNumber);
	}

}
