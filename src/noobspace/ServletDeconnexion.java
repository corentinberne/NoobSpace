package noobspace;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ServletDeconnexion extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		
		HttpSession session = req.getSession(true);
		Enumeration<String> n = session.getAttributeNames();
		
		while(n.hasMoreElements()) //pas sur que ça marche cette merde
			session.removeAttribute(n.nextElement());
		
		session.removeAttribute("mail");
		resp.sendRedirect("index.html");
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		
		HttpSession session = req.getSession(true);
		Enumeration<String> n = session.getAttributeNames();
		
		while(n.hasMoreElements()) //pas sur que ça marche cette merde
			session.removeAttribute(n.nextElement());
		
		session.removeAttribute("mail");
		resp.sendRedirect("index.html");
		
	}
}
