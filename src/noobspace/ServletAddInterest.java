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
public class ServletAddInterest extends HttpServlet {
    
  public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
    System.out.println("Adding an interest");
    /*User user = (User) req.getAttribute("user");
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }*/
    Dao dao = Dao.INSTANCE;
    String interest = checkNull(req.getParameter("interest"));
    HttpSession session = req.getSession();
    String sessionMail = (String) session.getAttribute("mail");
    dao.addInterest(sessionMail, interest);
    
    resp.sendRedirect("/EditProfil.jsp");
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
