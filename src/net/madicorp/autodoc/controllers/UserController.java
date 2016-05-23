package net.madicorp.autodoc.controllers;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.madicorp.autodoc.classes.ClassPath;
import net.madicorp.autodoc.classes.Dates;
import net.madicorp.autodoc.classes.FileEditer;
import net.madicorp.autodoc.classes.Functions;
import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.Document;
import net.madicorp.autodoc.models.Gabarit;
import net.madicorp.autodoc.models.ObjectType;
import net.madicorp.autodoc.models.Template;
import net.madicorp.autodoc.models.User;
import net.madicorp.autodoc.models.User_Document;
import net.madicorp.autodoc.services.CategorieService;
import net.madicorp.autodoc.services.DocumentService;
import net.madicorp.autodoc.services.GabaritService;
import net.madicorp.autodoc.services.ObjectService;
import net.madicorp.autodoc.services.ObjectTypeService;
import net.madicorp.autodoc.services.TemplateService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Diop Sega
 *
 */
@Controller
public class UserController {
	

	private ClassPathXmlApplicationContext context;
	private SecurityContext securityContext;
	 private Authentication authentication;
	 private UserService userService;
	 private User_DocumentService user_DocumentService;
	 private CategorieService categorieService;
	 private GabaritService gabaritService;
	 private TemplateService templateService;
	 private DocumentService documentService;
	 private ObjectTypeService objectTypeService;
	 private ObjectService objectService;
	 private ModelAndView model;
	 
	 
	
	 
	 
	 
	 
//	 @SuppressWarnings("unchecked")
//		@RequestMapping(value = { "/modules",}, method = RequestMethod.POST)
//		public ModelAndView moduleAdd(HttpServletRequest request,@RequestParam(required = false) String categorieNew,@RequestParam String modu, @RequestParam(required = false) String categ ) {
//		 
//		 	securityContext = SecurityContextHolder.getContext();
//		    authentication = securityContext.getAuthentication();
//			context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
//		    userService = (UserService) context.getBean("User");
//		    User user = new User();
//		    Object principal = authentication.getPrincipal();  
//		    if (principal instanceof UserDetails) {
//		    	UserDetails u = (UserDetails) principal;
//		    	user = (User) userService.findByName(u.getUsername()).get(0);
//		    }
//		 
//		 	
//		 	context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
//			categorieService = (CategorieService) context.getBean("Categorie");
//			
//			Categorie categorie;
//			
//			if(categorieNew =="")
//			{
//				categorie = (Categorie) categorieService.findById(Integer.parseInt(categ));
//			}
//			else
//			{
//				categorie = new Categorie(categorieNew);
//				categorie.setUser(user);
//				categorieService.save(categorie);
//			}
//			
//			Module module = new Module(user, categorie, modu, Dates.date());
//			
//			categorieService.save(module);
//			
//			List<Categorie> categories = (List<Categorie>) categorieService.findAll();
//			List<Module> modules = (List<Module>) categorieService.findByUser(user);
//		    model = new ModelAndView();
//			model.addObject("categories", categories);
//			model.addObject("categories", modules);
//			model.addObject("modulePage", "active");
//			model.setViewName("user/modules");
//			context.close();
//			return model;
//
//		}


	@RequestMapping(value = { "/gabarit",}, method = RequestMethod.GET)
	public ModelAndView gabaritPage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie");
		gabaritService = (GabaritService) context.getBean("Gabarit");
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories = (List<Categorie>) categorieService.findByUser(user);
		List<Gabarit> gabarits = gabaritService.findByUser(user);
		model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("gabarits", gabarits);
		model.addObject("gabaritPage", "active");
		model.setViewName("user/gabarit");
		context.close();
		return model;

	}
	
	
	@RequestMapping(value = {"/add-object-type",}, method = RequestMethod.GET)
	public ModelAndView addObjectTypePage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie"); 
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories =  categorieService.findByUser(user);
		model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("addObjectTypePage", "active");
		model.setViewName("user/addobjecttype");
		context.close();
		return model;
		
	}
	
	@RequestMapping(value = {"/add-object-type",}, method = RequestMethod.POST)
	public ModelAndView addObjectType(@RequestParam int categorie,  @RequestParam String objettypename ,@RequestParam String args,HttpServletRequest request) {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie"); 
		objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories =  categorieService.findByUser(user);
		
		Categorie categ = (Categorie) categorieService.findById(categorie);
		
		ObjectType objectType = new ObjectType(objettypename, args, categ);
		objectType.setVisibleFields(args);
		
		boolean created = objectTypeService.save(objectType);
		model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("addObjectTypePage", "active");
		model.addObject("created", created);
		model.setViewName("user/addobjecttype");
		context.close();
		return model;
		
	}
	
	@RequestMapping(value = {"/list-object-type",}, method = RequestMethod.GET)
	public ModelAndView listObjectTypePage(@RequestParam(required = false, defaultValue = "0") int item) {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
		categorieService = (CategorieService) context.getBean("Categorie"); 
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    List<Categorie> categories =  categorieService.findByUser(user);
	    List<ObjectType> objectTypes = null;
	    if(item != 0)
	    {
	    	objectTypes = objectTypeService.findByCategorie((Categorie) categorieService.findById(item));
	    }
	    else{
	    	objectTypes = objectTypeService.findByUser(user);
	    }
		model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("objectTypes", objectTypes);
		model.addObject("listObjectTypePage", "active");
		model.setViewName("user/listobjecttype");
		context.close();
		return model;
		
	}
	
	@RequestMapping(value = {"/add-object",}, method = RequestMethod.GET)
	public ModelAndView addObjectPage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie"); 
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories = categorieService.findByUser(user);
		model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("addObjectPage", "active");
		model.setViewName("user/addobject");
		context.close();
		return model;
		
	}
	
	@RequestMapping(value = {"/add-object",}, method = RequestMethod.POST)
	public ModelAndView addObject(@RequestParam (required = false, defaultValue = "0") int categorie, @RequestParam int objecttype, @RequestParam String objetname, @RequestParam String args) {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie"); 
		objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
		objectService = (ObjectService) context.getBean("Object"); 
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    
	   
		List<Categorie> categories = categorieService.findByUser(user);
		
		
		
		ClassPath classPath = new ClassPath();
	    String path = classPath.getWebInfPath();
	   
	    
	    ArrayList<String> argsList = new ArrayList<String>();
		String [] targs= args.split("/___/") ;
		String [] fields = objetname.split("/___/");
		String filename ="";
		
		for(String arg : targs)
		{
			argsList.add(arg);
			for (String field : fields)
			{
				String [] targ = arg.split("/__/");
				if(field.contains(targ[0]))
				{
					filename +=targ[1]+"_";
				}
			}
			
		}
		filename += Dates.date().getTime()+".txt";
		 path+="BACK-SPACE/"+user.getLogin()+"/Gabarit/"+filename;
		    File file = new File(path);
		    FileEditer fileEditer = new FileEditer();
		    fileEditer.setFile(file);
		fileEditer.setArgs(argsList);
	    boolean saved = fileEditer.saveFile();
	    ObjectType objtType = (ObjectType) objectTypeService.findById(objecttype);
	    if(categorie == 0)
	    {
	    	categorie = objtType.getCategorie().getIdCategorie();
	    }
	    Categorie categ = (Categorie) categorieService.findById(categorie);
		net.madicorp.autodoc.models.Object object = new net.madicorp.autodoc.models.Object();
		object.setNomGabarit(filename);
		object.setCategorie(categ);
		object.setObjectType(objtType);
	    if(saved)
	    {
	    	saved = objectService.save(object);
	    }
		
		model.addObject("categories", categories);
		model.addObject("created", saved);
		model.addObject("addObjectPage", "active");
		model.setViewName("user/addobject");
		context.close();
		return model;
		
	}
	
	@RequestMapping(value = {"/list-object",}, method = RequestMethod.GET)
	public ModelAndView listObjectPage(@RequestParam int item) {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
		objectService = (ObjectService) context.getBean("Object"); 
		categorieService = (CategorieService) context.getBean("Categorie"); 
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    List<Categorie> categories =  categorieService.findByUser(user);
	    List<net.madicorp.autodoc.models.Object> objects = null;
	    if(item != 0)
	    {
	    	objects = objectService.findByObjectType((ObjectType) objectTypeService.findById(item));
	    }
	    else{
	    	objects = objectService.findByUser(user);
	    }
	    
	    Map<net.madicorp.autodoc.models.Object,ArrayList<String>> fullObjects = new HashMap<net.madicorp.autodoc.models.Object, ArrayList<String>>();
	    
	    ClassPath classPath = new ClassPath();
	    String path = classPath.getWebInfPath();
		 path+="BACK-SPACE/"+user.getLogin()+"/Gabarit/";
		 FileEditer fileEditer = new FileEditer();
		 for (net.madicorp.autodoc.models.Object object : objects) {
			
			 fileEditer.setFile(new File(path+object.getNomGabarit()));
			 fullObjects.put(object, fileEditer.readFile());
			 
		}
		   
		    
		model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("objects", objects);
		model.addObject("fullObjects",fullObjects);
		model.addObject("object_type",(ObjectType) objectTypeService.findById(item));
		model.addObject("listObjectPage", "active");
		model.setViewName("user/listobject");
		context.close();
		return model;
		
	}
	

	
	
	@RequestMapping(value = { "/template-doc",}, method = RequestMethod.GET)
	public ModelAndView templateDocPage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie");
		templateService = (TemplateService) context.getBean("Template");
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories = (List<Categorie>) categorieService.findByUser(user);
		List<Template> templates = templateService.findByUserAndType(user, "Docx");
	    model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("templates", templates);
		model.addObject("templateDocPage", "active");
		model.setViewName("user/templatedoc");
		context.close();
		return model;

	}
	

	
	
	
	@RequestMapping(value = { "/template-xls",}, method = RequestMethod.GET)
	public ModelAndView templateXlsPage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie");
		templateService = (TemplateService) context.getBean("Template");
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories = (List<Categorie>) categorieService.findByUser(user);
		List<Template> templates = templateService.findByUserAndType(user, "Xls");
	    model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("templates", templates);
		model.addObject("templateXlsPage", "active");
		model.setViewName("user/templatexls");
		context.close();
		return model;

	}
	


	
	
	@RequestMapping(value = { "/template-ppt",}, method = RequestMethod.GET)
	public ModelAndView templatePptPage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie");
		templateService = (TemplateService) context.getBean("Template");
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories =  categorieService.findByUser(user);
		List<Template> templates = templateService.findByUserAndType(user, "Pptx");
	    model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("templates", templates);
		model.addObject("templatePptPage", "active");
		model.setViewName("user/templateppt");
		context.close();
		return model;

	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/documents",}, method = RequestMethod.GET)
	public ModelAndView documentPage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie");
		documentService = (DocumentService) context.getBean("Document");
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Document> documents = documentService.findByUser(user);
		List<Categorie> categories = categorieService.findByUser(user);
		List<User> users = (List<User>) userService.findAll();
		
	    model = new ModelAndView();
	    model.addObject("users",users);
	    model.addObject("me", user.getIdUser());
		model.addObject("categories", categories);
		model.addObject("documents", documents);
		model.addObject("documentPage", "active");
		model.setViewName("user/documents");
		context.close();
		return model;

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/shared-documents",}, method = RequestMethod.GET)
	public ModelAndView sharedDocumentPage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie");
		documentService = (DocumentService) context.getBean("Document");
		user_DocumentService = (User_DocumentService) context.getBean("User_Document");
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Document> documents = (List<Document>) documentService.findAll();
		List<Categorie> categories = categorieService.findByUser(user);
		List<User_Document> user_Documents = user_DocumentService.findByUser(user);
		Map<Document,Date> sharedDocuments = new HashMap<Document, Date>();
		
		for (Document document : documents) {
			for (User_Document user_Document : user_Documents){
				if(document.getIdDocument() == user_Document.getDocument_idDocument() )
				{
					sharedDocuments.put(document, user_Document.getSharedDate());
				}
			}
			
		}
		
		
	    model = new ModelAndView();
		model.addObject("documents", sharedDocuments);
		model.addObject("categories", categories);
		model.addObject("documentPage", "active");
		model.setViewName("user/shareddocument");
		context.close();
		return model;

	}
	
	
	
	
	
	
	@RequestMapping(value = { "/generate",}, method = RequestMethod.GET)
	public ModelAndView generatePage() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		categorieService = (CategorieService) context.getBean("Categorie"); 
		gabaritService = (GabaritService) context.getBean("Gabarit");
		templateService = (TemplateService) context.getBean("Template");
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		userService = (UserService) context.getBean("User");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
		List<Categorie> categories =  categorieService.findByUser(user);
		List<Gabarit> gabarits = (List<Gabarit>) gabaritService.findByUser(user);
		List<Template> templates = templateService.findByUser(user);
	    model = new ModelAndView();
		model.addObject("categories", categories);
		model.addObject("gabarits", gabarits);
		model.addObject("templates", templates);
		model.addObject("generatePage", "active");
		model.setViewName("user/generate");
		context.close();
		return model;

	}
	
	@RequestMapping(value = { "/generate",}, method = RequestMethod.POST)
	public ModelAndView generateDoc(HttpServletRequest request,
			@RequestParam (required = false, defaultValue = "0") int categorie,
			@RequestParam String docName,
			@RequestParam int templateId,
			@RequestParam int gabaritId) {
		
		
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		gabaritService = (GabaritService) context.getBean("Gabarit");
		templateService = (TemplateService) context.getBean("Template");
		documentService = (DocumentService) context.getBean("Document");
		categorieService = (CategorieService) context.getBean("Categorie");
		Gabarit gab = (Gabarit) gabaritService.findById(gabaritId);
		Template temp = (Template) templateService.findById(templateId);
		Categorie mod = null;
		if(categorie != 0)
		{
			 mod = (Categorie) categorieService.findById(categorie);
		}
		else{
			 mod = temp.getCategorie();
		}
		
		
		
		
		
		
		 	String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/";
		    
		    String textFilePath = path+"Gabarit/"+gab.getNomGabarit();
		    String templatePath = path+"Template/"+temp.getType()+"/"+temp.getNomTemplate();
		    docName +="_"+Dates.date().getTime();
		    String targetName = path+"Doc/"+docName;
		    
		    Functions functions = new Functions();
		   boolean created = functions.generateDocument(textFilePath, templatePath, targetName);
		   
		   
		   if(created)
		   {
			   String[] bits = temp.getNomTemplate().split("\\.");
			   docName+= "."+bits[bits.length-1];
			   
			   Document document = new Document(mod, temp, gab, docName);
			   created = documentService.save(document);
		   }
		   
		   
		   securityContext = SecurityContextHolder.getContext();
		    authentication = securityContext.getAuthentication();
			userService = (UserService) context.getBean("User");
		    User user = new User();
		    Object principal = authentication.getPrincipal();  
		    if (principal instanceof UserDetails) {
		    	UserDetails u = (UserDetails) principal;
		    	user = (User) userService.findByName(u.getUsername()).get(0);
		    }
		
			List<Categorie> categories = categorieService.findByUser(user);
			List<Gabarit> gabarits = (List<Gabarit>) gabaritService.findByUser(user);
			List<Template> templates = templateService.findByUser(user);
		    model = new ModelAndView();
			model.addObject("categories", categories);
			model.addObject("gabarits", gabarits);
			model.addObject("templates", templates);
			model.addObject("created", created);
			model.addObject("generatePage", "active");
			model.setViewName("user/generate");
			context.close();
			return model;

	}

}
