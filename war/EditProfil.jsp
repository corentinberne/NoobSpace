<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.dao.Dao" %>

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
  	<input type="text" name="Nom" value="Votre nom" ><br/>
  	
  	<label for="Prenom">Prenom</label><br/>
  	<input type="text" name="Prenom" value="Votre Prenom"><br/>

  	<label for="birthDate">Date de Naissance</label><br/>
  	<input type="date" name="birthDate" value="jj/mm/aaaa"><br/>
  	
  	<label for="ville">Votre ville</label><br/>
  	<input type="text" name="ville" value="Ville"><br/>
  	
  	<label for="postal">Code Postal</label><br/>
  	<input type="text" name="postal" value="Code Postal"><br/>
  	
  	<label for="city">Votre adresse</label><br/>
  	<input type="text" name="city" value="x, rue Pierre"><br/>
  	
  	<input type="submit" value="Envoyer"></input><br/>
  </form>
  
</body>
</html>