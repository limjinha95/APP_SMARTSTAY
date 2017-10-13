/**
 * 
 */
package smartstay.model.dto;

/**
 * 회원 스마트키 정보 데이터
 * @author limjinha
 * @version ver.1.0
 */
public class UserKey {
	
	/** 예약 회원 아이디 */
	private String userId;
	/** 예약 회원 예약 번호 */
	private int reservationNo;
	
	
	/** 기본 생성자 */
	public UserKey() {
		super();
	}

	/** 전체 생성자 */
	public UserKey(String userId, int reservationNo) {
		super();
		this.userId = userId;
		this.reservationNo = reservationNo;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

}
