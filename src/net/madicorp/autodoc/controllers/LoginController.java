package net.madicorp.autodoc.controllers;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.madicorp.autodoc.classes.ClassPath;
import net.madicorp.autodoc.classes.FileEditer;
import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.User;
import net.madicorp.autodoc.models.User_Document;
import net.madicorp.autodoc.services.CategorieService;
import net.madicorp.autodoc.services.UserService;
import net.madicorp.autodoc.services.User_DocumentService;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private ClassPathXmlApplicationContext context;
	 private SecurityContext securityContext;
	 private Authentication authentication;
	 private CategorieService categorieService;
	 private UserService userService;
	 private User_DocumentService user_DocumentService;
	 private ModelAndView model;
	 private String contextPath = "net/madicorp/autodoc/config/autodoc-context.xml";
	 
	 
	 @RequestMapping(value = { "/login**",}, method = RequestMethod.GET)
		public ModelAndView loginePage(HttpServletRequest request) {
		 
		 HttpSession usersession = request.getSession();
		    model = new ModelAndView();
		    if(usersession.getAttribute("name") != null)
		    {
		    	model.setViewName("redirect:/");
		    }
		    else{
		    	model.setViewName("login");
		    }
		  
			
			return model;
	 }

	@SuppressWarnings("serial")
	@RequestMapping(value = { "/","/home**",}, method = RequestMethod.GET)
	public ModelAndView userHomePage(HttpServletRequest request) {
		
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		context = new ClassPathXmlApplicationContext(contextPath);
		categorieService = (CategorieService) context.getBean("Categorie");
	    userService = (UserService) context.getBean("User");
	    user_DocumentService = (User_DocumentService) context.getBean("User_Document");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    HttpSession usersession = request.getSession();
	    usersession.setAttribute("name", user.getLogin());
	    ClassPath classPath = new ClassPath();
	    final String path = classPath.getWebInfPath()+"BACK-SPACE/"+user.getLogin()+"/";
	    final FileEditer fileEditer = new FileEditer();
	    
	    ArrayList<String> cloud = null;
	    ArrayList<String> document = null;
	    ArrayList<String> template = null;
	    ArrayList<String> gabarit = null;
		List<Categorie> categories = categorieService.findByUser(user);
	    if((new File(path+"Cloud")).exists())
	    {
	    	cloud = new ArrayList<String> (){{
	    		add(fileEditer.getFolderItems(
	    				new File(path+"Cloud")
	    				)+"");
	    		add(fileEditer.getFolderLength(
	    				new File(path+"Cloud")
	    				)+"");
	    	}};
	    }
	    

	    if((new File(path+"Doc")).exists())
	    {
	    	document = new ArrayList<String> (){{
	    		add(fileEditer.getFolderItems(
	    				new File(path+"Doc")
	    				)+"");
	    		add(fileEditer.getFolderLength(
	    				new File(path+"Doc")
	    				)+"");
	    	}};
	    }

	    if((new File(path+"Gabarit")).exists())
	    {
	    	gabarit = new ArrayList<String> (){{
	    		add(fileEditer.getFolderItems(
	    				new File(path+"Gabarit")
	    				)+"");
	    		add(fileEditer.getFolderLength(
	    				new File(path+"Gabarit")
	    				)+"");
	    	}};
	    }
	    

	    if((new File(path+"Template")).exists())
	    {
	    	template = new ArrayList<String> (){{
	    		add(fileEditer.getFolderItems(
	    				new File(path+"Template")
	    				)+"");
	    		add(fileEditer.getFolderLength(
	    				new File(path+"Template")
	    				)+"");
	    	}};
	    }
	    
	    
	    	
	    List<User_Document> user_Documents = user_DocumentService.findByUser(user);
	   
	    
	    model = new ModelAndView();
		model.addObject("name", user.getNomUser() +" "+user.getPrenomUser());
		model.addObject("role", user.getRole().getCodeRole());
		model.addObject("cloud", cloud);
		model.addObject("document", document);
		model.addObject("gabarit", gabarit);
		model.addObject("template", template);
		model.addObject("categories", categories);
		model.addObject("owned", user.getUser_Documents().size());
		model.addObject("shared", user_Documents.size());
		model.addObject("homePage", "active");
		model.setViewName("user/home");
		return model;
 
	}
	
	@RequestMapping(value = { "/admin",}, method = RequestMethod.GET)
	public ModelAndView adminHomePage() {
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		context = new ClassPathXmlApplicationContext(contextPath);
	    userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    
	    model = new ModelAndView();
		model.addObject("name", user.getNomUser() +" "+user.getPrenomUser());
		model.addObject("role", user.getRole().getCodeRole());
		model.setViewName("admin/home");
		context.close();
		return model;
 
	}

}
