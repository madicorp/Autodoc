/**
 * 
 */
package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Gabarit;
import net.madicorp.autodoc.models.User;

/**
 * @author sega
 *
 */
public interface GabaritService extends AppService {
	
	public List<Gabarit> findByUser(User user);

}
