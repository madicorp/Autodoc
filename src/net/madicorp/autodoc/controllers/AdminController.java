package net.madicorp.autodoc.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.madicorp.autodoc.classes.Hash;
import net.madicorp.autodoc.models.Role;
import net.madicorp.autodoc.models.User;
import net.madicorp.autodoc.services.RoleService;
import net.madicorp.autodoc.services.UserService;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	
	private ClassPathXmlApplicationContext context;
	 private UserService userService;
	 private RoleService roleService;
	 private ModelAndView model;
	 private String contextPath = "net/madicorp/autodoc/config/autodoc-context.xml";

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/admin/add-user",}, method = RequestMethod.GET)
	public ModelAndView addUserPage() {
		context = new ClassPathXmlApplicationContext(contextPath);
		roleService = (RoleService) context.getBean("Role");
		List<Role> roles = (List<Role>) roleService.findAll();
	    model = new ModelAndView();
		model.addObject("roles", roles);
		model.addObject("adduserPage", "active");
		model.setViewName("admin/adduser");
		context.close();
		return model;

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/admin/add-user",}, method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request) throws ServiceUnavailableException, NoSuchAlgorithmException {
		
		
		context = new ClassPathXmlApplicationContext(contextPath);
	    userService = (UserService) context.getBean("User");
	    roleService = (RoleService) context.getBean("Role");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		password = Hash.hash(password);
		String role = request.getParameter("Role");
		
		User user = new User((Role) roleService.findByName(role).get(0), email, password, nom, prenom);
	    boolean added = userService.save(user);
	    List<Role> roles = (List<Role>) roleService.findAll();
	    model = new ModelAndView();
		model.addObject("added", added);
		model.addObject("roles", roles);
		model.addObject("adduserPage", "active");
		model.setViewName("admin/adduser");
		context.close();
		return model;

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/admin/list-user",}, method = RequestMethod.GET)
	public ModelAndView listUserPage(HttpServletRequest request) {
		context = new ClassPathXmlApplicationContext(contextPath);
		userService = (UserService) context.getBean("User");
		 roleService = (RoleService) context.getBean("Role");
		String name = "";
	    HttpSession usersession = request.getSession();
	    name = usersession.getAttribute("name").toString();
		List<User> users = (List<User>) userService.findAll();
		 List<Role> roles = (List<Role>) roleService.findAll();
	    model = new ModelAndView();
		model.addObject("users", users);
		model.addObject("roles", roles);
		model.addObject("me", name);
		model.addObject("listuserPage", "active");
		model.setViewName("admin/listuser");
		context.close();
		return model;

	}
	
	@RequestMapping(value = { "/admin/edit-user",}, method = RequestMethod.PUT)
	public ModelAndView editUserPage(HttpServletRequest request,@RequestParam int idUser, @RequestParam String nom, @RequestParam String prenom, @RequestParam String login, @RequestParam(required = false) String password, @RequestParam String role) throws ServiceUnavailableException, NoSuchAlgorithmException {
		context = new ClassPathXmlApplicationContext(contextPath);
		userService = (UserService) context.getBean("User");
		roleService = (RoleService) context.getBean("Role");
		Role role2 = (Role) roleService.findByName(role).get(0);
		User user = (User) userService .findById(idUser);
		user.setLogin(login);
		user.setNomUser(nom);
		user.setPrenomUser(prenom);
		user.setRole(role2);
		if(password !="")
		{
			user.setPassword(Hash.hash(password));
		}
		boolean updated = userService.save(user);
	    model = new ModelAndView();
		model.addObject("message",updated );
		model.setViewName("response/rep");
		context.close();
		return model;

	}

}
