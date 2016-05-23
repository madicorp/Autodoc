package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Role;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Diop Sega
 *
 */
@Service("Role")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private  SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		List<Role> roles = null;
		
		try {
			roles = sf.getCurrentSession().createQuery("FROM Role r").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return roles;
	}

	@Override
	public Role findById(Integer id) {
		Role role = null;
		try {
			
			role = (Role) sf.getCurrentSession().get(Role.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByName(String code) {
		
		List<Role> roles = null;
		
		try {
			roles = sf.getCurrentSession().createQuery("FROM Role r WHERE r.codeRole = :code").setParameter("code", code).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return roles;
	}

	@Override
	public boolean save(Object role) {
		boolean saved = false;
		
		role = (Role) role;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(role);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object role) {
		boolean deleted = false;
		role = (Role) role;
		
		try {
			
			sf.getCurrentSession().delete(role);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

}
