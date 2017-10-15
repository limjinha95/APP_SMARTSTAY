package smartstay.model.service;

import smartstay.model.dao.UserDao;
import smartstay.model.dto.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public boolean isExist(String userId) {
		if (userDao.selectOne(userId) == 0)
			return false;
		return true;
	}
	
	public int checkLoginInfo(String userId, String userPw) {
		if (userDao.selectOne(userId, userPw) != 0)
			return userDao.selectOne(userId, userPw);
		return 0;
	}
	
	public int addOwner(User userDto) {
		return userDao.insertOwner(userDto);
	}
	
	public int addUser(User userDto) {
		return userDao.insertUser(userDto);
	}
	
	public String getUserName(int userNo) {
		return userDao.selectOne(userNo).getUserName();
	}
	
	public User getUser(int userNo) {
		return userDao.selectOne(userNo);
	}
	
	public int updateMember(User userDto) {
		return userDao.update(userDto);
	}
	
	public int deleteMember(int userNo) {
		return userDao.delete(userNo);
	}

}
