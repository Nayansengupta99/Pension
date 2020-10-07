package com.cognizant.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionerInput {

	private String name; 
	private Date dateOfBirth;
    private Long panNumber;
    private Long adhaarNumber;
    private String pensionGroup;

	
}
