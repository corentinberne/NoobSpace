package noobspace;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import noobspace.dao.Dao;

public class ServletRemoveFriend extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    String mail = checkNull(req.getParameter("mail"));
    
    HttpSession session = req.getSession();
    Dao.INSTANCE.removeFriend((String) session.getAttribute("mail"), mail);
    
    resp.sendRedirect("/FriendList.jsp");
  }
  
  private String checkNull(String s) {
	    if (s == null) {
	      return "";
	    }
	    return s;
	  }
} 
