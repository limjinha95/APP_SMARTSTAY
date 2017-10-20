package smartstay.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartstay.model.dao.OfficeDao;
import smartstay.model.dao.UserDao;
import smartstay.model.dto.Office;
import smartstay.model.dto.Room;
import smartstay.model.service.RoomListService;
import smartstay.vo.ActionForward;
import smartstay.vo.PageInfo;

public class RoomListViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String uid = (String)session.getAttribute("id");
		UserDao user = new UserDao();
		OfficeDao od = new OfficeDao();
		
		int userNo = user.selectOne(uid);
		Office office = od.selectOne(userNo);
		if(office!=null) {
			int officeNum = office.getOfficeNo();
			
			RoomListService rls = new RoomListService();
			ArrayList<Room> roomList = rls.getRoomList(officeNum);
			
			int page = 1;
			int limit = 6;
			int listCount = roomList.size();		
			int maxPage =(int)((double)listCount/limit+0.95);
			int startPage = (((int)((double)page/6+0.9))-1)*6+1;
			int endPage = startPage+6-1;
			if(endPage > maxPage)
				endPage= maxPage;
			PageInfo pageInfo = new PageInfo();	
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);	
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("roomList", roomList);
		}
				
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("room_manage.jsp");
		
		return forward;
	}

}
