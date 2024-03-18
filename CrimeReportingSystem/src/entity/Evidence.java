package entity;

public class Evidence {

	private int evidentId;
	private String description;
	private String locationFound;
	
	
	public Evidence( String description, String locationFound) {
		super();
		this.description = description;
		this.locationFound = locationFound;
	}
	
	public int getEvidentId() {
		return evidentId;
	}
	public void setEvidentId(int evidentId) {
		this.evidentId = evidentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocationFound() {
		return locationFound;
	}
	public void setLocationFound(String locationFound) {
		this.locationFound = locationFound;
	}
	
	@Override
	public String toString() {
		return "Evidence [evidentId=" + evidentId + ", description=" + description + ", locationFound=" + locationFound
				+ "]";
	}
	
	
}
