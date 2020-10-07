package com.cognizant.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class PensionerDetail {

	private String name;
    private Date dateOfBirth;
    private Long panNumber;
    private double salaryEarned;
    private double allowance;
    private String pensionGroup;
    private String bankName;
    private Long accountNumber;
    private String bankType;
   

    

}
