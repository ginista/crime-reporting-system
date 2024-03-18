package entity;

import java.time.LocalDate;

public class Report {

	private int reportId;
	private LocalDate reportDate;
	private String reportDetails;
	private String reportStatus;
	private Incident incident;
	private Officer reportingOfficer;
	
	public Report(int reportId, LocalDate reportDate, String reportDetails, String reportStatus,Officer reportingOfficer,Incident incident) {
		super();
		this.reportId = reportId;
		this.reportDate = reportDate;
		this.reportDetails = reportDetails;
		this.reportStatus = reportStatus;
		this.reportingOfficer = reportingOfficer;
		this.incident = incident;
		
	}

	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportDetails() {
		return reportDetails;
	}
	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}
	
	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Officer getReportingOfficer() {
		return reportingOfficer;
	}

	public void setReportingOfficer(Officer reportingOfficer) {
		this.reportingOfficer = reportingOfficer;
	}

	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", reportDate=" + reportDate + ", reportDetails=" + reportDetails
				+ ", reportStatus=" + reportStatus + "\n incident=" + incident + " reportingOfficer=" + reportingOfficer
				+ "]";
	}
	
	
	

	
	
	
	
}
