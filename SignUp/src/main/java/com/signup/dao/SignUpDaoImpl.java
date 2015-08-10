package com.signup.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.signup.vo.User;

public class SignUpDaoImpl implements ISignUpDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean addNewUser(User object) {
		Session session = sessionFactory.openSession();
		session.save(object);
		session.close();
		return true;
	}

	public boolean updateUser(User objectUpdateUser) {
		Session session = sessionFactory.openSession();
		System.out.println("there");
		Transaction tx = session.beginTransaction();
		System.out.println(objectUpdateUser.getDateOfBirth());
		session.update(objectUpdateUser);
		tx.commit();
		session.close();
		
		return true;
	}

	public User checkValidation(User objectCheckValidation) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("token", objectCheckValidation.getToken()));
		User validation = (User) criteria.uniqueResult();

		System.out.println(objectCheckValidation.getToken());
		/*
		 * User validation = (User) session.get(User.class,
		 * objectCheckValidation.getToken());
		 */
		System.out.println(validation.getToken());
		if (validation != null) {
			return validation;
		} 
			return null;
	}
}
