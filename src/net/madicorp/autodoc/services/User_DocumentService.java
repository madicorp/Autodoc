/**
 * 
 */
package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Document;
import net.madicorp.autodoc.models.User;
import net.madicorp.autodoc.models.User_Document;
import net.madicorp.autodoc.models.User_Document_PK;

/**
 * @author sega
 *
 */
public interface User_DocumentService extends AppService {
	
	
	public User_Document findById(User_Document_PK user_Document_PK);
	public List<User_Document> findByUser(User user);
	public List<User_Document> findByDocument(Document document);

}
