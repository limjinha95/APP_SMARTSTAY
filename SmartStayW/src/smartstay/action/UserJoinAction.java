package smartstay.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smartstay.model.dto.User;
import smartstay.model.service.UserService;
import smartstay.vo.ActionForward;

public class UserJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User();
		int joinResult=0;
		user.setUserId(request.getParameter("uid"));
		user.setUserPw(request.getParameter("upw"));
		user.setUserName(request.getParameter("uname"));
		user.setUserMobile(request.getParameter("utel"));
		user.setUserCorporateNumber(Integer.parseInt(request.getParameter("businessnum")));
		user.setUserAddress(request.getParameter("city")+request.getParameter("detailaddress"));
		user.setUserToken("0");
		UserService userService = new UserService();
		joinResult = userService.addOwner(user);
		
		ActionForward forward = null;
		if(joinResult!=0) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./index.jsp");
		}
		else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}
	
	

}
