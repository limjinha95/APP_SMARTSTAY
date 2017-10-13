/**
 * 
 */
package smartstay.model.dto;

/**
 * 객실 정보 데이터
 * @author limjinha
 * @version ver.1.0
 */
public class Room {
	
	/** 숙박업소 번호 */
	private int officeNo;
	/** 객실 번호 */
	private int roomNo;
	/** 객실 이름 */
	private String roomName;
	/** 객실 종류 */
	private String roomType;
	/** 객실 기준 인원 */
	private int standardNum;
	/** 객실 최대 인원 */
	private int maximumNum;
	/** 객실 1일 숙박 비용 */
	private int cost;
	
	
	/** 기본 생성자 */
	public Room() {
		super();
	}
	
	/** 객실 필수 데이터 생성자 */
	public Room(int officeNo, int roomNo, int standardNum, int maximumNum, int cost) {
		super();
		this.officeNo = officeNo;
		this.roomNo = roomNo;
		this.standardNum = standardNum;
		this.maximumNum = maximumNum;
		this.cost = cost;
	}

	/** 전체 데이터 생성자 */
	public Room(int officeNo, int roomNo, String roomName, String roomType, int standardNum, int maximumNum, int cost) {
		super();
		this.officeNo = officeNo;
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.roomType = roomType;
		this.standardNum = standardNum;
		this.maximumNum = maximumNum;
		this.cost = cost;
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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getStandardNum() {
		return standardNum;
	}

	public void setStandardNum(int standardNum) {
		this.standardNum = standardNum;
	}

	public int getMaximumNum() {
		return maximumNum;
	}

	public void setMaximumNum(int maximumNum) {
		this.maximumNum = maximumNum;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
