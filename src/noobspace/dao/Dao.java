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
