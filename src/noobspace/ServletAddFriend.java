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
import noobspace.model.NoobspaceUser;

@SuppressWarnings("serial")
public class ServletAddFriend extends HttpServlet {
    
  public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
    System.out.println("Adding a friend");
    /*User user = (User) req.getAttribute("user");
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }*/
    Dao dao = Dao.INSTANCE;
    String mail = checkNull(req.getParameter("mail"));     
    HttpSession session = req.getSession();
    String sessionMail = (String) session.getAttribute("mail");
    if(!mail.equals(sessionMail) && !dao.getUser(sessionMail).hasFriend(mail))
    	dao.addFriend(sessionMail, mail);
    
    resp.sendRedirect("/Mainpage.jsp");
  }
  
  public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
	    System.out.println("Adding a friend");
	    /*User user = (User) req.getAttribute("user");
	    if (user == null) {
	      UserService userService = UserServiceFactory.getUserService();
	      user = userService.getCurrentUser();
	    }*/
	    Dao dao = Dao.INSTANCE;
	    String mail = checkNull(req.getParameter("mail"));     
	    HttpSession session = req.getSession();
	    String sessionMail = (String) session.getAttribute("mail");
	    if(!mail.equals(sessionMail) && !dao.getUser(sessionMail).hasFriend(mail))
	    	dao.addFriend(sessionMail, mail);
	    
	    resp.sendRedirect("/Mainpage.jsp");
	  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
