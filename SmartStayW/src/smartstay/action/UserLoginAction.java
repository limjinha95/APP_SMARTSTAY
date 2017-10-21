package smartstay.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartstay.model.dto.User;
import smartstay.model.service.UserService;
import smartstay.vo.ActionForward;

public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User user = new User();
		user.setUserId(request.getParameter("uid"));
		user.setUserPw(request.getParameter("upw"));
		UserService userService = new UserService();
		int loginResult = userService.checkLoginInfo(request.getParameter("uid"), request.getParameter("upw"));
		ActionForward forward = null;
		if(loginResult !=0) {
			forward = new ActionForward();
			session.setAttribute("id", user.getUserId());
			forward.setRedirect(true);
			forward.setPath("./officeRegisterView.do");
		}
		else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인실패');");
			out.println("location.href='./index.jsp'");
			out.println("</script>");
		}
		
		return forward;
	}

}



