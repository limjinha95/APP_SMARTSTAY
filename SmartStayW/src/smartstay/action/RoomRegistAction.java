package smartstay.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import smartstay.model.dao.OfficeDao;
import smartstay.model.dao.UserDao;
import smartstay.model.dto.Office;
import smartstay.model.dto.Room;
import smartstay.model.service.RoomRegistService;
import smartstay.vo.ActionForward;

public class RoomRegistAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoomRegistService rrs = new RoomRegistService();
		String realFolder = "";
		String origFileName = null;
		String imgPath = null;
		String saveFolder="/static/img";
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("id");
		UserDao user = new UserDao();
		int userNo = user.selectOne(userId);
		OfficeDao od = new OfficeDao();
		Office of = od.selectOne(userNo);
		int officeNum = of.getOfficeNo();
		int maxSize = 5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());	
		Enumeration e = multi.getFileNames();
		String file = (String)e.nextElement();
		origFileName = multi.getOriginalFileName(file);
		imgPath = realFolder+"\\"+origFileName;
		Room room = new Room(officeNum, Integer.parseInt(multi.getParameter("roomnum")), multi.getParameter("roomname"), multi.getParameter("roomtype"), Integer.parseInt(multi.getParameter("standard")), Integer.parseInt(multi.getParameter("max")), Integer.parseInt(multi.getParameter("cost")), origFileName);

		boolean isRegistSuccess = rrs.registRoom(room);
		ActionForward forward = null;
		
		if(isRegistSuccess) {
			forward= new ActionForward();
			forward.setRedirect(true);
			forward.setPath("roomList.rc");
			return forward;
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패');");
			out.println("history.back();");
			out.println("</script>");
			
		}
		
		return forward;
	}
	

}
