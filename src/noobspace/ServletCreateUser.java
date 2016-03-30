package noobspace;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import noobspace.dao.Dao;

@SuppressWarnings("serial")
public class ServletCreateUser extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    System.out.println("Creating new noobspace user ");
    /*User user = (User) req.getAttribute("user");
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }*/

    String name = checkNull(req.getParameter("name"));
    String firstName = checkNull(req.getParameter("first name"));
    String mail = checkNull(req.getParameter("mail"));
    String password = checkNull(req.getParameter("password"));

    Dao.INSTANCE.add(name, firstName, mail, password);

    resp.sendRedirect("/UserApplication.jsp");
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
