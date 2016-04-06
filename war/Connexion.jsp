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
    	
    	<p>Pas encore inscrit jeune Noob? C'est par ici!</p>
    	<div class="bouton">
		<input type="button" value="Inscription" href="Inscription.jsp"/>
		</div>
    	
    	<p>Sinon, tu peux te connecter ici!</p>
        <form method="post" action="connexion">
            <fieldset>
                <label for="nom">Email :<span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br/>
                <label for="password">Mot de passe :<span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['password']}</span>
                <br/>
                <input type="submit" value="Connexion" class="sansLabel" />
                <br/>
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
    </body>
</html>