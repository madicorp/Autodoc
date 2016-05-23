package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.Template;
import net.madicorp.autodoc.models.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Diop Sega
 *
 */
@Service("Template")
@Transactional
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private  SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> findAll() {
		List<Template> templates = null;
		
		try {
			templates = sf.getCurrentSession().createQuery("FROM Template t").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return templates;
	}

	@Override
	public Template findById(Integer id) {
		Template template = null;
		try {
			
			template = (Template) sf.getCurrentSession().get(Template.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> findByName(String name) {
		
		List<Template> templates = null;
		
		try {
			templates = sf.getCurrentSession().createQuery("FROM Template t WHERE t.nomTemplate = :name").setParameter("name", name).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return templates;
	}

	@Override
	public boolean save(Object template) {
		boolean saved = false;
		
		template = (Template) template;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(template);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object template) {
		boolean deleted = false;
		template = (Template) template;
		
		try {
			
			sf.getCurrentSession().delete(template);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> findByUserAndType(User user, String type) {
		
		List<Template> templates = null;
		
		try {
			templates = sf.getCurrentSession().createQuery("FROM Template t WHERE t.categorie.user = :user AND t.type = :type").setParameter("user", user).setParameter("type", type).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return templates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> findByUser(User user) {
		
		List<Template> templates = null;
		
		try {
			templates = sf.getCurrentSession().createQuery("FROM Template t WHERE t.categorie.user = :user").setParameter("user", user).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return templates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> findByCategorieAndType(Categorie categorie,
			String type) {
		List<Template> templates = null;
		
		try {
			templates = sf.getCurrentSession().createQuery("FROM Template t WHERE t.categorie = :categorie AND t.type = :type").setParameter("categorie", categorie).setParameter("type", type).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return templates;
	}

}
