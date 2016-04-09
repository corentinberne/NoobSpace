package noobspace.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import noobspace.model.NoobspaceUser;
import noobspace.model.Profile;

public enum Dao
{
	INSTANCE;

	public List<NoobspaceUser> listUsers()
	{
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select u from NoobspaceUser u");
		List<NoobspaceUser> users = q.getResultList();
		em.close();
		return users;
	}

	public void add(String name, String firstName, String mail, String password)
	{
		synchronized (this)
		{
			EntityManager em = EMFService.get().createEntityManager();
			NoobspaceUser user = new NoobspaceUser(name, firstName, mail, password);
			em.persist(user);
			em.close();
		}
	}

	public void addFriend(String adderMail, String toAddMail)
	{
		synchronized (this)
		{
			EntityManager em = EMFService.get().createEntityManager();
			NoobspaceUser user = this.getUser(adderMail);
			user.addNoobFriend(toAddMail);
			em.persist(user);
			em.close();
		}
	}

	public void updateUserNames(String name, String firstName, String mail)
	{
		synchronized (this)
		{
			EntityManager em = EMFService.get().createEntityManager();
			NoobspaceUser user = this.getUser(mail);
			user.setFirstName(firstName);
			user.setName(name);
			em.persist(user);
			em.close();
		}
	}

	public void editProfile(String mail, String birthDate, String city, String codePostal, String address)
	{
		synchronized (this)
		{
			EntityManager em = EMFService.get().createEntityManager();
			Profile userProfile = this.getProfil(mail);
			if (userProfile == null)
				userProfile = new Profile(mail, birthDate, address, codePostal, city);
			else
			{
				userProfile.setAddress(address);
				userProfile.setBirthDate(birthDate);
				userProfile.setCity(city);
				userProfile.setCodePostal(codePostal);
			}
			em.persist(userProfile);
			em.close();
		}
	}

	public List<NoobspaceUser> getUsers()
	{
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from NoobspaceUser u");
		List<NoobspaceUser> users = q.getResultList();
		em.close();
		return users;
	}

	public boolean checkUser(String mail, String password)
	{
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from NoobspaceUser u where u.mail= :mail and u.password= :password");
		q.setParameter("mail", mail);
		q.setParameter("password", password);
		List<NoobspaceUser> users = q.getResultList();
		em.close();
		return (users.size() == 1);
	}

	public NoobspaceUser getUser(String mail)
	{
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from NoobspaceUser u where u.mail= :mail");
		q.setParameter("mail", mail);
		List<NoobspaceUser> users = q.getResultList();
		em.close();
		if (users.size() == 1)
			return users.get(0);
		else
		{
			return null;
		}
	}

	public Profile getProfil(String mail)
	{
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select p from Profile p where p.mail= :mail");
		q.setParameter("mail", mail);
		List<Profile> profiles = q.getResultList();
		em.close();
		if (profiles.size() == 1)
			return profiles.get(0);
		else
		{
			return null;
		}
	}

	public void remove(long id)
	{
		EntityManager em = EMFService.get().createEntityManager();
		try
		{
			NoobspaceUser user = em.find(NoobspaceUser.class, id);
			em.remove(user);
		} finally
		{
			em.close();
		}
	}
}
