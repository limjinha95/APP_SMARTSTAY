/**
 * 
 */
package smartstay.model.dto;

/**
 * ȸ�� ���� ���� ������
 * @author limjinha
 * @version ver.1.0
 */
public class UserCoupon {
	
	/** ȸ�� ���̵� */
	private String userId;
	/** ���� ��ȣ */
	private int couponNo;
	
	
	/** �⺻ ������ */
	public UserCoupon() {
		super();
	}

	/** ��ü ������ */
	public UserCoupon(String userId, int couponNo) {
		super();
		this.userId = userId;
		this.couponNo = couponNo;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	
}
