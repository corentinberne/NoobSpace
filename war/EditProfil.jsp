<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.model.Profile" %>
<%@ page import="noobspace.dao.Dao" %>

<%
Dao dao = Dao.INSTANCE;
String mail = (String)request.getSession().getAttribute("mail");

NoobspaceUser currentUser = dao.getUser(mail);
Profile currentProfile = dao.getProfil(mail);
System.out.println(currentUser);
if(currentUser != null){

%>

<!DOCTYPE html>

<html>
  <head>
    <title>Editer votre profil</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
  
  <form action="EditProfil" method="POST">
  	<label for="Nom">Nom</label><br/>
  	<input type="text" name="Nom" value="<%=currentUser.getName()%>" ><br/>
  	
  	<label for="Prenom">Prenom</label><br/>
  	<input type="text" name="Prenom" value="<%=currentUser.getFirstName()%>"><br/>

  	<label for="birthDate">Date de Naissance</label><br/>
  	<input type="date" name="birthDate" value="<%=currentProfile.getBirthDate()%>"><br/>
  	
  	<label for="city">Votre ville</label><br/>
  	<input type="text" name="city" value="<%=currentProfile.getCity()%>"><br/>
  	
  	<label for="postal">Code Postal</label><br/>
  	<input type="text" name="postal" value="<%=currentProfile.getCodePostal()%>"><br/>
  	
  	<label for="address">Votre adresse</label><br/>
  	<input type="text" name="address" value="<%=currentProfile.getAddress()%>"><br/>
  	
  	<input type="submit" value="Envoyer"></input><br/>
  </form>
  
</body>
</html>
<%}%>