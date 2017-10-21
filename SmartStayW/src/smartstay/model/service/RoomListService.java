package smartstay.model.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import smartstay.model.dao.RoomDao;
import smartstay.model.dto.Room;

public class RoomListService {
	
	public ArrayList<Room> getRoomList(int officeNum){
		RoomDao roomDao = new RoomDao();
		return roomDao.selectList(officeNum);
	}

}
