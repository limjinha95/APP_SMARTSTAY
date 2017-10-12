/**
 * 
 */
package smartstay.model.dto;

/**
 * ���ھ��� ���� ������
 * @author limjinha
 * @version ver.1.0
 */
public class Office {
	
	/** ���ھ��� ��ȣ */
	private int officeNo;
	/** ���ھ��� �̸� */
	private String officeName;
	/** ���ھ��� �ּ� */
	private String officeAddress;
	/** ���ھ��� ��ȭ��ȣ */
	private String officeCall;
	/** ���ھ��� �Ұ��� */
	private String officeInform;
	
	
	/** �⺻ ������ */
	public Office() {
		super();
	}

	/** ��ü ������ ������ */
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
