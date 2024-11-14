package sd.Sb_TreasureHunt_REST.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import sd.Sb_TreasureHunt_REST.model.User;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;

	public Database() {

		Configuration cfg = new Configuration();
		cfg.configure();
		this.sessionFactory = cfg.buildSessionFactory();
	}

	public User getUserByNameAndPwd(String uName, String pwd) {

		User user = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = session.createSelectionQuery(
					"SELECT u FROM User u WHERE u.name=?1 AND u.pwd=?2",
					User.class
				);
		query.setParameter(1, uName);
		query.setParameter(2, pwd);
		
		try {
			user = query.getSingleResult();
		}
		catch(NoResultException e) {
			System.out.println("Wrong username: " + uName + " or password: " + pwd);
		}
		
		
		tx.commit();
		session.close();
		
		return user;
	}

	public User getUserByName(String name) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println(name);
		
		SelectionQuery<User> query = session.createSelectionQuery(
				"SELECT u FROM User u WHERE u.name=?1",
				User.class
			);
		query.setParameter(1, name);
		
		User user = query.getSingleResult();
		
		tx.commit();
		session.close();
		
		return user;
	}

	public void mergeUser(User user) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(user);
		
		tx.commit();
		session.close();
		
		
	}
	
	

}
