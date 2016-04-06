<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8"/>
        <link rel="stylesheet" href="NoobSpace.css" />
    </head>
    
    <body>
    	<h1>NoobSpace</h1>

		<p>Votre liste d'amis</p>
		
		<%
		Dao dao = Dao.INSTANCE;
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		NoobspaceUser noobSpaceUser = dao.getUser(request.getSession().getAttribute("mail"));
		List<NoobSpaceUser> friends = noobSpaceUser.getFriends();
		            
		if (user != null){
		    url = userService.createLogoutURL(request.getRequestURI());
		    urlLinktext = "Logout";
		}
		    
		%>
		  <div style="width: 100%;">
		    <div class="line"></div>
		    <div class="topLine">
		      <div style="float: left;"><img src="images/user.png" /></div>
		      <div style="float: left;" class="headline">users</div>
		      <div style="float: right;"><a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%></div>
		    </div>
		  </div>
        
        <div style="clear: both;"/>  
		You have a total number of <%= friends.size() %>  friends.
		
		<table>
		  <tr>
		      <th>Name </th>
		      <th>First name</th>
		      <th>mail</th>
		    </tr>
		
		<% for (NoobspaceUser noobspaceUser : friends) {%>
		<tr> 
		<td>
		<%=noobspaceUser.getName()%>
		</td>
		<td>
		<%=noobspaceUser.getFirstName()%>
		</td>
		<td>
		<%=noobspaceUser.getMail()%>
		</td>
		<td>
		<a class="deleteFriend" href="/deleteFriend?id=<%=noobspaceUser.getId()%>" >Supprimer ami</a>
		</td>
		</tr> 
		<%}
		%>
		</table>

    </body>
</html>