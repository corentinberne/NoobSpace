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
public class ServletConnectUser extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    System.out.println("Connecting");

    String mail = checkNull(req.getParameter("email"));
    String password = checkNull(req.getParameter("password"));

    HttpSession session = req.getSession(true);
    session.setAttribute("mail", mail);
    
    boolean authenticationSuccessful = Dao.INSTANCE.checkUser(mail, password);
    if(!authenticationSuccessful){
    	resp.sendRedirect("/index.html");
    }
    else {
    	resp.sendRedirect("/Mainpage.jsp");
    }
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
