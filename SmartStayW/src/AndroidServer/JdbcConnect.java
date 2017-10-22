package AndroidServer;

import java.sql.*;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JdbcConnect {
	private Connection con = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public JdbcConnect(String dbName) throws Exception {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Driver Install Complete");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Error : " + cnfe);
		}

		String url = "jdbc:mysql://localhost:3306/smartstay";
		String id = "root";
		String pw = "1q2w3e4r";
		try {
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("Connection Complete");
		} catch (SQLException e) {
			System.err.println("Error : " + e);
		}
	}

	public boolean closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String Login(String id, String pwd, String token) throws SQLException {
		String query = "SELECT user_id, user_name, user_mobile FROM user_tb where user_id=? AND user_pw=PASSWORD(?)";
		psmt = con.prepareStatement(query);
		psmt.setString(1, id);
		psmt.setString(2, pwd);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		JSONObject jo = new JSONObject();
		String loginData;
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		for (int i = 0; i < numberColumn; i++)
			jo.put(columName[i], rs.getString(columName[i]));
		
		String query2 = "update user_tb set user_token=? where user_id = ?";
		psmt = con.prepareStatement(query2);
		psmt.setString(1, token);
		psmt.setString(2, id);
		int check;
		do {
		check = psmt.executeUpdate();
		}
		while(check<=0);
		loginData = jo.toString();
		return loginData;
	}
	
	public String SelectUser(String id) throws SQLException {
		String query = "SELECT user_id, user_name, user_mobile FROM user_tb where user_id=?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, id);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		JSONObject jo = new JSONObject();
		String loginData;
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		for (int i = 0; i < numberColumn; i++)
			jo.put(columName[i], rs.getString(columName[i]));
		loginData = jo.toString();
		return loginData;
	}

	public String OfficePnum(String OfficeCode) throws SQLException {
		String query = "SELECT office_call FROM office_tb where office_no= ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, OfficeCode);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		JSONObject jo = new JSONObject();
		String PnumData;
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		for (int i = 0; i < numberColumn; i++)
			jo.put(columName[i], rs.getString(columName[i]));
		PnumData = jo.toString();
		return PnumData;
	}

	public boolean IsUniqueID(String id) throws SQLException {
		String query = "SELECT COUNT(*) FROM user_tb where user_id = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, id);
		rs = psmt.executeQuery();
		int rowcount = 0;
		if (rs.next()) {
			rowcount = rs.getInt(1);
		}
		if (rowcount == 0)
			return true;
		else
			return false;
	}

	public boolean RegisterUser(String id, String name, String pwd, String Pnum, String token) {
		try {
			String query = "insert into user_tb (user_id, user_name, user_pw, user_mobile, user_token) values(?, ?, PASSWORD(?), ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, name);
			psmt.setString(3, pwd);
			psmt.setString(4, Pnum);
			psmt.setString(5, token);
			int check = psmt.executeUpdate();
			if (check > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean DeleteUser(String id) throws SQLException {
		String query = "delete from user_tb where user_id = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, id);
		int check = psmt.executeUpdate();
		if (check > 0)
			return true;
		else
			return false;
	}

	public boolean ChangePwd(String id, String Pwd) throws SQLException {
		String query = "update user_tb set user_pw = PASSWORD(?) where user_id = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(2, id);
		psmt.setString(1, Pwd);
		int check = psmt.executeUpdate();
		if (check > 0)
			return true;
		else
			return false;
	}

	public boolean FriendPlus(String id, String Rnum) throws SQLException {
		String query = "insert into user_key_tb values(?,?)";
		psmt = con.prepareStatement(query);
		psmt.setString(1, Rnum);
		psmt.setString(2, id);
		int check = psmt.executeUpdate();
		if (check > 0)
			return true;
		else
			return false;
	}

	public String Search_ip(String OFFICECODE, String RNUM) throws SQLException {
		String query = "SELECT ip FROM doorlock_tb where office_no = ? AND room_no = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, OFFICECODE);
		psmt.setString(2, RNUM);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		String Ip = rs.getString(columName[0]);
		return Ip;
	}

	public String ReservationCheck(String ID) throws SQLException {
		String query = "select reservation_tb.office_no, reservation_tb.room_no, office_tb.office_name,room_tb.room_name,reservation_tb.start_date, reservation_tb.end_date, room_tb.standard_num, room_tb.maximum_num from (reservation_tb join room_tb on reservation_tb.room_no = room_tb.room_no and reservation_tb.office_no = room_tb.office_no) join office_tb on reservation_tb.office_no = office_tb.office_no where user_id = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, ID);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		JSONArray ja = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		String RSCData;
		do {
			JSONObject jo = new JSONObject();
			for (int i = 0; i < numberColumn; i++)
				jo.put(columName[i], rs.getString(columName[i]));
			ja.add(jo);
		} while (rs.next());
		RSCData = ja.toString();
		return RSCData;
	}

	public String MyCoupon(String ID) throws SQLException {
		String query = "select coupon_tb.coupon_name, coupon_tb.start_date, coupon_tb.end_date, coupon_tb.coupon_info, coupon_tb.cost from user_coupon_tb join coupon_tb on user_coupon_tb.coupon_no = coupon_tb.coupon_no where user_id = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, ID);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		JSONArray ja = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		String CData;
		do {
			JSONObject jo = new JSONObject();
			for (int i = 0; i < numberColumn; i++)
				jo.put(columName[i], rs.getString(columName[i]));
			ja.add(jo);
		} while (rs.next());
		CData = ja.toString();
		return CData;
	}
	
	public String MyKey(String ID) throws SQLException {
		String query = "select reservation_tb.office_no, reservation_tb.room_no, office_tb.office_name,reservation_tb.start_date, reservation_tb.end_date, room_tb.standard_num, room_tb.maximum_num from (reservation_tb join room_tb on reservation_tb.room_no = room_tb.room_no and reservation_tb.office_no = room_tb.office_no) join office_tb on reservation_tb.office_no = office_tb.office_no where user_id = ? and reservation_tb.start_date <= CURDATE() and reservation_tb.end_date >= CURDATE()";
		psmt = con.prepareStatement(query);
		psmt.setString(1, ID);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		JSONArray ja = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		String CData;
		do {
			JSONObject jo = new JSONObject();
			for (int i = 0; i < numberColumn; i++)
				jo.put(columName[i], rs.getString(columName[i]));
			ja.add(jo);
		} while (rs.next());
		CData = ja.toString();
		return CData;
	}

	public String RoomCheck(String Oname, String Rnum) throws SQLException {
		String query = "select office_tb.office_name, reservation_tb.room_no, reservation_tb.start_date, reservation_tb.end_date from reservation_tb join office_tb on reservation_tb.office_no = office_tb.office_no where office_tb.office_name= ? and reservation_tb.room_no = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, Oname);
		psmt.setString(2, Rnum);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		JSONArray ja = new JSONArray();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		String ROCData;
		do {
			JSONObject jo = new JSONObject();
			for (int i = 0; i < numberColumn; i++)
				jo.put(columName[i], rs.getString(columName[i]));
			ja.add(jo);
		} while (rs.next());
		ROCData = ja.toString();
		return ROCData;
	}

	public String RoomList() throws SQLException {
		String query = "select office_tb.office_name, office_tb.office_no,room_tb.room_name,room_tb.room_no, office_tb.office_address, room_tb.room_type, room_tb.cost from room_tb join office_tb on room_tb.office_no = office_tb.office_no";
		psmt = con.prepareStatement(query);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		JSONArray ja = new JSONArray();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		String ARCData;
		do {
			JSONObject jo = new JSONObject();
			for (int i = 0; i < numberColumn; i++)
				jo.put(columName[i], rs.getString(columName[i]));
			ja.add(jo);
		} while (rs.next());
		ARCData = ja.toString();
		return ARCData;
	}

	public String CheckRidx(String officename, String rnum) throws SQLException {
		String query = "select reservation_no from reservation_tb where room_no= ? and office_no = (select office_no from office_tb where office_name= ? )";
		psmt = con.prepareStatement(query);
		psmt.setString(1, rnum);
		psmt.setString(2, officename);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		JSONObject jo = new JSONObject();
		jo.put(columName[0], rs.getString(columName[0]));
		String data = jo.toString();
		return data;
	}

	public String GetToken(String officename, String rnum) throws SQLException {
		String query = "select user_tb.user_token from (reservation_tb join user_key_tb on reservation_tb.reservation_no=user_key_tb.reservation_no) join user_tb on user_key_tb.user_id = user_tb.user_id where reservation_tb.start_date<=curdate() and reservation_tb.end_date>=curdate() and reservation_tb.office_no = ? and reservation_tb.room_no= ? ;";
		psmt = con.prepareStatement(query);
		psmt.setString(2, rnum);
		psmt.setString(1, officename);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		String data = "";
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		JSONArray ja = new JSONArray();
		do {
			data += rs.getString(columName[0]);
			data += "|";
		} while (rs.next());
		return data;
	}

	public String CheckOfficeCode(String officename) throws SQLException {
		String query = "select office_no from office_tb where office_name = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, officename);
		rs = psmt.executeQuery();
		if (rs.next() == false) {
			return "-";
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int numberColumn = rsmd.getColumnCount();
		String[] columName = new String[numberColumn];
		for (int i = 0; i < numberColumn; i++)
			columName[i] = rsmd.getColumnName(i + 1);
		JSONObject jo = new JSONObject();
		jo.put(columName[0], rs.getString(columName[0]));
		String data = jo.toString();
		return data;
	}

	public boolean ReservationCancel(String Ridx) throws SQLException {
		String query = "delete from reservation_tb where reservation_no = ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1, Ridx);
		int check = psmt.executeUpdate();
		if (check == 0)
			return false;
		else
			return true;
	}

	public boolean RegisterReservation(String officecode, String rnum, String id, String start, String end)
			throws SQLException {
		String query = "insert into reservation_tb (user_id, start_date, end_date, office_no, room_no) values( ? , ? , ? , ? , ? )";
		psmt = con.prepareStatement(query);
		psmt.setString(1, id);
		SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startD,endD;
		startD = endD = null;
		try {
			startD = (java.util.Date)trans.parse(start);
			endD = (java.util.Date)trans.parse(end);
		}catch(Exception e) {
			
		}
		psmt.setDate(2, new java.sql.Date(startD.getTime()));
		psmt.setDate(3, new java.sql.Date(endD.getTime()));
		psmt.setInt(4, Integer.parseInt(officecode));
		psmt.setInt(5, Integer.parseInt(rnum));
		int check = psmt.executeUpdate();
		if (check > 0) {
			System.out.println("reserve");
			return true;
		}
		else
			return false;
	}
}