package noobspace;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import noobspace.dao.Dao;
import noobspace.shared.FieldVerifier;

@SuppressWarnings("serial")
public class ServletInscription extends HttpServlet
{

	public static final int MIN_PASS_LENGTH = 6;
	
	public static boolean isValidEmail(String mail)
	{

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return (mail == null)? false : Pattern.matches(EMAIL_PATTERN, mail);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		System.out.println("Creating new noobspace user ...");
		
		String nom = checkNull(req.getParameter("Nom"));
		String prenom = checkNull(req.getParameter("Prenom"));
		String email = checkNull(req.getParameter("Email"));
		String password = checkNull(req.getParameter("pass"));
		String passwordConf = checkNull(req.getParameter("passConf"));

		HttpSession session = req.getSession(true);

		if(session.getAttribute("mail") != null){
			resp.sendRedirect("/Mainpage.jsp");
		}
		
		session.removeAttribute("erreurs");
		if (!checkInfos(session, nom, prenom, email, password, passwordConf))
		{
			System.out.println("... fail :(");
			resp.sendRedirect("/Inscription.jsp");
		} else
		{
			session.removeAttribute("erreurs");
			session.setAttribute("mail", email);

			Dao.INSTANCE.add(nom, prenom, email, password);
			Dao.INSTANCE.creerProfilVide(email);
			System.out.println("... succes !");
			resp.sendRedirect("/EditProfil.jsp");
		}
	}

	private String checkNull(String s)
	{
		if (s == null)
		{
			return "";
		}
		return s;
	}

	private boolean checkInfos(HttpSession session, String nom, String prenom, String email, String password, String passwordConf)
	{

		Map<String, String> erreurs = new HashMap<String, String>();
		session.setAttribute("erreurs", erreurs);
		boolean res = isValidEmail(email);
		if (!res)
			erreurs.put("mail", "Format d'email invalide");
		else if(!Dao.INSTANCE.isEmailFree(email)){
			erreurs.put("mail", "Email déjà utilisé");
			res = false;
		}
		
		if (nom.equals(""))
		{
			erreurs.put("nom", "Invalide");
			res = false;
		}
		if (prenom.equals(""))
		{
			erreurs.put("prenom", "Invalide");
			res = false;
		}
		if (password.length() < MIN_PASS_LENGTH)
		{
			erreurs.put("password", "Mot de passe trop court (Inferrieur à "+ MIN_PASS_LENGTH +" caractères");
			res = false;
		}
		if (!password.equals(passwordConf))
		{
			res = false;
			erreurs.put("password2", "Mots de passe differents");
		}
		return res;
	}
}
