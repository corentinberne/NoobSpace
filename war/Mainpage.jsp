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
    	
    	<p>Tu veux voir la liste des Noobs qui te servent d'amis? C'est juste ici!</p>
    	<a href="FriendList.jsp"> <input type="button" value="Liste d'amis"> </a>
    	
    	<p>Tu veux te faire de nouveaux amis? Pour en ajouter c'est par là!</p>    	
    	<form action="/addFriend" method="post" accept-charset="utf-8">
		  <table>
		    <tr>
		      <td><label for="mail">Mail</label></td>
		      <td><input type="text" name="mail" id="mail" size="65"/></td>
		    </tr>
		  	<tr>
		      <td colspan="2" align="right"><input type="submit" value="Ajouter"/></td>
		    </tr>
		  </table>
		</form>
    	
    	<p>Les derniers posts des Noobs de ta liste d'amis</p>
		
    </body>
</html>