package com.abcbs.parser.dental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Driver837 {

	static HashMap<String, String> loops = new HashMap<>();
	static {

		loops.put("1000A", "Submitter Name");
		loops.put("1000B", "Receiver Name");

		// Billing Provider Detial
		loops.put("2000A", "Billing Provider Hierarchical Level");
		loops.put("2010AA", "Billing Provider Name");
		loops.put("2010AB", "Pay-To Address Name");
		loops.put("2010AC", "Pay-To Plan Name");

		// Subscriber Detail
		loops.put("2000B", "Subscriber Hierarchical Level");
		loops.put("2010BA", "Subscriber Name");
		loops.put("2010BB", "Payer Name");

		// Patient Detail
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

		loops.put("2410", "Claim Pricing/Repricing information");

		loops.put("2420A", "Rendering Provider Name");
		loops.put("2420B", "Asssitant Surgeon Name");
		loops.put("2420C", "Supervising Provider Name");
		loops.put("2420D", "Service Facility Location Name");

		loops.put("2430", "Line Adjudication Information");

	}

	public static void main(String[] args) {
		System.out.println("parsing 837 d file");

		System.out.println(loops.get("2300"));

		HashMap<String, List<String>> loopsToSegements = new LinkedHashMap<>();

		try {
			List<String> allLines = Files
					.readAllLines(Paths.get("C:/Users/rjilani/Documents/FEP/Payload/837-For-Demo/TC36.txt"));

			System.out.println("Lines in file:" + allLines.size());

			List<String> segments1000A = new ArrayList<>();
			HashMap<String, List<String>> loopsSegmentsMap = new LinkedHashMap<>();
			String loop = "";
			for (String line : allLines) {
				if (line.startsWith("1") || line.startsWith("2")) {

					System.out
							.println("------------------------------------------------------------------------------");

					loop = line.substring(0, 6).strip();

					System.out.println(
							"loop:" + loop + " " + (loops.get(loop) != null ? loops.get(loop) : "not a valid loop"));

					System.out.println("Line size:" + line.length());

					String[] elements = line.split(" ");
					System.out.println("total elements:" + elements.length);
					System.out.println(line);
					if (loop.equals("1000A")) {
						segments1000A.add(line);
					}
					
					System.out.println("        ");
					for (String ele : elements) {

						if (ele.length() > 0) {

							System.out.println("lenght of element: " + ele + ": " + ele.length());
						}
					}

				}

			}
			
			printLines(segments1000A);
			
			if (segments1000A != null ) {
				loopsSegmentsMap.put("1000A", segments1000A);
			}
			
			iterateLoopsAndSegments(loopsSegmentsMap);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printLines(List<String> segments) {
		System.out
		.println("------------------------------------------------------------------------------");
		
		for (String segment:segments) {
			System.out.println(segment);
		}
	}

	private static void iterateLoopsAndSegments(HashMap<String, List<String>> loopsToSegements) {
		for (String key : loopsToSegements.keySet()) {
			System.out.println("Key: " + key);
			if (key.equals("1000A")) {
				loopsToSegements.get(key).stream().forEach(System.out::println);
			}
		}
	}

}
