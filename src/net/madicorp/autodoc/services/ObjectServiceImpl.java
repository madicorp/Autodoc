package net.madicorp.autodoc.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.madicorp.autodoc.models.ObjectType;
import net.madicorp.autodoc.models.User;

@Service("Object")
 @Transactional
public class ObjectServiceImpl implements ObjectService {

	@Autowired
	SessionFactory sf;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<net.madicorp.autodoc.models.Object> findAll() {
		
		List<net.madicorp.autodoc.models.Object> objects = null;
		
		try {
			objects = sf.getCurrentSession().createQuery("FROM Object o").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objects;
	}

	@Override
	public net.madicorp.autodoc.models.Object findById(Integer id) {
		
		net.madicorp.autodoc.models.Object object = null;
		try {
			
			object = (net.madicorp.autodoc.models.Object) sf.getCurrentSession().get(net.madicorp.autodoc.models.Object.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<net.madicorp.autodoc.models.Object> findByName(String name) {

		List<net.madicorp.autodoc.models.Object> objects = null;
		
		try {
			objects = sf.getCurrentSession().createQuery("FROM Object o WHERE o.nomGabarit = :name").setParameter("name", name).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objects;
	}

	@Override
	public boolean save(Object object) {
		boolean saved = false;
		
		object = (net.madicorp.autodoc.models.Object) object;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(object);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object object) {
		boolean deleted = false;
		object = (net.madicorp.autodoc.models.Object) object;
		
		try {
			
			sf.getCurrentSession().delete(object);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<net.madicorp.autodoc.models.Object> findByUser(User user) {
		List<net.madicorp.autodoc.models.Object> objects = null;
		
		try {
			objects = sf.getCurrentSession().createQuery("FROM Object o WHERE o.categorie.user = :user").setParameter("user", user).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<net.madicorp.autodoc.models.Object> findByObjectType(
			ObjectType objectType) {
List<net.madicorp.autodoc.models.Object> objects = null;
		
		try {
			objects = sf.getCurrentSession().createQuery("FROM Object o WHERE o.objectType = :objectType").setParameter("objectType", objectType).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objects;
	}

	
}
