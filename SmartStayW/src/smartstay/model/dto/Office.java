/**
 * 
 */
package smartstay.model.dto;

/**
 * 숙박업소 정보 데이터
 * @author limjinha
 * @version ver.1.0
 */
public class Office {
	
	/** 숙박업소 번호 */
	private int officeNo;
	/** 숙박업소 이름 */
	private String officeName;
	/** 숙박업소 주소 */
	private String officeAddress;
	/** 숙박업소 전화번호 */
	private String officeCall;
	/** 숙박업소 소개글 */
	private String officeInform;
	
	
	/** 기본 생성자 */
	public Office() {
		super();
	}

	/** 전체 데이터 생성자 */
	public Office(int officeNo, String officeName, String officeAddress, String officeCall, String officeInform) {
		super();
		this.officeNo = officeNo;
		this.officeName = officeName;
		this.officeAddress = officeAddress;
		this.officeCall = officeCall;
		this.officeInform = officeInform;
	}

	
	public int getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(int officeNo) {
		this.officeNo = officeNo;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getOfficeCall() {
		return officeCall;
	}

	public void setOfficeCall(String officeCall) {
		this.officeCall = officeCall;
	}

	public String getOfficeInform() {
		return officeInform;
	}

	public void setOfficeInform(String officeInform) {
		this.officeInform = officeInform;
	}

}
