/**
 * 
 */
package smartstay.model.dto;

/**
 * ȸ�� ���� ������
 * �Ϲ� ȸ�� : ȸ����ȣ 1000 ~
 * ���ھ��� ȸ�� : ȸ����ȣ 0000 ~
 * @author limjinha
 * @version ver.1.0
 */
public class User {

	/** ȸ�� ��ȣ */
	private int userNo;
	/** ȸ�� ���̵� */
	private String userId;
	/** ȸ�� �̸� */
	private String userName;
	/** ȸ�� ��й�ȣ */
	private String userPw;
	/** ȸ�� ��ȭ��ȣ */
	private String userMobile;
	/** ȸ�� ��ū */
	private String userToken;
	/** ȸ�� ����ڵ�� ��ȣ (���ھ���) */
	private int userCorporateNumber;
	/** ȸ�� �ּ� (���ھ���) */
	private String userAddress;
	
	
	/** �⺻ ������ */
	public User() {
		super();
	}

	/** ȸ�� �ʼ� ������ ������ */
	public User(int userNo, String userId, String userName, String userPw, String userMobile) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.userMobile = userMobile;
	}

	/** ���ھ��� �ʼ� ������ ������ */
	public User(int userNo, String userId, String userName, String userPw, String userMobile, int userCorporateNumber,
			String userAddress) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.userMobile = userMobile;
		this.userCorporateNumber = userCorporateNumber;
		this.userAddress = userAddress;
	}

	/** ��ü ������ ������ */
	public User(int userNo, String userId, String userName, String userPw, String userMobile, String userToken,
			int userCorporateNumber, String userAddress) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.userMobile = userMobile;
		this.userToken = userToken;
		this.userCorporateNumber = userCorporateNumber;
		this.userAddress = userAddress;
	}

	
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public int getUserCorporateNumber() {
		return userCorporateNumber;
	}

	public void setUserCorporateNumber(int userCorporateNumber) {
		this.userCorporateNumber = userCorporateNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
