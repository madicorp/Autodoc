package net.madicorp.autodoc.services;

import java.util.List;

/**
 * @author Diop Sega
 *
 */
public  interface AppService {
	
	public  List<?> findAll();
	public  Object findById(Integer id);
	public  List<?> findByName (String name);
	public  boolean save(Object object);
	public  boolean delete (Object object);

}
