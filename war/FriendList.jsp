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
        <link href="css/Mainpage.css" type="text/css" rel="stylesheet">
    </head>
    
    <body>
    	<div>
    	 <h1 style="float:left;">NoobSpace</h1>
    	 <a href="Mainpage.jsp" style="float:left;"> <input type="button" value="Accueil"> </a>
    	 <a href="Deconnexion" style="float:left;"> <input type="button" value="Se déconnecter"> </a>
    	</div>
    	<div style="clear:both;"></div>

		<h2>Votre liste d'amis</h2>
		
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
		
		<table id="listeAmis">
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
		<a class="done" href="/OtherUserFriendList.jsp?mail=<%=user.getMail()%>" >Voir ses amis</a>
		</td>
		<td>
		<a class="done" href="/OtherUserProfile.jsp?mail=<%=user.getMail()%>" >Voir son profil</a>
		</td>
		<td>
		<a class="done" href="/removeFriend?mail=<%=user.getMail()%>" >[X]</a>
		</td>
		</tr> 
		<%}
		%>
		</table>

    </body>
</html>