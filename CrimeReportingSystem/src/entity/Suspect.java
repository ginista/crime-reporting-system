package entity;

import java.time.LocalDate;

public class Suspect {
	
	private int suspectId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String contactInformation;
	
	
	public Suspect( String firstName, String lastName, LocalDate dateOfBirth, String gender,
			String contactInformation) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactInformation = contactInformation;
	}
	public int getSuspectId() {
		return suspectId;
	}
	public void setSuspectId(int suspectId) {
		this.suspectId = suspectId;
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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	@Override
	public String toString() {
		return "Suspect [suspectId=" + suspectId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", contactInformation=" + contactInformation
				+ "]";
	}
	
	

}
