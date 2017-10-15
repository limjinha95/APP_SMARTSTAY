package smartstay.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import smartstay.model.dto.User;

public class UserDao {
	private FactoryDao factory = FactoryDao.getInstance();

	public Connection getConnection() {
		return factory.getConnection();
	}

	
	public int insertUser(User dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into user_tb values(?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getUserNo());
			stmt.setString(2, dto.getUserId());
			stmt.setString(3, dto.getUserName());
			stmt.setString(4, dto.getUserPw());
			stmt.setString(5, dto.getUserMobile());
			stmt.setString(6, dto.getUserToken());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(일반회원 등록 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	public int insertOwner(User dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into members_tb values(?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getUserNo());
			stmt.setString(2, dto.getUserId());
			stmt.setString(3, dto.getUserName());
			stmt.setString(4, dto.getUserPw());
			stmt.setString(5, dto.getUserMobile());
			stmt.setInt(6, dto.getUserCorporateNumber());
			stmt.setString(7, dto.getUserAddress());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(숙박업주 회원 등록 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	
	public User selectOne(int userNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append("from user_tb ");
		sql.append("where user_no = ?");
		
		User dto = null;
		String userId = null;
		String userName = null;
		String userPw = null;
		String userMobile = null;
		String userToken = null;
		int userCorporateNumber = 0;
		String userAddress = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, userNo);			
			rs = stmt.executeQuery();

			while(rs.next()) {
				userNo = rs.getInt(1);
				userId = rs.getString(2);
				userName = rs.getString(3);
				userPw = rs.getString(4);
				userMobile = rs.getString(5);
				userToken = rs.getString(6);
				userCorporateNumber = rs.getInt(7);
				userAddress = rs.getString(8);

				dto = new User(userNo, userId, userName, userPw, userMobile, userToken, userCorporateNumber, userAddress);
				return dto;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(회원 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	
	
	public int update(User dto) {
		return 0;
	}
	
	public int delete(int userNo) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "delete user_tb where user_no = ? ";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userNo);

			return stmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR(회원탈퇴 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}

	public int selectOne(String userId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select user_no from user_tb where user_id=?";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("user_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(아이디 확인 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	public int selectOne(String userId, String userPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select user_no from user_tb where user_id=? and user_pw=?";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, userPw);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("user_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(로그인 정보 확인 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
}