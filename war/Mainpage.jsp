<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="noobspace.dao.Dao" %>
<%@ page import="noobspace.model.Profile" %>
<%@ page import="noobspace.model.Post" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="noobspace.model.NoobspaceUser"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8"/>
        <link href="css/Mainpage.css" type="text/css" rel="stylesheet">
    </head>
     <%
  if(request.getSession() != null){
  	if(request.getSession().getAttribute("mail") == null){
  		System.out.println(request.getSession().getAttribute("mail"));
  		out.print("<script>document.location.href=\"index.html\"</script>");}}
  %>
    <body>
    	<h1>NoobSpace</h1>
    	
    	<p>Tu veux voir la liste des Noobs qui te servent d'amis? C'est juste ici!</p>
    	<a href="FriendList.jsp"> <input type="button" value="Liste d'amis"> </a><br/>
    	<a href="EditProfil.jsp"> <input type="button" value="Modifier mon profil"> </a>
    	<a href="NewPost.jsp"> <input type="button" value="Publier"> </a><br/>
    	<p>Tu veux te faire de nouveaux amis? Pour en ajouter c'est par là!</p>    	
    	<form action="/addFriend" method="post" accept-charset="utf-8">
		  <table>
		    <tr>
		      <td><label for="mail">Mail</label></td>
		      <td><input type="email" name="mail" id="mail" size="65"/></td>
		    </tr>
		  	<tr>
		      <td colspan="2" align="right"><input type="submit" value="Ajouter"/></td>
		    </tr>
		  </table>
		</form>
		
		<p>Tu veux rechercher des Noob avec des intérêts en commun avec toi? C'est en-dessous!</p>    	
    	<form action="/SearchInterestResults.jsp" method="post" accept-charset="utf-8">
		  <table>
		    <tr>
		      <td><label for="interest">Intérêt</label></td>
		      <td><input type="interest" name="interest" id="interest" size="65"/></td>
		    </tr>
		  	<tr>
		      <td colspan="2" align="right"><input type="submit" value="Rechercher"/></td>
		    </tr>
		  </table>
		</form>
		
		<p>Tu veux publier quelque chose? C'est juste là!</p>   
		<form action="CreatePost" method="POST">
  			<label for="Text">Contenu de ta publication</label><br/>
  			<input type="text" name="Text"><br/>
  			<input type="submit" value="Envoyer"></input><br/>
  		</form>
    	
    	<p>Tes derniers posts de Noob</p>
    	<div id="myPosts">
    	<%
    	
    	Dao d = Dao.INSTANCE;
    	Profile profil = d.getProfil((String)request.getSession().getAttribute("mail"));
    	List posts = d.searchPost(profil);
    	if(posts != null){
	    	Iterator<Post> it = posts.iterator();
	    	while(it.hasNext()){
	    		Post courant = it.next();
	    	%>
	    		<div class="myPost">
	    			<p class="postDate"><%=courant.getPublicationDate().toString()%></p>
	    			<p class="postMessage"><%=courant.getMessage()%></p>
	    		</div>
	    	<%
    	}}
    	%>
    	</div>
    	
    	
    	<div id="myFriendsPosts">
    	<p>Les derniers posts des Noobs de ta liste d'amis</p>
    	<%

    	NoobspaceUser user = d.getUser((String)request.getSession().getAttribute("mail"));
    	List friends = user.getFriends();
    	Iterator<String> it;
    	String friend;
    	Profile profilFriend;
    	List postsFriend;
    	Iterator<Post> itPostFriend;
    	Post courantFriend;
    	NoobspaceUser friendUser;
		if(friends == null){
		%>
    		<div class="friendPost">
    		<p>Aucun ami</p>
    		</div>
    	<%
    	} else {
		    it = friends.iterator();
		    while(it.hasNext()){
		    	friend = it.next();
		    	%>
		    	<div class="friendPost2">
		    	<%
		    	profilFriend = d.getProfil(friend);
		    	friendUser = d.getUser(friend);
		    	postsFriend = d.searchPost(profilFriend);
		    	if(posts != null){
		    		itPostFriend = postsFriend.iterator();
		    		while(itPostFriend.hasNext()){
		    			courantFriend = itPostFriend.next();
		    			%>
		    			<div class="friendPost">
		    			<p class="postDate">De <span class="postFrom"><%=friendUser.getName()+ " " + friendUser.getFirstName()%></span> Le <%=courantFriend.getPublicationDate().toString()%></p>
		    			<p class="postMessage"><%=courantFriend.getMessage()%></p>
		    			<!--<a href="/SuprPost?id=<%=courantFriend.getId().getId()%>">X</a>-->
		    			</div>
		    			<%
				    }
				}
				%>
				</div>
				<%
			}
		}
		%>
		</div>
		<a href="Deconnexion"> <input type="button" value="Se déconnecter"> </a>
    </body>
</html>
