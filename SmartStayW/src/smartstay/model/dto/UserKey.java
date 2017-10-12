/**
 * 
 */
package smartstay.model.dto;

/**
 * ȸ�� ����ƮŰ ���� ������
 * @author limjinha
 * @version ver.1.0
 */
public class UserKey {
	
	/** ���� ȸ�� ���̵� */
	private String userId;
	/** ���� ȸ�� ���� ��ȣ */
	private int reservationNo;
	
	
	/** �⺻ ������ */
	public UserKey() {
		super();
	}

	/** ��ü ������ */
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
