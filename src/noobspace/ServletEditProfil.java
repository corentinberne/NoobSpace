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
public class ServletEditProfil extends HttpServlet {
    
  public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
    System.out.println("Creating new noobspace user ");

    HttpSession session = req.getSession();
    
    
    String mail = (String)session.getAttribute("mail");
    System.out.println(mail);
    String nom = checkNull(req.getParameter("Nom"));
    String prenom = checkNull(req.getParameter("Prenom"));
    String birthDate = checkNull(req.getParameter("birthDate"));
    String city = checkNull(req.getParameter("city"));
    String address = checkNull(req.getParameter("address"));
    String codePostal = checkNull(req.getParameter("postal"));

    System.out.println("editage profil !!!!!");
    
    Dao.INSTANCE.updateUserNames(nom, prenom,  mail);
    Dao.INSTANCE.editProfile(mail, birthDate, city, codePostal ,address);
    resp.sendRedirect("/Mainpage.jsp");
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
