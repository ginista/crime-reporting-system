package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Agency;
import entity.Evidence;
import entity.Incident;
import entity.Officer;
import entity.Report;
import entity.Suspect;
import entity.Victim;
import exception.IncidentNumberNotFoundException;
import util.DBConnection;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

	private Connection connection;
	private static final String CREATE_INCIDENT = "INSERT INTO incidents (incident_type,incident_date,location,description,status) VALUES ( ?, ?,?,?,?)";
	private static final String SEARCH_BY_INCIDENTTYPE = "SELECT * FROM incidents WHERE incident_type=?";
	private static final String UPDATE_INCIDENT_STATUS = "UPDATE incidents set status=? where incident_id=?";
	private static final String Incident_RANGE = "SELECT * FROM incidents WHERE incident_date between ? and ?";
	// private static final String INCIDENT_REPORT = "SELECT * FROM reports where
	// incident_id =?";
	private static final String GENERATE_REPORT = "select * from reports as r  inner join officers as o on r.officer_id = o.officer_id inner join law_enforcement_agencies as a on o.agency_id = a.agency_id where incident_id = ?";

	public CrimeAnalysisServiceImpl() {
		connection = DBConnection.createConnection();
	}

	@Override
	public boolean createIncident(Incident incident) {
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_INCIDENT, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, incident.getIncidentType());
			statement.setDate(2, Date.valueOf(incident.getIncidentDate()));
			statement.setString(3, incident.getLocation());
			statement.setString(4, incident.getDescription());
			statement.setString(5, incident.getStatus());
			statement.executeUpdate();

			// Retrieve the auto-generated incident_id
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				int incidentId = generatedKeys.getInt(1);
				// Now you have the incident_id
				incident.setIncidentId(incidentId);
			}

			insertVictims(incident);
			insertSuspects(incident);
			insertEvidence(incident);
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	private void insertVictims(Incident incident) throws SQLException {
		// System.out.println("IncidentId:"+ incident.getIncidentId());
		if (incident.getVictims() != null) {
			String sql = "INSERT INTO victims (first_name, last_name, dateOfBirth, gender, contactInformation,incident_id) VALUES ( ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				for (Victim victim : incident.getVictims()) {

					ps.setInt(6, incident.getIncidentId());
					ps.setString(1, victim.getFirstName());
					ps.setString(2, victim.getLastName());
					ps.setDate(3, Date.valueOf(victim.getDateOfBirth()));
					ps.setString(4, victim.getGender());
					ps.setString(5, victim.getContactInformation());
					ps.addBatch();
				}
				ps.executeBatch();
			}
		}
	}

	private void insertSuspects(Incident incident) throws SQLException {
		if (incident.getSuspects() != null) {
			String sql = "INSERT INTO suspects ( first_name, last_name, dateOfBirth, gender, contactInformation,incident_id) VALUES ( ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				for (Suspect suspect : incident.getSuspects()) {
					ps.setInt(6, incident.getIncidentId());
					ps.setString(1, suspect.getFirstName());
					ps.setString(2, suspect.getLastName());
					ps.setDate(3, Date.valueOf(suspect.getDateOfBirth()));
					ps.setString(4, suspect.getGender());
					ps.setString(5, suspect.getContactInformation());
					ps.addBatch();
				}
				ps.executeBatch();
			}
		}
	}

	private void insertEvidence(Incident incident) throws SQLException {
		if (incident.getEvidence() != null) {
			String sql = "INSERT INTO evidence (incident_id, description, location_found) VALUES (?, ?, ?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {

				ps.setInt(1, incident.getIncidentId());
				ps.setString(2, incident.getDescription());
				ps.setString(3, incident.getLocation());
				ps.executeUpdate();

			}
		}
	}

	@Override
	public List<Incident> searchIncidents(String incidentType) {
		List<Incident> incidents = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(SEARCH_BY_INCIDENTTYPE);
			statement.setString(1, incidentType);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int incidentId = resultSet.getInt("incident_id");
				LocalDate incidentDate = resultSet.getDate("incident_date").toLocalDate();
				String location = resultSet.getString("location");
				String description = resultSet.getString("description");
				String status = resultSet.getString("status");

				// Fetch victims for the incident
				List<Victim> victims = getVictimsForIncident(incidentId);

				// Fetch suspects for the incident
				List<Suspect> suspects = getSuspectsForIncident(incidentId);

				Evidence evidence = getEvidenceForIncident(incidentId);
				Incident incident = new Incident(incidentId, incidentType, incidentDate, location, description, status,
						victims, suspects, evidence);
				incidents.add(incident);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return incidents;
	}

	@Override
	public boolean updateIncidentStatus(String status, int incidentId) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_INCIDENT_STATUS);
			statement.setString(1, status);
			statement.setInt(2, incidentId);
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 0) {
				throw new IncidentNumberNotFoundException("Incident id not found " + incidentId);
			}
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncidentNumberNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Incident> getIncidentsInDateRange(LocalDate startDate, LocalDate endDate) {
		List<Incident> incidents = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(Incident_RANGE);
			statement.setDate(1, Date.valueOf(startDate));
			statement.setDate(2, Date.valueOf(endDate));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int incidentId = resultSet.getInt("incident_id");
				String incidentType = resultSet.getString("incident_type");
				LocalDate incidentDate = resultSet.getDate("incident_date").toLocalDate();
				String location = resultSet.getString("location");
				String description = resultSet.getString("description");
				String status = resultSet.getString("status");

				System.out.println(incidentId);
				// Fetch victims for the incident
				List<Victim> victims = getVictimsForIncident(incidentId);

				// Fetch suspects for the incident
				List<Suspect> suspects = getSuspectsForIncident(incidentId);

				Evidence evidence = getEvidenceForIncident(incidentId);
				Incident incident = new Incident(incidentId, incidentType, incidentDate, location, description, status,
						victims, suspects, evidence);
				incidents.add(incident);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incidents;
	}

	private List<Victim> getVictimsForIncident(int incidentId) throws SQLException {
		List<Victim> victims = new ArrayList<>();
		String query = "SELECT * FROM victims WHERE incident_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, incidentId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int victimId = resultSet.getInt("victim_id");
				// Construct Victim object and add to the list
				Victim victim = new Victim(resultSet.getString("first_name"), resultSet.getString("last_name"),
						resultSet.getDate("dateOfBirth").toLocalDate(), resultSet.getString("gender"),
						resultSet.getString("contactInformation"));
				victim.setVictimId(victimId);
				victims.add(victim);
			}
		}
		return victims;
	}

	private List<Suspect> getSuspectsForIncident(int incidentId) throws SQLException {
		List<Suspect> suspects = new ArrayList<>();
		String query = "SELECT * FROM suspects WHERE incident_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, incidentId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int suspectId = resultSet.getInt("suspect_id");
				// Construct Suspect object and add to the list
				Suspect suspect = new Suspect(resultSet.getString("first_name"), resultSet.getString("last_name"),
						resultSet.getDate("dateOfBirth").toLocalDate(), resultSet.getString("gender"),
						resultSet.getString("contactInformation"));
				suspect.setSuspectId(suspectId);
				suspects.add(suspect);
			}
		}
		return suspects;
	}

	private Evidence getEvidenceForIncident(int incidentId) throws SQLException {
		Evidence evidence = null;
		String query = "SELECT * FROM evidence WHERE incident_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, incidentId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int evidentId = resultSet.getInt("evident_id");
				// Construct Evidence object
				evidence = new Evidence(resultSet.getString("description"), resultSet.getString("location_found"));
				evidence.setEvidentId(evidentId);
			}
		}
		return evidence;
	}

	@Override
	public List<Report> generateIncidentReport(int incidentId) {
		List<Report> reports = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(GENERATE_REPORT);
			statement.setInt(1, incidentId);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				throw new IncidentNumberNotFoundException("Incident id not found " + incidentId);
			} else {
				// Reset cursor to before first row
				resultSet.beforeFirst();
				Incident incident = fetchIncidentDetails(incidentId);
				while (resultSet.next()) {
					int reportId = resultSet.getInt("report_id");
					LocalDate reportDate = resultSet.getDate("report_date").toLocalDate();
					String reportDetails = resultSet.getString("report_details");
					String reportStatus = resultSet.getString("status");
					int officerId = resultSet.getInt("officer_id");
					String firstName = resultSet.getString("first_name");
					String lastName = resultSet.getString("last_name");
					String badgeNumber = resultSet.getString("badge_number");
					String officerRank = resultSet.getString("officer_rank");
					String officerContactInformation = resultSet.getString(12);
					int agencyId = resultSet.getInt("agency_id");
					String agencyName = resultSet.getString("agency_name");
					String jurisdiction = resultSet.getString("jurisdiction");
					String agencyContactInformation = resultSet.getString(17);

					Agency agency = new Agency(agencyId, agencyName, jurisdiction, agencyContactInformation, null);
					Officer officer = new Officer(officerId, firstName, lastName, badgeNumber, officerRank,
							officerContactInformation, agency);

					Report report = new Report(reportId, reportDate, reportDetails, reportStatus, officer, incident);
					reports.add(report);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncidentNumberNotFoundException e) {
			System.out.println(e.getMessage());

		}
		return reports;
	}

	private Incident fetchIncidentDetails(int incidentId) throws SQLException, IncidentNumberNotFoundException {
		// SQL query to fetch incident details from the database
		String query = "SELECT * FROM incidents WHERE incident_id = ?";
		Incident incident = null;

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, incidentId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				// Retrieve incident details from the result set
				String incidentType = resultSet.getString("incident_type");
				LocalDate incidentDate = resultSet.getDate("incident_date").toLocalDate();
				String location = resultSet.getString("location");
				String description = resultSet.getString("description");
				String status = resultSet.getString("status");

				// Fetch victims for the incident
				List<Victim> victims = getVictimsForIncident(incidentId);

				// Fetch suspects for the incident
				List<Suspect> suspects = getSuspectsForIncident(incidentId);

				Evidence evidence = getEvidenceForIncident(incidentId);
				// Create the Incident object
				incident = new Incident(incidentId, incidentType, incidentDate, location, description, status, victims,
						suspects, evidence);
			} else {
				throw new IncidentNumberNotFoundException("Incident not found with id: " + incidentId);
			}
		}

		return incident;
	}
}