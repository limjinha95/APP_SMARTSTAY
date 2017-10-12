/**
 * 
 */
package smartstay.model.dto;

/**
 * ����� ���� ������
 * @author limjinha
 * @version ver.1.0
 */
public class Doorlock {
	
	/** ���ھ��� ��ȣ */
	private int officeNo;
	/** ���� ��ȣ */
	private int roomNo;
	/** ip */
	private String ip;
	
	
	/** �⺻ ������ */
	public Doorlock() {
		super();
	}
	
	/** ��ü ������ */
	public Doorlock(int officeNo, int roomNo, String ip) {
		super();
		this.officeNo = officeNo;
		this.roomNo = roomNo;
		this.ip = ip;
	}

	
	public int getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(int officeNo) {
		this.officeNo = officeNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
