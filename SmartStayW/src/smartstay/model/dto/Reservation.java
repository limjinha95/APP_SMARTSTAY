/**
 * 
 */
package smartstay.model.dto;

/**
 * ���ھ��� ���� ���� ������
 * @author limjinha
 * @version ver.1.0
 */
public class Reservation {
	
	/** ���� ��ȣ */
	private int reservationNo;
	/** ���� ȸ�� ���̵� */
	private String userId;
	/** ���� ������ */
	private String startDate;
	/** ���� �������� */
	private String endDate;
	/** ���ھ��� ��ȣ */
	private int officeNo;
	/** ���� ��ȣ */
	private int roomNo;
	
	
	/** �⺻ ������ */
	public Reservation() {
		super();
	}
	
	/** ��ü ������ ������ */
	public Reservation(int reservationNo, String userId, String startDate, String endDate, int officeNo, int roomNo) {
		super();
		this.reservationNo = reservationNo;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.officeNo = officeNo;
		this.roomNo = roomNo;
	}

	
	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

}
