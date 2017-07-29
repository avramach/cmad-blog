package com.cisco.cmad.blog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cisco.cmad.blog.api.User;
import com.cisco.cmad.blog.test.DatabaseSetup;


public class UserJpaDao implements UserDAO {

	private static EntityManagerFactory factory = null;

	private static UserJpaDao UserJpaDao = null;

	public static UserJpaDao getInstance() {
		if (UserJpaDao == null) {
			UserJpaDao = new UserJpaDao();
			factory = Persistence.createEntityManagerFactory("blogPu");
			DatabaseSetup.addEmf(factory);		
		}
		return UserJpaDao;
	}

	@Override
	public void create(User user) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public User read(String userId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, userId);
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@Override
	public void update(User user) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User userUpdate = em.find(User.class, user.getUserId());
		userUpdate.setUserName(user.getUserName());
		userUpdate.setAboutUser(user.getAboutUser());
		userUpdate.setEmailId(user.getEmailId());
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public void delete(String userId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, userId);
		em.remove(user);
		em.getTransaction().commit();
		em.close();

	}

}
