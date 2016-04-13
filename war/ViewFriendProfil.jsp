<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.dao.Dao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="noobspace.model.Profile" %>


<!DOCTYPE html> 

<html>
    <head>
        <meta charset="utf-8"/>
    </head>
    
    <body>
    	<h1>NoobSpace</h1>
		
		<%
		Dao dao = Dao.INSTANCE;
		Profile profile = dao.getProfil(request.getParameter("mail"));
		NoobspaceUser user = dao.getUser(request.getParameter("mail"));
		List<String> interests = profile.getInterests();
		    
		%>
		<table>
			<tr><p>Vous consultez le profil de <%=user.getFirstName()%> <%=user.getName()%></p>
			</tr>
			<tr>
				<td><p>Adresse e-mail : <%=user.getMail()%></p>
				</td>
			</tr>
			<tr>
				<td><p>Né le : <%=profile.getBirthDate()%></p>
				</td>
			</tr>
			<tr>
				<td><p>Habite : <%=profile.getAddress()%>, <%=profile.getCodePostal()%> <%=profile.getCity()%></p>
				</td>
			</tr>
			<tr>
				<p>Intérêt </p>
			</tr>
			<tr>
				<% int i = 0;
					for (String interest : interests) { %>
				<td class="interest">
					<%=interest%>
				</td>
				<% } 
				if ( i % 10 == 0) {
				%>
				</tr><tr>
				<% } %>
			</tr>
			
		</table>
		
		
		
		
		
		<table>
		  <tr>
		     <th>Intérêt </th>
		  </tr>
		
		<% for (String interest : interests) {%>
		<tr> 
		<td>
		<%=interest%>
		</td>
		</tr> 
		<%}
		%>
		</table>
		
		  <form action="CreateComment?mail=<%=request.getParameter("mail")%>" method="POST">
		  	<label for="Text">Text</label><br/>
		  	<input type="text" name="Text"><br/>
		  	<input type="submit" value="Envoyer"></input><br/>
  		</form>
		
    </body>
</html>