/**
 * 
 */
package smartstay.model.dto;

/**
 * 쿠폰 정보 데이터
 * @author limjinha
 * @version ver.1.0
 */
public class Coupon {
	
	/** 쿠폰 번호 */
	private int couponNo;
	/** 쿠폰 이름 */
	private String couponName;
	/** 쿠폰 사용 기간 시작일 */
	private String startDate;
	/** 쿠폰 사용 기간 마지막일 */
	private String endDate;
	/** 쿠폰 사용 조건 */
	private String couponInfo;
	/** 쿠폰 할인 가격 */
	private int cost;
	
	
	/** 기본 생성자 */
	public Coupon() {
		super();
	}
	
	/** 전체 데이터 생성자 */
	public Coupon(int couponNo, String couponName, String startDate, String endDate, String couponInfo, int cost) {
		super();
		this.couponNo = couponNo;
		this.couponName = couponName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.couponInfo = couponInfo;
		this.cost = cost;
	}

	
	public int getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
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

	public String getCouponInfo() {
		return couponInfo;
	}

	public void setCouponInfo(String couponInfo) {
		this.couponInfo = couponInfo;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
