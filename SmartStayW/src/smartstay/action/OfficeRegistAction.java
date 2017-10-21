package smartstay.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import smartstay.model.dao.UserDao;
import smartstay.model.dto.Office;
import smartstay.model.service.OfficeRegistService;
import smartstay.vo.ActionForward;

public class OfficeRegistAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OfficeRegistService of = new OfficeRegistService();
		String realFolder = "";
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("id"); 
		UserDao ud = new UserDao();
		String origFileName = null;
		String imgPath = null;
		int userNo = ud.selectOne(userId);
		String saveFolder="/static/img";
		int maxSize = 5*1024*1024;
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		String image = multi.getFilesystemName("image");
		
		Enumeration e = multi.getFileNames();
		String file = (String)e.nextElement();
		origFileName = multi.getOriginalFileName(file);
		imgPath = realFolder+"\\"+origFileName;
		
		Office office = new Office(userNo, multi.getParameter("hotelname"), multi.getParameter("hoteladdress"), multi.getParameter("hotelphone"), multi.getParameter("hotelinfo"), origFileName);

		boolean isRegistSuccess = of.registOffice(office);
		ActionForward forward = null;
		
		if(isRegistSuccess) {
			forward= new ActionForward();
			forward.setRedirect(true);
			forward.setPath("officeRegisterView.do");
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

