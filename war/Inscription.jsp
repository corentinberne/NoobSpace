<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="noobspace.dao.Dao" %>
<%
	Map<String,String> erreurs;
	String emailErreur ="";
	String passwordErreur1 ="";
	String passwordErreur2 ="";
	String nomErreur = "";
	String prenomErreur = "";
	
	if(request.getSession() != null){
		erreurs = (Map<String,String>)request.getSession().getAttribute("erreurs"); 
		if(erreurs != null){
		
			if (erreurs.get("nom") != null) nomErreur = "Nom invalide"+"<br/>";
			if (erreurs.get("prenom") != null) prenomErreur = "Prenom invalide"+ "<br/>";
			if (erreurs.get("mail") != null) emailErreur=erreurs.get("mail")+"<br/>";
			if (erreurs.get("password") != null) passwordErreur1 = erreurs.get("password")+"<br/>";
			if (erreurs.get("password2") != null) passwordErreur2 = erreurs.get("password2")+"<br/>";
			
			//Si l'utilisateur recharge la page il faut supprimer les erreurs pour ne pas les re-afficher
			request.getSession().removeAttribute("erreurs"); 
		}
	}
%>

<!DOCTYPE html>

<html>
  <head>
    <title>Inscription</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <%
  if(request.getSession() != null){
  	if(request.getSession().getAttribute("mail") != null){
  		out.print("<script>document.location.href=\"Mainpage.jsp\"</script>");}}
  %>
  <body>
  <center>
  <form action="Inscription" method="POST">
  
  	<span style="color:red" ><%=nomErreur%></span>
  	<label for="Nom">Nom</label><br/>
  	<input type="text" name="Nom" value="Votre nom" ><br/>
  	
  	
  	<span style="color:red" ><%=prenomErreur%></span>
  	<label for="Prenom">Prenom</label><br/>
  	<input type="text" name="Prenom" value="Votre Prenom"><br/>
  	
  	
  	<span style="color:red" ><%=emailErreur%></span>
  	<label for="Email">Votre addrese mail</label><br/>
  	<input type="email" name="Email" value="Ex: toto@example.com" ><br/>
  	
  	
  	<span style="color:red" ><%=passwordErreur1%></span>
  	<label for="pass">Tapez votre mot de passe</label><br/>
  	<input type="password" name="pass"><br/>
  	
  	<span style="color:red" ><%=passwordErreur2%></span>
  	<label for="passConf">Confirmer votre mot de passe</label><br/>
  	<input type="password" name="passConf"><br/>
  	
  	<input type="submit" value="Envoyer"></input><br/>
  </form>
  </center>
</body>
</html>