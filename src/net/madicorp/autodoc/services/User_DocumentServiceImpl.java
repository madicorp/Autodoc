package net.madicorp.autodoc.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.madicorp.autodoc.models.Document;
import net.madicorp.autodoc.models.User;
import net.madicorp.autodoc.models.User_Document;
import net.madicorp.autodoc.models.User_Document_PK;

/**
 * @author Diop Sega
 *
 */
@Service("User_Document")
@Transactional
public class User_DocumentServiceImpl implements User_DocumentService {

	@Autowired
	SessionFactory sf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User_Document> findAll() {
	List<User_Document> user_documents = null;
		
		try {
			user_documents = sf.getCurrentSession().createQuery("FROM User_Document ud").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return user_documents;
	}

	@Override
	public Object findById(Integer id) {
		return null;
	}

	@Override
	public List<?> findByName(String name) {
		return null;
	}

	@Override
	public boolean save(Object user_document) {
		boolean saved = false;
		
		user_document = (User_Document) user_document;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(user_document);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object user_document) {
		boolean deleted = false;
		
		user_document = (User_Document) user_document;
		
		try {
			
			sf.getCurrentSession().delete(user_document);
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleted;	
	}

	@Override
	public User_Document findById(User_Document_PK user_Document_PK) {
		User_Document user_document = null;
		try {
			
			user_document = (User_Document) sf.getCurrentSession().get(User_Document.class, user_Document_PK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_document;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User_Document> findByUser(User user) {
	List<User_Document> user_documents = null;
		
		try {
			user_documents = sf.getCurrentSession().createQuery("FROM User_Document ud WHERE ud.user_idUser = :idUser").setParameter("idUser", user.getIdUser()).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return user_documents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User_Document> findByDocument(Document document) {
		List<User_Document> user_documents = null;
		
		try {
			user_documents = sf.getCurrentSession().createQuery("FROM User_Document ud WHERE ud.document_idDocument = :idDocument").setParameter("idDocument", document.getIdDocument()).list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return user_documents;
	}

}
