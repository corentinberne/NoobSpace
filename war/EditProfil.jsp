<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.model.Profile" %>
<%@ page import="noobspace.dao.Dao" %>

<%
Dao dao = Dao.INSTANCE;
String mail = (String)request.getSession().getAttribute("mail");
Thread.sleep(20);
NoobspaceUser currentUser = dao.getUser(mail);
Profile currentProfile = dao.getProfil(mail);
if(currentUser != null){
	String name = (currentUser.getName() == null ||currentUser.getName().equals("null")) ? "Votre nom" : currentUser.getName();
	String prenom = (currentUser.getFirstName() == null ||currentUser.getFirstName().equals("null")) ? "Votre prenom" : currentUser.getFirstName();
	String date = (currentProfile.getBirthDate() == null ||currentProfile.getBirthDate().equals("null")) ? "dd/mm/aaaa" : currentProfile.getBirthDate();
	String city = (currentProfile.getCity() == null ||currentProfile.getCity().equals("null")) ? "Ville" : currentProfile.getCity();
	String postal = (currentProfile.getCodePostal() == null ||currentProfile.getCodePostal().equals("null")) ? "Code postal" : currentProfile.getCodePostal();
	String address = (currentProfile.getAddress() == null ||currentProfile.getAddress().equals("null")) ? "Adresse" : currentProfile.getAddress();
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
  	<input type="text" name="Nom" value="<%=name%>" ><br/>
  	
  	<label for="Prenom">Prenom</label><br/>
  	<input type="text" name="Prenom" value="<%=prenom%>"><br/>

  	<label for="birthDate">Date de Naissance</label><br/>
  	<input type="date" name="birthDate" value="<%=date%>"><br/>
  	
  	<label for="city">Votre ville</label><br/>
  	<input type="text" name="city" value="<%=city%>"><br/>
  	
  	<label for="postal">Code Postal</label><br/>
  	<input type="text" name="postal" value="<%=postal%>"><br/>
  	
  	<label for="address">Votre adresse</label><br/>
  	<input type="text" name="address" value="<%=address%>"><br/>
  	
  	<input type="submit" value="Envoyer"></input><br/>
  </form>
  
  <p>Montre à tes amis quels sont tes intérêts!</p>    	
    	<form action="/addInterest" method="post" accept-charset="utf-8">
		  <table>
		    <tr>
		      <td><label for="interest">Intérêt</label></td>
		      <td><input type="text" name="interest" id="interest" size="65"/></td>
		    </tr>
		  	<tr>
		      <td colspan="2" align="right"><input type="submit" value="Ajouter"/></td>
		    </tr>
		  </table>
		</form>
		
		
	<%
		Profile profil = dao.getProfil((String) request.getSession().getAttribute("mail"));
		List<String> interests = profil.getInterests();   
	%>
		
	<table>
		  <tr>
		     <th>Intérêt </th>
		  </tr>
		
		<% for (String interest : interests) {%>
		<tr> 
		<td>
		<%=interest%>
		</td>
		<td>
		<a class="done" href="/removeInterest?interest=<%=interest%>" >Supprimer</a>
		</td>
		</tr> 
		<%}
		%>
		</table>
  
</body>
</html>
<%}%>