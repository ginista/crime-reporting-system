package entity;

import java.util.List;

public class Agency {
	private int agencyId;
	private String agencyName;
	private String jurisdiction;
	private String contactInformation;
	private List<Officer> officers;

	
	public Agency(int agencyId, String agencyName, String jurisdiction, String contactInformation,
			List<Officer> officers) {
		super();
		this.agencyId = agencyId;
		this.agencyName = agencyName;
		this.jurisdiction = jurisdiction;
		this.contactInformation = contactInformation;
		this.officers = officers;
	}

	public List<Officer> getOfficers() {
		return officers;
	}

	public void setOfficers(List<Officer> officers) {
		this.officers = officers;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Override
	public String toString() {
		return "Agency [agencyId=" + agencyId + ", agencyName=" + agencyName + ", jurisdiction=" + jurisdiction
				+ ", contactInformation=" + contactInformation + "]";
	}


	
}
