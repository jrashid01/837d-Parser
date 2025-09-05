package com.abcbs.parser.dental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindLX {
	public static void main(String[] args) throws IOException {

		String allLines = Files.readString(
				Paths.get("C:/Users/rjilani/Documents/FEP/Payload/837-For-Demo/EDI_Instituitional_2521316484.txt"));
//		System.out.println(allLines); 

		// String input = "Details of all persons. Person=details=John Smith-age-22;
		// Person=details=Alice Kohl-age-23; Person=details=Ram Mohan-city-Dallas;
		// Person=details=Michael Jack-city-Boston;";
		Pattern pattern = Pattern.compile("[0-9]*LX");
		Matcher matcher = pattern.matcher(allLines);
		int i = 0;
		while (matcher.find()) {
			String str = matcher.group();
			System.out.println(str);
			i++;
			if (i >= 50)
				break;
		}

		System.out.println(i);

		String ContractNumber = getContractNumber(allLines);

		System.out.println(ContractNumber);
	}

	static String getContractNumber(String payload) {

		Pattern regex2300ICN = Pattern.compile("2010BA0001NM1 [a-zA-Z0-9\\s]*");
		Matcher m = regex2300ICN.matcher(payload);
		String contractNumber = "";
		if (m.find()) {
			contractNumber = m.group().substring(154, 163);
			System.out.println(contractNumber);
		}

		return contractNumber;
	}

}
