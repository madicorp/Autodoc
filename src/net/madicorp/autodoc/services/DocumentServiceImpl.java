package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Document;
import net.madicorp.autodoc.models.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Diop Sega
 *
 */
@Service("Document")
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private  SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> findAll() {
		List<Document> documents = null;
		
		try {
			documents = sf.getCurrentSession().createQuery("FROM Document d").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return documents;
	}

	@Override
	public Document findById(Integer id) {
		Document document = null;
		try {
			
			document = (Document) sf.getCurrentSession().get(Document.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> findByName(String name) {
		
		List<Document> documents = null;
		
		try {
			documents = sf.getCurrentSession().createQuery("FROM Document d WHERE d.nomDocument = :name").setParameter("name", name).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return documents;
	}

	@Override
	public boolean save(Object document) {
		boolean saved = false;
		
		document = (Document) document;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(document);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object document) {
		boolean deleted = false;
		document = (Document) document;
		
		try {
			
			sf.getCurrentSession().delete(document);
			
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> findByUser(User user) {
		
		List<Document> documents = null;
		
		try {
			documents = sf.getCurrentSession().createQuery("FROM Document d WHERE d.categorie.user = :user").setParameter("user", user).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return documents;
	}

}
