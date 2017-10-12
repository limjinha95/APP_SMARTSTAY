/**
 * 
 */
package smartstay.model.dto;

/**
 * 숙박업소 예약 정보 데이터
 * @author limjinha
 * @version ver.1.0
 */
public class Reservation {
	
	/** 예약 번호 */
	private int reservationNo;
	/** 예약 회원 아이디 */
	private String userId;
	/** 예약 시작일 */
	private String startDate;
	/** 예약 마지막일 */
	private String endDate;
	/** 숙박업소 번호 */
	private int officeNo;
	/** 객실 번호 */
	private int roomNo;
	
	
	/** 기본 생성자 */
	public Reservation() {
		super();
	}
	
	/** 전체 데이터 생성자 */
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
