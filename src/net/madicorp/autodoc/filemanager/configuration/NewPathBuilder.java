package net.madicorp.autodoc.filemanager.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import net.madicorp.autodoc.classes.ClassPath;
import net.madicorp.autodoc.filemanager.configuration.DefaultPathBuilder;

/**
 * @author Diop Sega
 *
 */
@Controller
public class NewPathBuilder extends DefaultPathBuilder {
	
	@Override
	public String getBaseDir(HttpServletRequest request) {
		
	    String name = "";
	    HttpSession usersession = request.getSession();
	    name = usersession.getAttribute("name").toString();
	    ClassPath classPath = new ClassPath();
	    String path = classPath.getWebInfPath();
    	return path+"BACK-SPACE/"+name+"/";
    
	}
	
	@Override
	public String getBaseUrl(HttpServletRequest request) {
		 String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		return "/WEB-INF/BACK-SPACE/"+name+"/";
	}
}
