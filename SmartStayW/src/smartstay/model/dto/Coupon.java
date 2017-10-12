/**
 * 
 */
package smartstay.model.dto;

/**
 * @author limjinha
 * @version ver.1.0
 */
public class Coupon {
	
	/** ���� ��ȣ */
	private int couponNo;
	/** ���� �̸� */
	private String couponName;
	/** ���� ��� �Ⱓ ������ */
	private String startDate;
	/** ���� ��� �Ⱓ �������� */
	private String endDate;
	/** ���� ��� ���� */
	private String couponInfo;
	/** ���� ���� ���� */
	private int cost;
	
	
	/** �⺻ ������ */
	public Coupon() {
		super();
	}
	
	/** ��ü ������ ������ */
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
