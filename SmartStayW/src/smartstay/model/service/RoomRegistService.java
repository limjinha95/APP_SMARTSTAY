package smartstay.model.service;

import smartstay.model.dao.RoomDao;
import smartstay.model.dto.Room;

public class RoomRegistService {
	public boolean registRoom(Room room) {
		int result;
		RoomDao roomDao = new RoomDao();
		result = roomDao.insert(room); 
		
		if(result ==0) {
			System.out.println("Error Update Office");
			return false;
		}else {
			System.out.println("Success Update Office");
			return true;
		}
	}
}
