package entity;

import java.time.LocalDate;
import java.util.List;

public class Incident {
	private int incidentId;
	private String incidentType;
	private LocalDate incidentDate;
	private String location;
	private String description;
	private String status;
	private List<Victim> victims;
	private List<Suspect> suspects;
	private Evidence evidence;

	public Incident(String incidentType, LocalDate incidentDate, String location, String description, String status,
			List<Victim> victims, List<Suspect> suspects, Evidence evidence) {
		super();
		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.location = location;
		this.description = description;
		this.status = status;
		this.victims = victims;
		this.suspects = suspects;
		this.evidence = evidence;
	}

	public Incident(int incidentId, String incidentType, LocalDate incidentDate, String location, String description,
			String status, List<Victim> victims, List<Suspect> suspects, Evidence evidence) {
		super();
		this.incidentId = incidentId;
		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.location = location;
		this.description = description;
		this.status = status;
		this.victims = victims;
		this.suspects = suspects;
		this.evidence = evidence;
	}
	public Incident(int incidentId, String incidentType, LocalDate incidentDate, String location, String description,
			String status) {
		super();
		this.incidentId = incidentId;
		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.location = location;
		this.description = description;
		this.status = status;
	}

	public List<Victim> getVictims() {
		return victims;
	}

	public void setVictims(List<Victim> victims) {
		this.victims = victims;
	}

	public List<Suspect> getSuspects() {
		return suspects;
	}

	public void setSuspects(List<Suspect> suspects) {
		this.suspects = suspects;
	}

	public Evidence getEvidence() {
		return evidence;
	}

	public void setEvidence(Evidence evidence) {
		this.evidence = evidence;
	}

	public Incident() {

	}

	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}

	public LocalDate getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(LocalDate incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Incident [incidentId=" + incidentId + ", incidentType=" + incidentType + ", incidentDate="
				+ incidentDate + ", location=" + location + ", description=" + description + ", status=" + status
				+ "\n victims=" + victims + "\n suspects=" + suspects + "\n evidence=" + evidence + "]\n";
	}

	
}
