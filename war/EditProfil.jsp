<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.model.Profile" %>
<%@ page import="noobspace.dao.Dao" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="noobspace.model.Comment" %>

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
        <link href="css/Mainpage.css" type="text/css" rel="stylesheet">
      <meta charset="utf-8"> 
  </head>
  <body>
  <div>
    	 <h1 style="float:left;">NoobSpace</h1>
    	 <a href="Mainpage.jsp" style="float:left;"> <input type="button" value="Accueil"> </a>
    	 <a href="Deconnexion" style="float:left;"> <input type="button" value="Se déconnecter"> </a>
    	</div>
    	<div style="clear:both;"></div>
  
  <form action="EditProfil" method="POST" style="float:left;">
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
  <%
		Profile profil = dao.getProfil((String) request.getSession().getAttribute("mail"));
		List<String> interests = profil.getInterests();   
	%>
  <table class="interests" style="float:left;">
		  <tr>
		     <th>Intérêt </th>
		  </tr>
		
		<% for (String interest : interests) {%>
		<tr> 
		<td>
		<%=interest%>
		</td>
		<td>
		<a class="done" href="/removeInterest?interest=<%=interest%>" >[X]</a>
		</td>
		</tr> 
		<%}
		%>
		</table>
  
  <div style="clear:both;"></div>
  
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
  
      	<div id="myPosts">
    	<%
    	
    	Dao d = Dao.INSTANCE;
    	Profile profil2 = d.getProfil((String)request.getSession().getAttribute("mail"));
    	List comments = d.searchComment(profil2);
    	System.out.println(comments);
    	if(comments != null){
	    	Iterator<Comment> it = comments.iterator();
	    	while(it.hasNext()){
	    		Comment courant = it.next();
	    	%>
	    		<div class="friendPost">
	    			<p class="postDate"><%=courant.getMailPublisher()%></p>
	    			<p class="postDate"><%=courant.getPublicationDate().toString()%></p>
	    			<p class="postMessage"><%=courant.getMessage()%></p>
	    		</div>
	    	<%
    	}}
    	%>
    	</div>
  
</body>
</html>
<%}%>
