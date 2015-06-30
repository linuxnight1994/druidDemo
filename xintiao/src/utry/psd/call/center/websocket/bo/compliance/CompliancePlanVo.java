package utry.psd.call.center.websocket.bo.compliance;

import java.io.Serializable;

/**
 * 遵时度数据安排数据BO
 * 
 * date 2015-03-26
 * 
 * @author sharkTang
 * 
 */
public class CompliancePlanVo implements Serializable {
	private static final long serialVersionUID = 98429492015032603L;
	private String sno;// 关联用户工号
	private String sName;// 姓名
	private String classNo;// 班次
	private String descOne; // 安排话务
	private String overtimeTimeDescOne;// 安排加班
	private String overEateDescOne;// 安排就餐
	private String excptionInfo;// 异常信息

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(sno).append(",").append(sName).append(",").append(classNo)
				.append(",").append(excptionInfo).append(",").append(descOne)
				.append(",").append(overtimeTimeDescOne).append(",")
				.append(overEateDescOne);
		return str.toString();
	}

	public String getExcptionInfo() {
		return excptionInfo;
	}

	public void setExcptionInfo(String excptionInfo) {
		this.excptionInfo = excptionInfo;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getDescOne() {
		return descOne;
	}

	public void setDescOne(String descOne) {
		this.descOne = descOne;
	}

	public String getOvertimeTimeDescOne() {
		return overtimeTimeDescOne;
	}

	public void setOvertimeTimeDescOne(String overtimeTimeDescOne) {
		this.overtimeTimeDescOne = overtimeTimeDescOne;
	}

	public String getOverEateDescOne() {
		return overEateDescOne;
	}

	public void setOverEateDescOne(String overEateDescOne) {
		this.overEateDescOne = overEateDescOne;
	}

}
