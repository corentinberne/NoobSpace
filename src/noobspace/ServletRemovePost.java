package noobspace;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noobspace.dao.Dao;

public class ServletRemovePost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	try {
	    System.out.println("yololoo");
	    long id = Long.parseLong(req.getParameter("id"));
	    System.out.println("yololo0o");
	    Dao.INSTANCE.removePost((String) req.getSession().getAttribute("mail"), Dao.INSTANCE.getPost(id));
	    System.out.println("yololo2o");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	resp.sendRedirect("/Mainpage.jsp");
    }
}
