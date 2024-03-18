package dao;

import java.util.*;

import entity.Incident;
import entity.Report;

import java.time.LocalDate;

public interface ICrimeAnalysisService {
	
	// Create a new incident
	boolean createIncident(Incident incident);
	
	// Update the status of an incident
	boolean updateIncidentStatus(String status,int incidentId);
	
	// Get a list of incidents within a date range
	List<Incident> getIncidentsInDateRange(LocalDate startDate, LocalDate endDate);
	
	// Search for incidents based on various criteria
	List<Incident>searchIncidents(String incidentType);
	
	// Generate incident reports
	List<Report>generateIncidentReport(int incidentId);
	
}
