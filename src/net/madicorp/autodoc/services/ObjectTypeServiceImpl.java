/**
 * 
 */
package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.ObjectType;
import net.madicorp.autodoc.models.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sega
 *
 */
@Service("ObjectType")
@Transactional
public class ObjectTypeServiceImpl implements ObjectTypeService {

	
	@Autowired
	SessionFactory sf;
	
	/* (non-Javadoc)
	 * @see net.econostic.autodoc.services.AppService#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjectType> findAll() {
		List<ObjectType> objectTypes = null;
		
		try {
			objectTypes = sf.getCurrentSession().createQuery("FROM ObjectType ot").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objectTypes;
	}

	/* (non-Javadoc)
	 * @see net.econostic.autodoc.services.AppService#findById(java.lang.Integer)
	 */
	@Override
	public ObjectType findById(Integer id) {
		ObjectType objectType = null;
		try {
			
			objectType = (ObjectType) sf.getCurrentSession().get(ObjectType.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectType;
	}

	/* (non-Javadoc)
	 * @see net.econostic.autodoc.services.AppService#findByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjectType> findByName(String name) {
		List<ObjectType> objectTypes = null;
		
		try {
			objectTypes = sf.getCurrentSession().createQuery("FROM ObjectType ot WHERE ot.nomObjectType = :name").setParameter("name", name).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objectTypes;
	}

	/* (non-Javadoc)
	 * @see net.econostic.autodoc.services.AppService#save(java.lang.Object)
	 */
	@Override
	public boolean save(Object objectType) {
		boolean saved = false;
		
		objectType = (ObjectType) objectType;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(objectType);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	/* (non-Javadoc)
	 * @see net.econostic.autodoc.services.AppService#delete(java.lang.Object)
	 */
	@Override
	public boolean delete(Object objectType) {
		boolean deleted = false;
		objectType = (ObjectType) objectType;
		
		try {
			
			sf.getCurrentSession().delete(objectType);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjectType> findByCategorie(Categorie categorie) {
		List<ObjectType> objectTypes = null;
		
		try {
			objectTypes = sf.getCurrentSession().createQuery("FROM ObjectType ot WHERE ot.categorie = :categorie").setParameter("categorie", categorie).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objectTypes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjectType> findByUser(User user) {
		
		List<ObjectType> objectTypes = null;
		
		try {
			objectTypes = sf.getCurrentSession().createQuery("FROM ObjectType ot WHERE ot.categorie.user = :user").setParameter("user", user).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return objectTypes;
	}

}
