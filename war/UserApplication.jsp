<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="noobspace.model.NoobspaceUser" %>
<%@ page import="noobspace.dao.Dao" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
  <head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
<%
Dao dao = Dao.INSTANCE;

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

String url = userService.createLoginURL(request.getRequestURI());
String urlLinktext = "Login";
List<NoobspaceUser> users = new ArrayList<NoobspaceUser>();
            
if (user != null){
    url = userService.createLogoutURL(request.getRequestURI());
    urlLinktext = "Logout";
    users = dao.getUsers();
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
You have a total number of <%= users.size() %>  users.

<table>
  <tr>
      <th>Name </th>
      <th>First name</th>
      <th>mail</th>
    </tr>

<% for (NoobspaceUser noobspaceUser : users) {%>
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
<a class="done" href="/done?id=<%=noobspaceUser.getId()%>" >Done</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">New user</div>

<% if (user != null){ %> 

<form action="/new" method="post" accept-charset="utf-8">
  <table>
    <tr>
      <td><label for="name">Name</label></td>
      <td><input type="text" name="name" id="name" size="65"/></td>
    </tr>
    <tr>
      <td valign="top"><label for="first name">First name</label></td>
      <td><input type="text" name="first name" id="first name" size="65"/></td>
    </tr>
  <tr>
    <td valign="top"><label for="mail">Mail</label></td>
    <td><input type="text" name="mail" id="mail" size="65" /></td>
  </tr>
  <tr>
      <td colspan="2" align="right"><input type="submit" value="Create"/></td>
    </tr>
  </table>
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html>