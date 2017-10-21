package smartstay.model.service;

import smartstay.model.dao.OfficeDao;
import smartstay.model.dto.Office;

public class OfficeRegistService {
	public boolean registOffice(Office office) {
		int result;
		OfficeDao officeDao = new OfficeDao();
		
		if(officeDao.selectOne(office.getOwnerNo())!=null){
			result = officeDao.updateOffice(office.getOwnerNo(), office.getOfficeName(), office.getOfficeAddress(), office.getOfficeCall(), office.getOfficeInform(), office.getOfficeImage());	
		}
		else {
			result = officeDao.insert(office);
		}
		
		if(result ==0) {
			System.out.println("Error Update Office");
			return false;
		}else {
			System.out.println("Success Update Office");
			return true;
		}
	}
}
