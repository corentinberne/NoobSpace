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
public class ServletCreateComment extends HttpServlet {
    
  public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
    System.out.println("Creating a new post ");

    HttpSession session = req.getSession();
    
    
    String mailPublisher = (String)session.getAttribute("mail");
    String mailDestinataire = checkNull((String) req.getParameter("mail"));;
    String msg = checkNull(req.getParameter("Text"));
    if(!msg.equals(""))
    	Dao.INSTANCE.createComment(mailPublisher, msg, mailDestinataire);
    resp.sendRedirect("/OtherUserProfile.jsp?mail="+mailDestinataire);
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}
