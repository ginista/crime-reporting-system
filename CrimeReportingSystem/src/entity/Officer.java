package entity;

public class Officer {

	private int officerId;
	private String firstName;
	private String lastName;
	private String badgeNumber;
	private String rank;
	private String contactInformation;
	private Agency agency;

	public Officer(int officerId, String firstName, String lastName, String badgeNumber, String rank,
			String contactInformation, Agency agency) {
		super();
		this.officerId = officerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.badgeNumber = badgeNumber;
		this.rank = rank;
		this.contactInformation = contactInformation;
		this.agency = agency;
	}

	public int getOfficerId() {
		return officerId;
	}

	public void setOfficerId(int officerId) {
		this.officerId = officerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	@Override
	public String toString() {
		return "Officer [officerId=" + officerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", badgeNumber=" + badgeNumber + ", rank=" + rank + ", contactInformation=" + contactInformation
				+ "\n agency=" + agency + "]";
	}

	

}
