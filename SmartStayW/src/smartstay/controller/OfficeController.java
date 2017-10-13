package smartstay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		switch(action) {
		case "myOffice":
			myOffice(request, response);
			break;
		case "myRoomList":
			myRoomList(request, response);
			break;
		case "updateOfficeForm":
			updateOfficeForm(request, response);
			break;
		case "updateOffice":
			updateOffice(request, response);
			break;
		case "updateRoomForm":
			updateRoomForm(request, response);
			break;
		case "updateRoom":
			updateRoom(request, response);
			break;
		}
	}
		
	
    public OfficeController() {
        super();
    }
    
    
    /** 숙박업소 관리 */
    protected void myOffice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
    /** 객실 리스트 */
    protected void myRoomList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
    /** 숙박업소 정보 수정 페이지 */
    protected void updateOfficeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
    /** 숙박업소 정보 수정 */
    protected void updateOffice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
    /** 객실 정보 수정 페이지 */
    protected void updateRoomForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
    /** 객실 정보 수정 */
    protected void updateRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request,response);
	}

}
