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
public class ServletInscription extends HttpServlet {
    
  public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
    System.out.println("Creating new noobspace user ");
    /*User user = (User) req.getAttribute("user");
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }*/

    String nom = checkNull(req.getParameter("Nom"));
    String prenom = checkNull(req.getParameter("Prenom"));
    String email = checkNull(req.getParameter("Email"));
    String password = checkNull(req.getParameter("pass"));
    String passwordConf = checkNull(req.getParameter("passConf"));

    System.out.println("yolyoyoyoy");
    
    if(!password.equals(passwordConf))
	 resp.sendRedirect("/Inscription.jsp");
    
    else{
        Dao.INSTANCE.add(nom, prenom, email, password);
        resp.sendRedirect("/UserApplication.jsp");
    }
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
