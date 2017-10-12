/**
 * 
 */
package smartstay.model.dto;

/**
 * ���� ���� ������
 * @author limjinha
 * @version ver.1.0
 */
public class Room {
	
	/** ���ھ��� ��ȣ */
	private int officeNo;
	/** ���� ��ȣ */
	private int roomNo;
	/** ���� �̸� */
	private String roomName;
	/** ���� ���� */
	private String roomType;
	/** ���� ���� �ο� */
	private int standardNum;
	/** ���� �ִ� �ο� */
	private int maximumNum;
	/** ���� 1�� ���� ��� */
	private int cost;
	
	
	/** �⺻ ������ */
	public Room() {
		super();
	}
	
	/** ���� �ʼ� ������ ������ */
	public Room(int officeNo, int roomNo, int standardNum, int maximumNum, int cost) {
		super();
		this.officeNo = officeNo;
		this.roomNo = roomNo;
		this.standardNum = standardNum;
		this.maximumNum = maximumNum;
		this.cost = cost;
	}

	/** ��ü ������ ������ */
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
