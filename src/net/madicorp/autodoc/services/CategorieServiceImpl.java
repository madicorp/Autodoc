package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Diop Sega
 *
 */
@Service("Categorie")
@Transactional
public class CategorieServiceImpl implements CategorieService {
	
	@Autowired
	private  SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> findAll() {
		List<Categorie> categories = null;
		
		try {
			categories = sf.getCurrentSession().createQuery("FROM Categorie c").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return categories;
	}

	@Override
	public Categorie findById(Integer id) {
		Categorie categorie = null;
		try {
			
			categorie = (Categorie) sf.getCurrentSession().get(Categorie.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorie;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> findByName(String name) {
		
		List<Categorie> categories = null;
		
		try {
			categories = sf.getCurrentSession().createQuery("FROM Categorie c WHERE c.nomCategorie = :name").setParameter("name", name).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return categories;
	}

	@Override
	public boolean save(Object categorie) {
		boolean saved = false;
		
		categorie = (Categorie) categorie;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(categorie);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object categorie) {
		boolean deleted = false;
		categorie = (Categorie) categorie;
		
		try {
			
			sf.getCurrentSession().delete(categorie);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> findByUser(User user) {
List<Categorie> categories = null;
		
		try {
			categories = sf.getCurrentSession().createQuery("FROM Categorie c WHERE c.user = :user").setParameter("user", user).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return categories;
	}

}
