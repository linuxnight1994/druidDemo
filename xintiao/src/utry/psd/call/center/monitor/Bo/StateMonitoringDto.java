package utry.psd.call.center.monitor.Bo;

import java.util.Date;

public class StateMonitoringDto {
	private String staffNo;
	private Date dateTime;
	private String centerID;
	private String skillLineID;
	private String groupIdOne;
	private String groupIdTwo;
	private int actStatus;
	private int totalDuration;
	private int direction;
	private int statusNum;

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getCenterID() {
		return centerID;
	}

	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}

	public String getSkillLineID() {
		return skillLineID;
	}

	public void setSkillLineID(String skillLineID) {
		this.skillLineID = skillLineID;
	}

	public String getGroupIdOne() {
		return groupIdOne;
	}

	public void setGroupIdOne(String groupIdOne) {
		this.groupIdOne = groupIdOne;
	}

	public String getGroupIdTwo() {
		return groupIdTwo;
	}

	public void setGroupIdTwo(String groupIdTwo) {
		this.groupIdTwo = groupIdTwo;
	}

	public int getActStatus() {
		return actStatus;
	}

	public void setActStatus(int actStatus) {
		this.actStatus = actStatus;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getStatusNum() {
		return statusNum;
	}

	public void setStatusNum(int statusNum) {
		this.statusNum = statusNum;
	}

}
