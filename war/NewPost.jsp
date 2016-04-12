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
if(currentUser != null){

%>

<!DOCTYPE html>

<html>
  <head>
    <title>Creer un post</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
  
  <form action="CreatePost" method="POST">
  	<label for="Text">Text</label><br/>
  	<input type="text" name="Text"><br/>
  	
  	<input type="submit" value="Envoyer"></input><br/>
  </form>
  
</body>
</html>
<%}%>