package noobspace;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noobspace.dao.Dao;

public class ServletRemoveUser extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    String id = req.getParameter("id");
    Dao.INSTANCE.remove(Long.parseLong(id));
    resp.sendRedirect("/UserApplication.jsp");
  }
} 
