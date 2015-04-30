package com.agilent.earray8.springconfig.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agilent.earray8.springconfig.beans.User;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    protected final Logger log = LoggerFactory.getLogger(getClass());

   
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		Session session = _sessionFactory.getCurrentSession();
		if (session == null)
			session = _sessionFactory.openSession();
		return session;
	}

	public void save(User user) {
		getSession().save(user);
		log.info("User is Saved. User="+user);
		return;
	}

	public void delete(User user) {
		getSession().delete(user);
		log.info("User is Deleted. User="+user);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return getSession().createQuery("from User").list();
	}

	public User getByEmail(String email) {
		return (User) getSession()
				.createQuery("from User where email = :email")
				.setParameter("email", email).uniqueResult();
	}

	public User getById(long id) {
		return (User) getSession().get(User.class, id);
	}

	public void update(User user) {
		getSession().saveOrUpdate(user);
		log.info("User is Updated. User="+user);
		return;
	}
}
