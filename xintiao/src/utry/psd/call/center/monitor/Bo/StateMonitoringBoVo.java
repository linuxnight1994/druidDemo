package utry.psd.call.center.monitor.Bo;

import java.io.Serializable;

public class StateMonitoringBoVo implements Serializable {
	private static final long serialVersionUID = 98429492015032401L;
	private int sid;// 序号
	private String sName;// 姓名
	private String sno;// 工号
	private String classNo;// 班次
	private String groupType;// 组别
	private String branchCenter;// 技能线
	private String extensionNumber;// 分机号
	private String status;// 状态
	private String timeLong;// 时长
	private int isRed;//是否红色显示
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getBranchCenter() {
		return branchCenter;
	}

	public void setBranchCenter(String branchCenter) {
		this.branchCenter = branchCenter;
	}

	public String getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}

	public int getIsRed() {
		return isRed;
	}

	public void setIsRed(int isRed) {
		this.isRed = isRed;
	}

	public String toStringd() {
		return "StateMonitoringBoVo [sid=" + sid + ", sName=" + sName
				+ ", sno=" + sno + ", classNo=" + classNo + ", groupType="
				+ groupType + ", branchCenter=" + branchCenter
				+ ", extensionNumber=" + extensionNumber + ", status=" + status
				+ ", timeLong=" + timeLong + "}";
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("id:" + sid).append(",").append("sName:" + "'"+sName+ "'")
				.append(",").append("sno:" + "'" + sno + "'").append(",")
				.append("classNo:"  + "'"+ classNo + "'").append(",")
				.append("groupType:"  + "'"+ groupType + "'").append(",")
				.append("branchCenter:"  + "'"+ branchCenter + "'").append(",")
				.append("extensionNumber:" + "'" + extensionNumber + "'").append(",")
				.append("status:"  + "'"+ status + "'").append(",")
				.append("timeLong:"  + "'"+ timeLong + "'").append(",")
				.append("isRed:"  + "'"+ isRed + "'");
		return str.toString();
	}

}
