package smartstay.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import smartstay.model.dto.Room;

public class RoomDao {
	private FactoryDao factory = FactoryDao.getInstance();

	public Connection getConnection() {
		return factory.getConnection();
	}
	
	
	public int insert(Room dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into office_tb values(?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getOfficeNo());
			stmt.setInt(2, dto.getRoomNo());
			stmt.setString(3, dto.getRoomName());
			stmt.setString(4, dto.getRoomType());
			stmt.setInt(5, dto.getStandardNum());
			stmt.setInt(6, dto.getMaximumNum());
			stmt.setInt(7, dto.getCost());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(객 정보 등록 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	
	public ArrayList<Room> selectList(int officeNo) {
		ArrayList<Room> list = new ArrayList<Room>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from room_tb where office_no = ?";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, officeNo);
			rs = stmt.executeQuery();

			int roomNo = 0;
			String roomName = null;
			String roomType = null;
			int standardNum = 0;
			int maximumNum = 0;
			int cost = 0;

			Room dto = null;

			while(rs.next()) {
				officeNo = rs.getInt("office_no");
				roomNo = rs.getInt("room_no");
				roomName= rs.getString("room_name");
				roomType = rs.getString("room_type");
				standardNum = rs.getInt("standard_num");
				maximumNum = rs.getInt("maximum_num");
				cost = rs.getInt("cost");

				dto = new Room(officeNo, roomNo, roomName, roomType, standardNum, maximumNum, cost);
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(객실 리스트 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	public Room selectOne(int officeNo, int roomNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append("from room_tb ");
		sql.append("where office_no = ? and room_no = ?");
		
		Room dto = null;
		String roomName = null;
		String roomType = null;
		int standardNum = 0;
		int maximumNum = 0;
		int cost = 0;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, officeNo);	
			stmt.setInt(2, roomNo);
			rs = stmt.executeQuery();

			while(rs.next()) {
				officeNo = rs.getInt(1);
				roomNo = rs.getInt(2);
				roomName = rs.getString(3);
				roomType = rs.getString(4);
				standardNum = rs.getInt(5);
				maximumNum = rs.getInt(6);
				cost = rs.getInt(7);
				

				dto = new Room(officeNo, roomNo, roomName, roomType, standardNum, maximumNum, cost);
				return dto;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(객실 정보 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	
	public int updateRoom(int officeNo, int roomNo, String roomName, String roomType, int standardNum, int maximumNum, int cost) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update room_tb set ");
		sql.append("room_name = ?, room_type = ?, standard_num = ?, maximun_num = ?, cost = ? ");
		sql.append("where office_no = ? and room_no = ?");
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, roomName);
			stmt.setString(2, roomType);
			stmt.setInt(3, standardNum);
			stmt.setInt(4, maximumNum);
			stmt.setInt(5, cost);
			stmt.setInt(6, officeNo);
			stmt.setInt(7, roomNo);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(객실 정보 수정 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	
	public int delete(int officeNo, int roomNo) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "delete from room_tb where office_no = ? and room_no = ? ";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, officeNo);
			stmt.setInt(2, roomNo);

			return stmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR(객실 정보 삭제 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
}