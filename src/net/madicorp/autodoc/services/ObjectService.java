package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.Object;
import net.madicorp.autodoc.models.ObjectType;
import net.madicorp.autodoc.models.User;

public interface ObjectService extends AppService {

	public List<Object> findByUser(User user);
	public List<Object> findByObjectType(ObjectType objectType) ;
		
	
	
}
