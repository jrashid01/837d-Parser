package com.abcbs.parser.dental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Driver837 {

	static HashMap<String, String> loops = new HashMap<>();
	static {

		loops.put("1000A", "Submitter Name");
		loops.put("1000B", "Receiver Name");
		
		//Billing Provider Detial
		loops.put("2000A", "Billing Provider Hierarchical Level");
		loops.put("2010AA", "Billing Provider Name");
		loops.put("2010AB", "Pay-To Address Name");
		loops.put("2010AC", "Pay-To Plan Name");
		
		//Subscriber Detail
		loops.put("2000B", "Subscriber Hierarchical Level");
		loops.put("2010BA", "Subscriber Name");
		loops.put("2010BB", "Payer Name");
		
		//Patient Detail
		loops.put("2000C", "Patient Hierarchical Level");
		loops.put("2010CA", "Patient Name");
		
		loops.put("2300", "Claim Infomration");
		
		loops.put("2310A", "Refering Provider Name");
		loops.put("2310B", "Rendering Provider Name");
		loops.put("2310C", "Service Facility Location");
		loops.put("2310D", "Assistant Surgeon Name");
		loops.put("2310E", "Supervising Provider Name");
		
		loops.put("2320", "Other Subscriber Information");
		
		loops.put("2330A", "Other Subscriber Name");
		loops.put("2330B", "Other Payer Name");
		loops.put("2330C", "Other Payer Refering Provider");
		loops.put("2330D", "Other Payer Rendering Provider");
		loops.put("2330E", "Other Payer Supervising Provider");
		loops.put("2330F", "Other Payer Billing Provider");
		loops.put("2330G", "Other Payer Service Facility Location");
		loops.put("2330H", "Other Payer Assistant Surgeon");
		
		loops.put("2400", "Service Line Number");
		
		loops.put("2420A", "Rendering Provider Name");
		loops.put("2420B", "Asssitant Surgeon Name");
		loops.put("2420C", "Supervising Provider Name");
		loops.put("2420D", "Service Facility Location Name");
		
		loops.put("2430", "Line Adjudication Information");
		



	}

	public static void main(String[] args) {
		System.out.println("parsing 837 d file");

		System.out.println(loops.get("2300"));

		try {
			List<String> allLines = Files.readAllLines(
					Paths.get("C:/Users/rjilani/Documents/FEP/Payload/837-For-Demo/EDI_2503699DO01T10.txt"));

			System.out.println("Lines in file:" + allLines.size());

			int i = 0;
			for (String line : allLines) {
				if (line.startsWith("1") || line.startsWith("2")) {
					System.out
							.println("------------------------------------------------------------------------------");
					i = 1;
					if (i == 1) {
						String loop = line.substring(0, 6).strip();
						System.out.println("loop:" + loop + " " + loops.get(loop));
					}

					System.out.println("Line size:" + line.length());

//				System.out.println(line.substring(78, 87));

					String[] elements = line.split(" ");
					System.out.println("total elements:" + elements.length);
//					System.out.println("        ");
					System.out.println(line);
					System.out.println("        ");
					for (String ele : elements) {

						if (ele.length() > 0) {

							System.out.println("lenght of element: " + ele + ": " + ele.length());
						}
					}

				}

				i = 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
