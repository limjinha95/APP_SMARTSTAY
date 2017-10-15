package smartstay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartstay.model.service.UserService;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userSV = new UserService();
       
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		switch(action) {
		case "home":
			home(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "join":
			join(request, response);
			break;
		case "joinSave":
			joinSave(request, response);
			break;
		}
	}
		
	
    public UserController() {
        super();
    }
    
    
    /** 홈 이동 */
    protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
    /** 로그인 */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		if (userId.length() > 0 && userPw.length() > 0) {
			int userNo = userSV.checkLoginInfo(userId, userPw);
			
			if (userNo >= 1 && userNo <= 1000) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userId", userId);
				session.setAttribute("userPw", userPw);

				//todo : 로그인 후 가져올 정보  
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("Message", "아이디, 비밀번호를 다시 확인해주시기 바랍니다.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("Message", "로그인 정보를 입력하시기 바랍니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}
    
    /** 로그아웃 */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);

		if(session != null && session.getAttribute("userNo") != null ) {
			session.removeAttribute("userId");
			session.removeAttribute("userPw");
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else {
			request.setAttribute("Message", "로그인 후 사용하시기 바랍니다.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
    
    /** 회원가입 */
    protected void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
    /** 회원가입 저장 */
    protected void joinSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request,response);
	}

}
