package com.cognizant.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PensionDetail {

	private String name; 
	private Date dateOfBirth;
    private Long panNumber;
    private String pensionGroup;
    private double pensionAmount;

}
