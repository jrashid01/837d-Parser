package com.abcbs.parser.dental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Driver837P {

	static final HashMap<String, String> loops = new HashMap<>();
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
		loops.put("2310D", "Supervising Provider Name");
		loops.put("2310E", "Ambulance PICK-UP Location");
		loops.put("2310F", "Ambulance DROP-OFF Location");

		loops.put("2320", "Other Subscriber Information");

		loops.put("2330A", "Other Subscriber Name");
		loops.put("2330B", "Other Payer Name");
		loops.put("2330C", "Other Payer Refering Provider");
		loops.put("2330D", "Other Payer Rendering Provider");
		loops.put("2330E", "Other Payer Service Facility Location");
		loops.put("2330F", "Other Payer Supervising Provider");
		loops.put("2330G", "Other Payer Billing Provider");

		loops.put("2400", "Service Line Number");

		loops.put("2410", "Drug IDEntification");

		loops.put("2420A", "Rendering Provider Name");
		loops.put("2420B", "Purchased Service Provider Name");
		loops.put("2420C", "Service Facility Location Name");
		loops.put("2420D", "Supervising Provider Name");

		loops.put("2420E", "Ordering Provider Name");
		loops.put("2420F", "Refering Provider Name");

		loops.put("2420G", "Ambulance Pick-Up Location");
		loops.put("2420H", "Ambulance Drop-OFF Location");

		loops.put("2430", "Line Adjudication Information");

		loops.put("2440", "Form Identification Code");

	}

	public static void main(String[] args) {
		System.out.println("parsing 837 P file");

		try {
			List<String> allLines = Files
					.readAllLines(Paths.get("C:/Users/rjilani/Documents/FEP/Payload/837-For-Demo/SPLIT.DENT.TXT"));

			System.out.println("Lines in file:" + allLines.size());

			List<String> segments1000A = new ArrayList<>();
			List<String> segments1000B = new ArrayList<>();
			List<String> segments2000A = new ArrayList<>();
			List<String> segments2010AA = new ArrayList<>();
			List<String> segments2010AB = new ArrayList<>();
			List<String> segments2010AC = new ArrayList<>();
			
			/////////////////
			
			////////////////

			List<String> segments2300 = new ArrayList<>();
			List<String> segments2320 = new ArrayList<>();
			List<String> segments2400 = new ArrayList<>();
			List<String> segments2430 = new ArrayList<>();
			List<String> segments2330B = new ArrayList<>();

			HashMap<String, List<String>> loopsSegmentsMap = new LinkedHashMap<>();
			String loop = "";
			String previousLoop = "1000A";
			int i = 0;
			for (String line : allLines) {

				if (line.startsWith("1") || line.startsWith("2")) {

					System.out
							.println("------------------------------------------------------------------------------");

					loop = line.substring(0, 6).strip();

					if (!previousLoop.equals(loop)) {
						System.out.println("previousLoop count: " + previousLoop + ":" + i);
						System.out.println(
								"------------------------------------------------------------------------------");
						i = 0;
						previousLoop = loop;
						i = 1;
					} else {
						i++;
					}

					System.out.println(
							"loop" + loop + " " + (loops.get(loop) != null ? loops.get(loop) : "not a valid loop"));

					System.out.println("Line size:" + line.length());

					String[] elements = line.split(" ");
					System.out.println("total elements:" + elements.length);
					System.out.println(line);

					addLinesToSegments(segments1000A, segments1000B, segments2000A, segments2010AA, segments2010AB, segments2010AC, segments2300, segments2320,
							segments2400, segments2430, segments2330B, loop, line);

					System.out.println("        ");
					for (String ele : elements) {

						if (ele.length() > 0) {

							System.out.println("lenght of element: " + ele + ": " + ele.length());
						}
					}

				}

			}
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("previousLoop count: " + previousLoop + ":" + i);
			System.out.println("------------------------------------------------------------------------------");

//			printLines(segments1000A);

			mapLoopsToSegments(segments1000A, segments1000B, segments2000A, segments2010AA, segments2010AB, segments2010AC, segments2300, segments2320,
					segments2330B, segments2400, segments2430, loopsSegmentsMap);

			iterateLoopsAndSegments(loopsSegmentsMap);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addLinesToSegments(List<String> segments1000A, List<String> segments1000B, List<String> segments2000A,
			List<String> segments2010AA, List<String> segments2010AB, List<String> segments2010AC, List<String> segments2300, List<String> segments2320,
			List<String> segments2400, List<String> segments2430, List<String> segments2330B, String loop,
			String line) {
		if (loop.equals("1000A")) {
			segments1000A.add(line);
		}

		if (loop.equals("1000B")) {
			segments1000B.add(line);
		}

		if (loop.equals("2000A")) {
			segments2000A.add(line);
		}

		if (loop.equals("2010AA")) {
			segments2010AA.add(line);
		}
		if (loop.equals("2000AB")) {
			segments2010AB.add(line);
		}
		
		if (loop.equals("2010AC")) {
			segments2010AC.add(line);
		}
		
		/////////
		
		
		////////

		if (loop.equals("2300")) {
			segments2300.add(line);
		}

		if (loop.equals("2320")) {
			segments2320.add(line);
		}

		if (loop.equals("2330B")) {
			segments2330B.add(line);
		}

		if (loop.equals("2400")) {
			segments2400.add(line);
		}

		if (loop.equals("2430")) {
			segments2430.add(line);
		}
		
		
	}

	private static void mapLoopsToSegments(List<String> segments1000A, List<String> segments1000B,
			List<String> segments2000A, List<String> segments2010AA, List<String> segments2010AB, List<String> segments2010AC, List<String> segments2300,
			List<String> segments2320, List<String> segments2330B, List<String> segments2400, List<String> segments2430,
			HashMap<String, List<String>> loopsSegmentsMap) {
		if (segments1000A.size() > 0) {
			loopsSegmentsMap.put("1000A", segments1000A);
		}

		if (segments1000B.size() > 0) {
			loopsSegmentsMap.put("1000B", segments1000B);
		}

		if (segments2000A.size() > 0) {
			loopsSegmentsMap.put("2000A", segments2000A);
		}

		if (segments2010AA.size() > 0) {
			loopsSegmentsMap.put("2010AA", segments2010AA);
		}
		if (segments2010AB.size() > 0) {
			loopsSegmentsMap.put("2010AB", segments2010AB);
		}

		if (segments2010AC.size() > 0) {
			loopsSegmentsMap.put("2010AC", segments2010AC);
		}
		
		/////////////////////
		
		
		///////////////////////////

		if (segments2300.size() > 0) {
			loopsSegmentsMap.put("2300", segments2300);
		}

		if (segments2320.size() > 0) {
			loopsSegmentsMap.put("2320", segments2320);
		}

		if (segments2330B.size() > 0) {
			loopsSegmentsMap.put("2330B", segments2330B);
		}

		if (segments2400.size() > 0) {
			loopsSegmentsMap.put("2400", segments2400);
		}

		if (segments2430.size() > 0) {
			loopsSegmentsMap.put("2430", segments2430);
		}
	}

	private static void printLines(List<String> segments) {
		System.out.println("------------------------------------------------------------------------------");

		for (String segment : segments) {
			System.out.println(segment);
		}
	}

	private static void iterateLoopsAndSegments(HashMap<String, List<String>> loopsToSegements) {
		for (String key : loopsToSegements.keySet()) {
			System.out.println("Key: " + key);
			if (key.equals("2000AB") || key.equals("2010AC")) {
				loopsToSegements.get(key).stream().forEach(System.out::println);
			}
		}
	}

}
