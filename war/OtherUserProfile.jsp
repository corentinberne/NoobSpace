<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.model.Profile" %>
<%@ page import="noobspace.dao.Dao" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="noobspace.model.Post" %>
<%@ page import="noobspace.model.Comment" %>


<!DOCTYPE html> 

<html>
    <head>
        <meta charset="utf-8"/>
        <link href="css/Mainpage.css" type="text/css" rel="stylesheet">
    </head>
    
    <body>
    
    	<div>
    	 <h1 style="float:left;">NoobSpace</h1>
    	 <a href="Mainpage.jsp" style="float:left;"> <input type="button" value="Accueil"> </a>
    	 <a href="Deconnexion" style="float:left;"> <input type="button" value="Se déconnecter"> </a>
    	</div>
    	<div style="clear:both;"></div>
		
		<%
		Dao dao = Dao.INSTANCE;
		Profile profile = dao.getProfil(request.getParameter("mail"));
		NoobspaceUser user = dao.getUser(request.getParameter("mail"));
		List<String> interests = profile.getInterests();
		    
		%>
		<table>
			<tr><th>Vous consultez le profil de <%=user.getFirstName()%> <%=user.getName()%></th>
			</tr>
			<tr>
				<td><p>Adresse e-mail : <%=user.getMail()%></p>
				</td>
				<td><p style="margin-right:20px;margin-left:20px;">Né le : <%=profile.getBirthDate()%></p>
				</td>
				<td><p>Habite : <%=profile.getAddress()%>, <%=profile.getCodePostal()%> <%=profile.getCity()%></p>
				</td>
			</tr>
			<tr>
				<th>Intérêts</th>
			</tr>
			<tr class="interests">
				<% int i = 0;
					for (String interest : interests) {
					i = i +1; %>
				<td class="interest">
					<%=interest%>
				</td>
				<%  
				if ( (i % 10) == 0) {
				%>
				</tr><tr  class="interests">
				<% }} %>
			</tr>
		</table>
		
		</br>
		<form action="CreateComment?mail=<%=request.getParameter("mail")%>" method="POST">
			<label for="Text">Vous avez un commentaire à faire à la personne ? C'est par là -> </label>
			<input type="text" name="Text">
			<input type="submit" value="Envoyer"></input><br/>
		</form>
  		
  		<div>
  			<div style="width:49%;float:left;">
  			<h3>Post de la personne</h3>
  				<%
		    	List posts = dao.searchPost(profile);
		    	if(posts != null){
			    	Iterator<Post> it = posts.iterator();
			    	while(it.hasNext()){
			    		Post courant = it.next();
			    	%>
			    		<div class="friendPost">
			    			<p class="postDate"><%=courant.getPublicationDate().toString()%></p>
			    			<p class="postMessage"><%=courant.getMessage()%></p>
			    		</div>
			    	<%
		    		}
		    	}
		    	%>
  			</div>
  			<div style="width:49%;float:left;">
	  			<h3>Commentaires concernant la personne</h3>
  				<%
		    	List comments = dao.searchComment(profile);
		    	if(comments != null){
			    	Iterator<Comment> it = comments.iterator();
			    	while(it.hasNext()){
			    		Comment courant = it.next();
			    		NoobspaceUser publisher = dao.getUser(courant.getMailPublisher());
			    	%>
			    		<div class="friendPost">
			    			<p class="identity"><%=publisher.getName() + " " + publisher.getFirstName()%></p>
			    			<p class="postDate"><%=courant.getPublicationDate().toString()%></p>
			    			<p class="postMessage"><%=courant.getMessage()%></p>
			    		</div>
			    	<%
		    		}
		    	}
		    	%>
  			</div>  		
  		
  		</div>
  		
    </body>
</html>