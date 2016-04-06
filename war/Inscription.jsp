<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.dao.Dao" %>

<!DOCTYPE html>

<html>
  <head>
    <title>Inscription</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
  
  <form action="Inscription" method="POST">
  	<label for="Nom">Nom</label><br/>
  	<input type="text" name="Nom" value="Votre nom" ><br/>
  	
  	<label for="Prenom">Prenom</label><br/>
  	<input type="text" name="Prenom" value="Votre Prenom"><br/>
  	
  	<label for="Email">Votre addrese mail</label><br/>
  	<input type="text" name="Email" value="Ex: toto@example.com" ><br/>
  	
  	<label for="pass">Tapez votre mot de passe</label><br/>
  	<input type="password" name="pass"><br/>
  	
  	<label for="passConf">Confirmer votre mot de passe</label><br/>
  	<input type="password" name="passConf"><br/>
  	
  	<input type="submit" value="Envoyer"></input><br/>
  </form>
  
</body>
</html>