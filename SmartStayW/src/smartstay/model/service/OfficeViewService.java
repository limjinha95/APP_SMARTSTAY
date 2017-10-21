package smartstay.model.service;

import smartstay.model.dao.OfficeDao;
import smartstay.model.dao.UserDao;
import smartstay.model.dto.Office;

public class OfficeViewService {
	
	public Office getOfficeView(String userId) {
		OfficeDao officeDao = new OfficeDao();
		UserDao userDao = new UserDao();
		int userNo = userDao.selectOne(userId);
		Office userOffice = officeDao.selectOne(userNo);
		
		return userOffice;
	}

}
