package noobspace;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import noobspace.dao.Dao;

@SuppressWarnings("serial")
public class ServletCreatePost extends HttpServlet {
    
  public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
    System.out.println("Creating a new post ");

    HttpSession session = req.getSession();
    
    
    String mail = (String)session.getAttribute("mail");
    String msg = checkNull(req.getParameter("Text"));
    if(!msg.equals(""))
    	Dao.INSTANCE.createPost(mail, msg);
    resp.sendRedirect("/Mainpage.jsp");
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
