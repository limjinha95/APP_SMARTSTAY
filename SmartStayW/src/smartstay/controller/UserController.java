package smartstay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

	}
    
    /** 로그아웃 */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
