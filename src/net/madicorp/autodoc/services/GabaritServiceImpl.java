package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Gabarit;
import net.madicorp.autodoc.models.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Diop Sega
 *
 */
@Service("Gabarit")
@Transactional
public class GabaritServiceImpl implements GabaritService {

	@Autowired
	private  SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Gabarit> findAll() {
		List<Gabarit> gabarits = null;
		
		try {
			gabarits = sf.getCurrentSession().createQuery("FROM Gabarit g").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return gabarits;
	}

	@Override
	public Gabarit findById(Integer id) {
		Gabarit gabarit = null;
		try {
			
			gabarit = (Gabarit) sf.getCurrentSession().get(Gabarit.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gabarit;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gabarit> findByName(String name) {
		
		List<Gabarit> gabarits = null;
		
		try {
			gabarits = sf.getCurrentSession().createQuery("FROM Gabarit g WHERE g.nomGabarit = :name").setParameter("name", name).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return gabarits;
	}

	@Override
	public boolean save(Object gabarit) {
		boolean saved = false;
		
		gabarit = (Gabarit) gabarit;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(gabarit);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object gabarit) {
		boolean deleted = false;
		gabarit = (Gabarit) gabarit;
		
		try {
			
			sf.getCurrentSession().delete(gabarit);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gabarit> findByUser(User user) {
		List<Gabarit> gabarits = null;
		
		try {
			gabarits = sf.getCurrentSession().createQuery("FROM Gabarit g WHERE g.categorie.user = :user").setParameter("user", user).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return gabarits;
	}


}
