/**
 * 
 */
package smartstay.model.dto;

/**
 * 회원 정보 데이터
 * 일반 회원 : 회원번호 1000 ~
 * 숙박업주 회원 : 회원번호 0000 ~
 * @author limjinha
 * @version ver.1.0
 */
public class User {

	/** 회원 번호 */
	private int userNo;
	/** 회원 아이디 */
	private String userId;
	/** 회원 이름 */
	private String userName;
	/** 회원 비밀번호 */
	private String userPw;
	/** 회원 전화번호 */
	private String userMobile;
	/** 회원 토큰 */
	private String userToken;
	/** 회원 사업자등록 번호 (숙박업주) */
	private int userCorporateNumber;
	/** 회원 주소 (숙박업주) */
	private String userAddress;
	
	
	/** 기본 생성자 */
	public User() {
		super();
	}

	/** 회원 필수 데이터 생성자 */
	public User(int userNo, String userId, String userName, String userPw, String userMobile) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.userMobile = userMobile;
	}

	/** 숙박업주 필수 데이터 생성자 */
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

	/** 전체 데이터 생성자 */
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
