/**
 * 
 */
package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Document;
import net.madicorp.autodoc.models.User;

/**
 * @author sega
 *
 */
public interface DocumentService extends AppService {
	
	public List<Document> findByUser(User user);

}
