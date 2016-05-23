/**
 * 
 */
package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.Template;
import net.madicorp.autodoc.models.User;

/**
 * @author sega
 *
 */
public interface TemplateService extends AppService {
	
	
	public List<Template> findByUser(User user);
	public List<Template> findByUserAndType(User user, String type);
	public List<Template> findByCategorieAndType(Categorie categorie, String type);

}
