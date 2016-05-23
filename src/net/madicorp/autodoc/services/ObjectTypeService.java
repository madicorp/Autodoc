/**
 * 
 */
package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.ObjectType;
import net.madicorp.autodoc.models.User;

/**
 * @author sega
 *
 */
public interface ObjectTypeService extends AppService {
	
	public List<ObjectType> findByCategorie (Categorie categorie);
	public List<ObjectType> findByUser (User user);
}
