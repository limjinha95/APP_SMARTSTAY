/**
 * 
 */
package smartstay.model.dto;

/**
 * 회원 쿠폰 정보 데이터
 * @author limjinha
 * @version ver.1.0
 */
public class UserCoupon {
	
	/** 회원 아이디 */
	private String userId;
	/** 쿠폰 번호 */
	private int couponNo;
	
	
	/** 기본 생성자 */
	public UserCoupon() {
		super();
	}

	/** 전체 생성자 */
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
