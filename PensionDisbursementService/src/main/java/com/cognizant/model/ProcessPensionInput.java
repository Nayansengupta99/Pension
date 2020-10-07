package com.cognizant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessPensionInput {

	private Long adhaarNumber;
	private double pensionAmount;
	
	private int serviceCharge;
	

	public ProcessPensionInput(Long adhaarNumber, double pensionAmount) {
		super();
		this.adhaarNumber = adhaarNumber;
		this.pensionAmount = pensionAmount;
	}
}
