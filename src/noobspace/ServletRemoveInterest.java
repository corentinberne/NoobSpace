package noobspace;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import noobspace.dao.Dao;

public class ServletRemoveInterest extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    String interest = checkNull(req.getParameter("interest"));
    
    HttpSession session = req.getSession();
    Dao.INSTANCE.removeInterest((String) session.getAttribute("mail"), interest);
    
    resp.sendRedirect("/EditProfil.jsp");
  }
  
  private String checkNull(String s) {
	    if (s == null) {
	      return "";
	    }
	    return s;
	  }
} 
