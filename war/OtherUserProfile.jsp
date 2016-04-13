<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.dao.Dao" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8"/>
    </head>
    
    <body>
    	<h1>NoobSpace</h1>
		
		<%
		Dao dao = Dao.INSTANCE;
		Profile profil = dao.getProfile(request.getParameter("mail"));
		NoobspaceUser user = dao.getUser(request.getParameter("mail"));
		List<String> interests = profil.getInterests();
		    
		%>
		
		<p>Vous consultez le profil de <%=user.getFirstName()%> <%=user.getName()%></p>
		<p>Adresse e-mail : <%=user.getMail()%></p>
		<p>Né le : <%=profile.getBirthDate()%></p>
		<p>Habite : <%=profile.getAddress()%>, <%=profile.getCodePostal()%> <%=profile.getCity()%></p>
		
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

    </body>
</html>