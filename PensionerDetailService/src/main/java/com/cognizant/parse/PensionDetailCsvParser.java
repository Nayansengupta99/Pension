package com.cognizant.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.model.PensionerDetail;

public class PensionDetailCsvParser {

	public List<PensionerDetail> readFromCsv(String fileName) throws IOException, ParseException {

		List<PensionerDetail> details = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
		String line = br.readLine();
		while (line != null) {
			String[] attributes = line.split(",");
			PensionerDetail detail = createDetail(attributes);
			details.add(detail);
			line = br.readLine();
		}

		return details;
	}

	private PensionerDetail createDetail(String[] metadata) throws ParseException {

		String name = metadata[1];
		Date dateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse(metadata[2]);
		Long panNumber = Long.parseLong(metadata[3]);
		Long adhaarNumber = Long.parseLong(metadata[4]);
		double salaryEarned = Double.parseDouble(metadata[5]);
		double allowance = Double.parseDouble(metadata[6]);
		String pensionGroup = metadata[7];
		String bankName = metadata[8];
		Long accountNumber = Long.parseLong(metadata[9]);
		String bankType = metadata[10];

		return new PensionerDetail(name, dateOfBirth, panNumber, adhaarNumber, salaryEarned, allowance, pensionGroup,
				bankName, accountNumber, bankType);

	}

}
