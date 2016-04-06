package noobspace.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import noobspace.model.NoobspaceUser;

public enum Dao {
  INSTANCE;

  public List<NoobspaceUser> listUsers() {
    EntityManager em = EMFService.get().createEntityManager();
    // read the existing entries
    Query q = em.createQuery("select u from NoobspaceUser u");
    List<NoobspaceUser> users = q.getResultList();
    return users;
  }

  public void add(String name, String firstName, String mail,
      String password) {
    synchronized (this) {
      EntityManager em = EMFService.get().createEntityManager();
      NoobspaceUser user = new NoobspaceUser(name, firstName, mail, password);
      em.persist(user);
      em.close();
    }
  }

  public List<NoobspaceUser> getUsers() {
    EntityManager em = EMFService.get().createEntityManager();
    Query q = em.createQuery("select u from NoobspaceUser u");
    List<NoobspaceUser> users = q.getResultList();
    return users;
  }
  
  public boolean checkUser(String mail, String password) {
	    EntityManager em = EMFService.get().createEntityManager();
	    Query q = em.createQuery("select u from NoobspaceUser u where u.mail= :mail and u.password= :password");
	    q.setParameter("mail", mail);
	    q.setParameter("password", password);
	    List<NoobspaceUser> users = q.getResultList();	    
	    return (users.size()==1);
  }
  
  public NoobspaceUser getUser(String mail) {
	    EntityManager em = EMFService.get().createEntityManager();
	    Query q = em.createQuery("select u from NoobspaceUser u where u.mail= :mail");
	    q.setParameter("mail", mail);
	    List<NoobspaceUser> users = q.getResultList();
	    if(users.size()==1)
	    	return users.get(0);
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
}
