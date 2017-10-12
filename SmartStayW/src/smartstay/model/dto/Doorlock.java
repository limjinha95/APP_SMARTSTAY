/**
 * 
 */
package smartstay.model.dto;

/**
 * 도어락 정보 데이터
 * @author limjinha
 * @version ver.1.0
 */
public class Doorlock {
	
	/** 숙박업소 번호 */
	private int officeNo;
	/** 객실 번호 */
	private int roomNo;
	/** ip */
	private String ip;
	
	
	/** 기본 생성자 */
	public Doorlock() {
		super();
	}
	
	/** 전체 생성자 */
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
