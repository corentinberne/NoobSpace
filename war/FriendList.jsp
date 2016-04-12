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

		<p>Votre liste d'amis</p>
		
		<%
		Dao dao = Dao.INSTANCE;
		NoobspaceUser noobSpaceUser = dao.getUser((String) request.getSession().getAttribute("mail"));
		List<String> friendsMails = noobSpaceUser.getFriends();
		
		ArrayList<NoobspaceUser> friends = new ArrayList<NoobspaceUser>();
		for(int i = 0; i < friendsMails.size(); i++){
			NoobspaceUser u = dao.getUser(friendsMails.get(i));
			friends.add(u);
		}
		    
		%>
        
        <div style="clear: both;"/>  
		You have a total number of <%= friends.size() %>  friends.
		
		<table>
		  <tr>
		     <th>Name </th>
		     <th>First name</th>
		     <th>mail</th>
		  </tr>
		
		<% for (NoobspaceUser user : friends) {%>
		<tr> 
		<td>
		<%=user.getFirstName()%>
		</td>
		<td>
		<%=user.getName()%>
		</td>
		<td>
		<%=user.getMail()%>
		</td>
		<td>
		<a class="done" href="/removeFriend?mail=<%=user.getMail()%>" >Supprimer</a>
		</td>
		</tr> 
		<%}
		%>
		</table>

    </body>
</html>