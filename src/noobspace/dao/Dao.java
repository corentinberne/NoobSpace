package noobspace.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;

import noobspace.model.Comment;
import noobspace.model.NoobspaceUser; 
import noobspace.model.Post;
import noobspace.model.Profile;

public enum Dao {
    INSTANCE;

    public List<NoobspaceUser> listUsers() {
	EntityManager em = EMFService.get().createEntityManager();
	// read the existing entries
	Query q = em.createQuery("select u from NoobspaceUser u");
	List<NoobspaceUser> users = q.getResultList();
	em.close();
	return users;
    }

    public void removeFriend(String deleterMail, String toDeleteMail) {
	synchronized (this) {
	    EntityManager em = EMFService.get().createEntityManager();
	    NoobspaceUser user = this.getUser(deleterMail);
	    user.deleteNoobFriend(toDeleteMail);
	    em.persist(user);
	    em.close();
	}
    }

    public void addFriend(String adderMail, String toAddMail) {
	synchronized (this) {
	    EntityManager em = EMFService.get().createEntityManager();
	    NoobspaceUser user = this.getUser(adderMail);
	    NoobspaceUser userToAdd = this.getUser(toAddMail); 
	    if(userToAdd != null){
	    	user.addNoobFriend(toAddMail);
	    }
	    em.persist(user);
    	em.close();
	}
    }
    
    public void addInterest(String mail, String interest) {
    	synchronized (this) {
    	    EntityManager em = EMFService.get().createEntityManager();
    	    Profile userProfile = this.getProfil(mail);
    	    userProfile.addInterest(interest);
    	    em.persist(userProfile);
    	    em.close();
    }
    }
    
    public void removeInterest(String mail, String interest) {
    	synchronized (this) {
    	    EntityManager em = EMFService.get().createEntityManager();
    	    Profile userProfile = this.getProfil(mail);
    	    userProfile.removeInterest(interest);
    	    em.persist(userProfile);
    	    em.close();
    	}
        }

    public void add(String name, String firstName, String mail, String password) {
	synchronized (this) {
	    EntityManager em = EMFService.get().createEntityManager();
	    NoobspaceUser user = new NoobspaceUser(name, firstName, mail, password);
	    em.persist(user);
	    em.close();
	}
    }

    public void creerProfilVide(String mail) {
	synchronized (this) {
	    EntityManager em = EMFService.get().createEntityManager();
	    Profile userProfile = new Profile(mail);
	    em.persist(userProfile);
	    em.close();
	}
    }

    public void updateUserNames(String name, String firstName, String mail) {
	synchronized (this) {
	    EntityManager em = EMFService.get().createEntityManager();
	    NoobspaceUser user = this.getUser(mail);
	    user.setFirstName(firstName);
	    user.setName(name);
	    em.persist(user);
	    em.close();
	}
    }

    public void editProfile(String mail, String birthDate, String city, String codePostal, String address) {
	synchronized (this) {
	    EntityManager em = EMFService.get().createEntityManager();
	    Profile userProfile = this.getProfil(mail);
	    if (userProfile == null)
		userProfile = new Profile(mail, birthDate, address, codePostal, city);
	    else {
		userProfile.setAddress(address);
		userProfile.setBirthDate(birthDate);
		userProfile.setCity(city);
		userProfile.setCodePostal(codePostal);
	    }
	    em.persist(userProfile);
	    em.close();
	}
    }

    public boolean isEmailFree(String mail) {
	synchronized (this) {
	    EntityManager em = EMFService.get().createEntityManager();
	    Query q = em.createQuery("select u from NoobspaceUser u where u.mail= :mail");
	    q.setParameter("mail", mail);
	    List<NoobspaceUser> users = q.getResultList();
	    em.close();
	    System.out.println(users.size() == 0);
	    return users.size() == 0;
	}
    }

    public List<NoobspaceUser> getUsers() {
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select u from NoobspaceUser u");
	List<NoobspaceUser> users = q.getResultList();
	em.close();
	return users;
    }

    public boolean checkUser(String mail, String password) {
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select u from NoobspaceUser u where u.mail= :mail and u.password= :password");
	q.setParameter("mail", mail);
	q.setParameter("password", password);
	List<NoobspaceUser> users = q.getResultList();
	em.close();
	return (users.size() == 1);
    }

    public NoobspaceUser getUser(String mail) {
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select u from NoobspaceUser u where u.mail= :mail");
	q.setParameter("mail", mail);
	List<NoobspaceUser> users = q.getResultList();
	em.close();
	if (users.size() == 1)
	    return users.get(0);
	else {
	    return null;
	}
    }

    public Profile getProfil(String mail) {
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select p from Profile p where p.mail= :mail");
	q.setParameter("mail", mail);
	List<Profile> profiles = q.getResultList();
	em.close();
	if (profiles.size() == 1)
	    return profiles.get(0);
	else {
	    return null;
	}
    }

    public void remove(long id) {
	EntityManager em = EMFService.get().createEntityManager();
	try {
	    NoobspaceUser user = em.find(NoobspaceUser.class, id);
	    em.remove(user);
	} finally {
	    em.close();
	}
    }
    
    public void createPost(String mail, String msg) {
	EntityManager em = EMFService.get().createEntityManager();
	Profile userProfile = this.getProfil(mail);
	Date d = new Date();
	System.out.println(d.toString());
	Post p = new Post(msg, d.toString());
	userProfile.addMyPosts(p);
	em.persist(userProfile);
	em.close();
    }
    
    public List<Post> searchPost(Profile id) {
	
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select p from Post p where p.user = :id");
	q.setParameter("id", id);
	List<Post> posts = q.getResultList();
	em.close();
	return posts;
	
    }
    
    public void createComment(String mail, String msg, String friendMail) {
	EntityManager em = EMFService.get().createEntityManager();
	Profile userProfile = this.getProfil(friendMail);
	Date d = new Date();
	System.out.println(d.toString());
	Comment p = new Comment(msg, d.toString(), mail);
	userProfile.addCommentFromFriends(p);
	em.persist(userProfile);
	em.close();
    }
    
    public List<Comment> searchComment(Profile id) {
	
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select p from Comment p where p.user = :id");
	q.setParameter("id", id);
	List<Comment> comments = q.getResultList();
	em.close();
	return comments;
	 
    }
    
    public void removePost(String mail, Post p) {
	
	
	
	EntityManager em = EMFService.get().createEntityManager();
	Profile userProfile = new Profile(mail);

	System.out.println("Suppresion post2");
	userProfile.removeMyPosts(p);System.out.println("Suppresion post : " + p);
	em.persist(userProfile);
	em.close();
	
	System.out.println("Suppresion post1");
	EntityManager em2 = EMFService.get().createEntityManager();
	em2.remove(p);
	System.out.println("Suppresion post12");
	em2.close();
	
    }
    
    public Post getPost(long id) {
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select p from Post p");
	List<Post> Posts = q.getResultList();
	for (Post p : Posts) {
	    if(p.getId().getId() == id){
		em.close();
		return p;}
	}
	em.close();
	return null;
    }

	
	public List<Profile> getProfiles()
	{
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select p from Profile p");
		List<Profile> profiles = q.getResultList();
		em.close();
		return profiles;
	}
	
	public List<String> getInterest(String interestName){
		List<String> mails = new ArrayList<String>();
		List<Profile> profiles = getProfiles();
		for (Profile currentProfile : profiles){
			if(currentProfile.searchInterest(interestName))
				mails.add(currentProfile.getMail());
		}
		return mails;
	}

}
