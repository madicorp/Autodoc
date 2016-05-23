/**
 * 
 */
package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.User;

/**
 * @author sega
 *
 */
public interface CategorieService extends AppService {
	
	public List<Categorie> findByUser (User user);

}
