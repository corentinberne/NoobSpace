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

    String mail = checkNull(req.getParameter("mail"));
    NoobspaceUser friendToAdd = Dao.INSTANCE.getUser(mail);
    
    HttpSession session = req.getSession();
    NoobspaceUser user = Dao.INSTANCE.getUser((String) session.getAttribute("mail"));
    
    System.out.println(session.getAttribute("mail"));
    System.out.println(friendToAdd.getMail());
    
    user.getFriends().add(friendToAdd);

    resp.sendRedirect("/Mainpage.jsp");
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
