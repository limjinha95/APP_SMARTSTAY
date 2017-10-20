package smartstay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartstay.action.Action;
import smartstay.action.OfficeRegistAction;
import smartstay.action.OfficeViewAction;
import smartstay.action.UserJoinAction;
import smartstay.action.UserLogoutAction;
import smartstay.vo.ActionForward;

public class OfficeFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OfficeFrontController() {
		super();
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		Action action = null;
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("index.jsp");
		} 
		else {

			if (command.equals("/officeRegisterView.do")) {
				action = new OfficeViewAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (command.equals("/officeRegisterAction.do")) {
				action = new OfficeRegistAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 

			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
