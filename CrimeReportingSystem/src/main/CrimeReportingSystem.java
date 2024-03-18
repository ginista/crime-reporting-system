package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.CrimeAnalysisServiceImpl;
import entity.Evidence;
import entity.Incident;
import entity.Report;
import entity.Suspect;
import entity.Victim;

public class CrimeReportingSystem {

	public static void main(String args[]) {
		CrimeAnalysisServiceImpl crimeAnalysis = new CrimeAnalysisServiceImpl();
		Scanner sc = new Scanner(System.in);
		boolean isRepeat = true;
		while (isRepeat) {
			System.out.println(
					"1. Create Incident\n2. Update Incident Status\n3. getIncidents In date Range\n4. Search Incidents\n5. Generate Incident Report\n6. Exit");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				// create incident
				System.out.println("Enter Incident type[Robbery,Homicide,Theft]:");
				String incidentType = sc.next();
				System.out.println("Enter Incident Date[YYYY-MM-DD]: ");
				LocalDate incidentdate = LocalDate.parse(sc.next());
				System.out.println("Enter Location:");
				String location = sc.next();
				System.out.println("Enter Description: ");
				String description = sc.next();
				System.out.println("Enter status[open,closed,underInvestigation]: ");
				String status = sc.next();

				/* Victim Details */

				List<Victim> victims = new ArrayList<>();
				boolean addMoreVictims = true;
				while (addMoreVictims) {
					System.out.println("Add Victim (0 to finish): ");
					int victimId = sc.nextInt();
					if (victimId == 0) {
						addMoreVictims = false;
					} else {
						System.out.println("Enter Victim's First Name:");
						String firstName = sc.next();
						System.out.println("Enter Victim's Last Name:");
						String lastName = sc.next();
						System.out.println("Enter Victim's dob (YYYY-MM-DD): ");
						LocalDate dateOfBirth = LocalDate.parse(sc.next());
						System.out.println("Enter Victim's gender:");
						String gender = sc.next();
						sc.nextLine(); // Consume newline
						System.out.println("Enter Victim's Contact Information: ");
						String contactInformation = sc.nextLine();
						Victim victim = new Victim(firstName, lastName, dateOfBirth, gender, contactInformation);
						victims.add(victim);

					}

				}
				//System.out.println(victims);
				/* Suspect Details */
				List<Suspect> suspects = new ArrayList<>();
				boolean addMoreSuspects = true;
				while (addMoreSuspects) {
					System.out.println("Add Suspect (0 to finish): ");
					int suspectId = sc.nextInt();
					if (suspectId == 0) {
						addMoreSuspects = false;
					} else {
						System.out.println("Enter Suspects's First Name:");
						String firstName = sc.next();
						System.out.println("Enter Suspects's Last Name:");
						String lastName = sc.next();
						System.out.println("Enter Suspects's dob: ");
						LocalDate dateOfBirth = LocalDate.parse(sc.next());
						System.out.println("Enter Suspects's gender:");
						String gender = sc.next();
						System.out.println("Enter Suspects's Contact Information: ");
						String contactInformation = sc.next();
						Suspect suspect = new Suspect(firstName, lastName, dateOfBirth, gender, contactInformation);
						suspects.add(suspect);
					}
				}
				/* Evidence Details */

				System.out.println("Enter Description about the evidence: ");
				String description1 = sc.next();
				System.out.println("Enter evident location found");
				String locationFound = sc.next();

				Evidence evidence = new Evidence(description, locationFound);

				Incident incident = new Incident(incidentType, incidentdate, location, description, status, victims,
						suspects, evidence);
				
				boolean check = crimeAnalysis.createIncident(incident);
				if (check) {
					System.out.println("Incident added successfully");
				} else {
					System.out.println("Something went wrong");
				}
				break;
			case 2:
				System.out.println("Enter incident Id:");
				int incidentId = sc.nextInt();
				System.out.println("Enter status[open,closed,underinvestigation]:");
				status = sc.next();
				check = crimeAnalysis.updateIncidentStatus(status, incidentId);
				if (check) {
					System.out.println("Updated successfully for the incident id" + incidentId);
				}
				break;

			case 3:
				System.out.println("Enter Incident start date[YYYY-MM-DD]: ");
				LocalDate startDate = LocalDate.parse(sc.next());
				System.out.println("Enter Incident end date[YYYY-MM-DD]: ");
				LocalDate endDate = LocalDate.parse(sc.next());
				List<Incident> incidents1 = crimeAnalysis.getIncidentsInDateRange(startDate, endDate);
				System.out.println(incidents1);
				break;

			case 4:
				// search incident by incident type
				System.out.println("Enter incident type[theft,robbery,homicide]:");
				incidentType = sc.next();
				List<Incident> incidents = crimeAnalysis.searchIncidents(incidentType);
				System.out.println(incidents);
				break;

			case 5:
				System.out.println("Enter Incident Id: ");
				incidentId = sc.nextInt();
				/*
				 * System.out.println("Enter Incident type[Robbery,Homicide,Theft]:");
				 * incidentType = sc.next();
				 * System.out.println("Enter Incident Date[YYYY-MM-DD]: "); incidentdate =
				 * LocalDate.parse(sc.next()); System.out.println("Enter Location:"); location =
				 * sc.next(); System.out.println("Enter Description: "); description =
				 * sc.next();
				 * System.out.println("Enter status[open,closed,underInvestigation]: ");
				 */
				// status = sc.next();
				// Incident incident2 = new Incident(incidentId, incidentType, incidentdate,
				// location, description, status);
				List<Report> report = crimeAnalysis.generateIncidentReport(incidentId);
				if (!report.isEmpty())
					System.out.println(report);
				break;
			case 6:
				isRepeat = false;
				break;
			default:
				System.out.println("Invalid choice...Enter from 1 to 6 ");

			}
		}
	}
}
