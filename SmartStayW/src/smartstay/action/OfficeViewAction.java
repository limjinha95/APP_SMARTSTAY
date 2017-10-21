package smartstay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartstay.model.dto.Office;
import smartstay.model.service.OfficeViewService;
import smartstay.vo.ActionForward;

public class OfficeViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		OfficeViewService officeViewService = new OfficeViewService();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("id");
		if (userId != null) {
			Office office = officeViewService.getOfficeView(userId);
			if (office != null) {
				request.setAttribute("office", office);
				session.setAttribute("officeNum", office.getOfficeNo());
			}
			ActionForward forward = new ActionForward();
			forward.setPath("hotel_register.jsp");
			return forward;		
		} else {
			System.out.println("Session 만료");
		}
		return null;
	}

}
