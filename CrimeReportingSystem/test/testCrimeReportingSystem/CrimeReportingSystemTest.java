package testCrimeReportingSystem;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import entity.Victim;
import dao.CrimeAnalysisServiceImpl;
import entity.Incident;
import entity.Suspect;
import entity.Evidence;

public class CrimeReportingSystemTest {

	@Test
	public void testCreateIncident() {
		CrimeAnalysisServiceImpl crimeAnalysis = new CrimeAnalysisServiceImpl();
		List<Victim> victims = new ArrayList<>();
		LocalDate dateOfBirth = LocalDate.parse("2001-09-21");
		Victim victim = new Victim("Antanin", "Ginista", dateOfBirth, "female", "Chennai");
		victims.add(victim);
		List<Suspect> suspects = new ArrayList<>();
		LocalDate dateOfBirth1 = LocalDate.parse("2001-09-28");
		Suspect suspect = new Suspect("Antanin", "Garry", dateOfBirth1, "Male", "Trichy");
		suspects.add(suspect);
		Evidence evidence = new Evidence("onspot", "victim'shome");
		LocalDate incidentDate = LocalDate.parse("2001-09-21");
		Incident incident = new Incident(109, "homicide", incidentDate, "Chennai", "Homicide_incident", "open", victims,
				suspects, evidence);
		boolean check = crimeAnalysis.createIncident(incident);
		assertTrue(check);

	}

	@Test
	public void testUpdateIncidentStatusSuccessfully() {

		CrimeAnalysisServiceImpl crimeAnalysis = new CrimeAnalysisServiceImpl();
		int incidentId = 1;
		String newStatus = "closed";

		boolean result = crimeAnalysis.updateIncidentStatus(newStatus, incidentId);

		assertTrue(result);
	}

	/* Test for invalid incident id */
	@Test
	public void testInvalidUpdateIncidentStatus() {

		CrimeAnalysisServiceImpl crimeAnalysis = new CrimeAnalysisServiceImpl();
		int incidentId = 99;
		String newStatus = "closed";

		boolean result = crimeAnalysis.updateIncidentStatus(newStatus, incidentId);

		assertFalse(result);
	}

}
