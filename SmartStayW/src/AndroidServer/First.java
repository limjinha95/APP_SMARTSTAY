package AndroidServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import smartstay.model.dao.UserDao;
import smartstay.model.dto.User;

@WebServlet(name = "first", urlPatterns = { "/first" })
public class First extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public First() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		System.out.println(data);
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(data);
			JSONObject jsonObj = (JSONObject) obj;
			String head = (String) jsonObj.get("head");
			JdbcConnect jc = new JdbcConnect("smartstay");
			if (head.equals("Login")) {
				String Id = (String) jsonObj.get("ID");
				String Pwd = (String) jsonObj.get("PWD");
				String Token = (String) jsonObj.get("Token");
				String loginData = jc.Login(Id, Pwd, Token);
				System.out.println(loginData);
				response.getWriter().append(loginData);
			} else if (head.equals("SelectUser")) {
				String Id = (String) jsonObj.get("ID");
				String loginData = jc.SelectUser(Id);
				System.out.println(loginData);
				response.getWriter().append(loginData);
			} else if (head.equals("ID")) {
				String Id = (String) jsonObj.get("ID");
				String IdData;
				JSONObject jo = new JSONObject();
				if (jc.IsUniqueID(Id))
					jo.put("Unique", "Y");
				else
					jo.put("Unique", "N");
				IdData = jo.toString();
				System.out.println(IdData);
				response.getWriter().append(IdData);
			} else if (head.equals("Register")) {
				String Id = (String) jsonObj.get("ID");
				String Pwd = (String) jsonObj.get("PWD");
				String Name = (String) jsonObj.get("Name");
				String Pnum = (String) jsonObj.get("Pnum");
				String Token = (String) jsonObj.get("Token");
				boolean check = false;
				do
					check = jc.RegisterUser(Id, Name, Pwd, Pnum, Token);
				while (check == false);
				System.out.println("ok");
			} else if (head.equals("Delete")) {
				String Id = (String) jsonObj.get("ID");
				boolean check = false;
				do
					check = jc.DeleteUser(Id);
				while (check == false);
			} else if (head.equals("ChangePwd")) {
				String Id = (String) jsonObj.get("ID");
				String Pwd = (String) jsonObj.get("PWD");
				boolean check = false;
				do
					check = jc.ChangePwd(Id, Pwd);
				while (check == false);
				System.out.println("ok");
			} else if (head.equals("SearchOfficePnum")) {
				String OfficeCode = (String) jsonObj.get("OfficeCode");
				String PnumData = jc.OfficePnum(OfficeCode);
				response.getWriter().append(PnumData);
			} /*else if (head.equals("Search_Room_Ip")) {
				UserThread.
			}*/
			else if (head.equals("FriendPlus")) {
				String RoomNumber = (String) jsonObj.get("RoomNumber");
				String FriendId = (String) jsonObj.get("FriendId");
				boolean check = false;
				do
					check = jc.FriendPlus(FriendId, RoomNumber);
				while (check == false);
			} else if (head.equals("RoomCheck")) {
				String OfficeCode = (String) jsonObj.get("OfficeCode");
				String RoomNumber = (String) jsonObj.get("RoomNumber");
				response.getWriter().append(jc.RoomCheck(OfficeCode, RoomNumber));
			} else if (head.equals("RoomList")) {
				response.getWriter().append(jc.RoomList());
			} else if (head.equals("ReservationCancle")) {
				String OfficeCode = (String) jsonObj.get("OfficeCode");
				String RoomNumber = (String) jsonObj.get("RoomNumber");
				String Ridx = jc.CheckRidx(OfficeCode, RoomNumber);
				boolean check = false;
				do
					check = jc.ReservationCancel(Ridx);
				while (check == false);
			} else if (head.equals("Reservation")) {
				String RoomNumber = (String) jsonObj.get("room_no");
				String ID = (String) jsonObj.get("ID");
				String StartDate = (String) jsonObj.get("StartDate");
				String EndDate = (String) jsonObj.get("EndDate");
				String officeCode = (String) jsonObj.get("office_no");
				System.out.println("ID "+ID+" Rno "+RoomNumber+" Ono "+officeCode+" S "+StartDate+" D "+EndDate);
				boolean check = false;
				do
					check = jc.RegisterReservation(officeCode, RoomNumber, ID, StartDate, EndDate);
				while (check == false);
			} else if (head.equals("ReservationCheck")) {
				String ID = (String) jsonObj.get("ID");
				response.getWriter().append(jc.ReservationCheck(ID));
			} else if (head.equals("MyCoupon")) {
				String ID = (String) jsonObj.get("ID");
				response.getWriter().append(jc.MyCoupon(ID));
			} else if (head.equals("MyKey")) {
				String ID = (String) jsonObj.get("ID");
				response.getWriter().append(jc.MyKey(ID));
			} else if(head.equals("Search_Room_Ip")) {
            	String id = (String) jsonObj.get("ID");
            	UserDao ud = new UserDao();
            	int userNo = ud.selectOne(id);
            	User user = ud.selectOne(userNo);
            	
				MessageService ms = new MessageService();
				ms.push(user.getUserToken());
            }
			
			jc.closeDB();
		} catch (Exception e) {

		}

	}

}
