package smartstay.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import smartstay.model.dto.Office;

public class OfficeDao {
	private FactoryDao factory = FactoryDao.getInstance();

	public Connection getConnection() {
		return factory.getConnection();
	}
	
	public int insert(Office dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into office_tb values(?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getOwnerNo());
			stmt.setInt(2, dto.getOfficeNo());
			stmt.setString(3, dto.getOfficeName());
			stmt.setString(4, dto.getOfficeAddress());
			stmt.setString(5, dto.getOfficeCall());
			stmt.setString(6, dto.getOfficeInform());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(숙박업소 정보 등록 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	
	public Office selectOne(int ownerNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from office_tb where owner_no = ?";
		
		Office dto = null;
		int officeNo = 0;
		String officeName = null;
		String officeAddress = null;
		String officeCall = null;
		String officeInform = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, ownerNo);			
			rs = stmt.executeQuery();

			while(rs.next()) {
				ownerNo = rs.getInt(1);
				officeNo = rs.getInt(2);
				officeName = rs.getString(3);
				officeAddress = rs.getString(4);
				officeCall = rs.getString(5);
				officeInform = rs.getString(6);

				dto = new Office(ownerNo, officeNo, officeName, officeAddress, officeCall, officeInform);
				return dto;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(숙박업소 정보 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	
	
	public int updateOffice(int ownerNo, String officeName, String officeAddress, String officeCall, String officeInform) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update office_tb set ");
		sql.append("office_name = ?, office_address = ?, office_call = ?, office_inform = ? ");
		sql.append("where owner_no = ?");
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, officeName);
			stmt.setString(2, officeAddress);
			stmt.setString(3, officeCall);
			stmt.setString(4, officeInform);
			stmt.setInt(5, ownerNo);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(숙박업소 정보 수정 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	
	public int delete(int ownerNo) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "delete from office_tb where owner_no = ? ";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ownerNo);

			return stmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR(숙박업소 정보 삭제 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}

}
