package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Diop Sega
 *
 */
@Service("User")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private  SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		List<User> users = null;
		
		try {
			users = sf.getCurrentSession().createQuery("FROM User u").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User findById(Integer id) {
		User user = null;
		try {
			
			user = (User) sf.getCurrentSession().get(User.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByName(String email) {
		
		List<User> users = null;
		
		try {
			users = sf.getCurrentSession().createQuery("FROM User u WHERE u.login = :email").setParameter("email", email).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public boolean save(Object user) {
		boolean saved = false;
		
		user = (User) user;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(user);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object user) {
		boolean deleted = false;
		user = (User) user;
		
		try {
			
			sf.getCurrentSession().delete(user);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

}
