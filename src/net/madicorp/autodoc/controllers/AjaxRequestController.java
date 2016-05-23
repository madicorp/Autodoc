package net.madicorp.autodoc.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.madicorp.autodoc.classes.ClassPath;
import net.madicorp.autodoc.classes.Dates;
import net.madicorp.autodoc.classes.FileEditer;
import net.madicorp.autodoc.models.Categorie;
import net.madicorp.autodoc.models.Document;
import net.madicorp.autodoc.models.Gabarit;
import net.madicorp.autodoc.models.ObjectType;
import net.madicorp.autodoc.models.Template;
import net.madicorp.autodoc.models.User;
import net.madicorp.autodoc.models.User_Document;
import net.madicorp.autodoc.models.User_Document_PK;
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
public class AjaxRequestController {
	
	private ClassPathXmlApplicationContext context;
	 private SecurityContext securityContext;
	 private Authentication authentication;
	 private UserService userService;
	 private CategorieService categorieService;
	 private User_DocumentService user_DocumentService;
	 private GabaritService gabaritService;
	 private TemplateService templateService;
	 private DocumentService documentService;
	 private ObjectTypeService objectTypeService;
	 private ObjectService objectService;
	 private ModelAndView model;
	 private String contextPath = "net/madicorp/autodoc/config/autodoc-context.xml";
	 
	
	 /**
	  * Categorie Ajax Requests Controls
	  */
	 
		@RequestMapping(value = { "/categorie",}, method = RequestMethod.PUT)
		public ModelAndView categorieCreate(@RequestParam String categName) {
			context = new ClassPathXmlApplicationContext(contextPath);
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
		    Categorie categorie = new Categorie(categName, user);
		    boolean created = categorieService.save(categorie);
		    model = new ModelAndView();
		    model.addObject("message", created);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
	 
	 
	 /**
	  * END Categorie Ajax Requests Controls
	  */
	 
	 /**
	  * Cloud Ajax Requests Controls
	  */
	 
		@RequestMapping(value = { "/cloud-space",}, method = RequestMethod.PUT)
		public ModelAndView cloudCreateFolder(HttpServletRequest request,@RequestParam String currentFolder , @RequestParam String NewFolderName) {
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/Cloud"+currentFolder+NewFolderName;
		    
		    boolean created =false;
			File file = new File(path);
			if(!file.exists())
			{
				created =file.mkdirs();
				
			}
			
		    
		    model = new ModelAndView();
		    model.addObject("message", created);
			model.setViewName("response/rep");
			return model;
	 
		}
		
		@RequestMapping(value = { "/cloud-space-del",}, method = RequestMethod.PUT)
		public ModelAndView cloudDeleteFolder(HttpServletRequest request,@RequestParam String currentFolder , @RequestParam String files) {
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/Cloud"+currentFolder;
		    
		    boolean deleted =false;
		    
		    String [] tFiles = files.split(",");
		    
		    for(String file : tFiles)
		    {
		    	deleted = (new File(path+file)).delete();
		    }
			
		    
		    model = new ModelAndView();
		    model.addObject("message", deleted);
			model.setViewName("response/rep");
			return model;
	 
		}
	 
		 /**
		  * END Cloud Ajax Requests Controls
		  */
		
		 /**
		  * Document Ajax Requests Controls
		  */
		
		@RequestMapping(value = { "/shared-documents-dowload",}, method = RequestMethod.GET)
		public void sharedDocumentDownload(HttpServletRequest request, HttpServletResponse response,@RequestParam int documentref) throws ServletException, IOException {
			context = new ClassPathXmlApplicationContext(contextPath);
			documentService = (DocumentService) context.getBean("Document");
			securityContext = SecurityContextHolder.getContext();
		    authentication = securityContext.getAuthentication();
			userService = (UserService) context.getBean("User");
			user_DocumentService = (User_DocumentService) context.getBean("User_Document");
		    User user = new User();
		    Object principal = authentication.getPrincipal();  
		    if (principal instanceof UserDetails) {
		    	UserDetails u = (UserDetails) principal;
		    	user = (User) userService.findByName(u.getUsername()).get(0);
		    }
		    if(user_DocumentService.findById(new User_Document_PK(user.getIdUser(), documentref)) == null)
		    {
	            throw new ServletException("Acces Denied !");

		    }
			 Document document = (Document) documentService.findById(documentref);
			  user = document.getCategorie().getUser();
			 ClassPath classPath = new ClassPath();
			    String path = classPath.getWebInfPath();
			    path+="BACK-SPACE/"+user.getLogin()+"/Doc/"+document.getNomDocument();
		
			        File file = new File(path);
			        
			        if(!file.exists()){
			        		
			        	            throw new ServletException("File doesn't exists on server.");
			        	     
			        	        }
			        
			        		
			        	        
			        	        ServletContext ctx = request.getServletContext();
			        	        
			        	        InputStream fis = new FileInputStream(file);
			        	        
			        	        String mimeType = ctx.getMimeType(file.getAbsolutePath());
			        	        
			        	        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
			        	        
			        	        response.setContentLength((int) file.length());
			        	        
			        	        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			        	        
			        	 
			        	        
			        	        ServletOutputStream os       = response.getOutputStream();
			        	        
			        	        byte[] bufferData = new byte[1024];
			        	        
			        	        int read=0;
			        	        
			        	        while((read = fis.read(bufferData))!= -1){
			        	        	
			        	            os.write(bufferData, 0, read);
			        	            
			        	        }
			        	        
			        	        os.flush();
			        	        
			        	        os.close();
			        	        
			        	        fis.close();
			        	
			        	        System.out.println("File downloaded at client successfully");

		}
		
		@RequestMapping(value = { "/documents",}, method = RequestMethod.POST)
		public ModelAndView documentShare(HttpServletRequest request,@RequestParam int documentref, @RequestParam String users ) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			documentService = (DocumentService) context.getBean("Document");
			userService = (UserService) context.getBean("User");
			user_DocumentService = (User_DocumentService) context.getBean("User_Document");
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    User user = (User) userService.findByName(name).get(0);
			Document document = (Document) documentService.findById(documentref);
			List<User_Document> user_Documents = user_DocumentService.findByDocument(document);
			String[] usersref = users.split(",");
			Date date = Dates.date();
			boolean shared = false;
			if(user_Documents.size() > 0)
			{
			for(User_Document user_Document : user_Documents)
			{
				boolean unshare = true;
			for (String useref : usersref) {
				
				if(useref !="" && user_Document.getUser_idUser() != Integer.parseInt(useref))
				{
					shared=user_DocumentService.save(new User_Document(user,Integer.parseInt(useref), document.getIdDocument(), date));
					unshare = false;
				}
				else if(useref !="" && user_Document.getUser_idUser() == Integer.parseInt(useref))
				{
					unshare = false;
				}
				
				}
				if(unshare)
				{
					shared = user_DocumentService.delete(user_Document);
				}
			}
			}
			else{
				
				for (String useref : usersref) {
					if(useref !="" )
					{
						shared=user_DocumentService.save(new User_Document(user,Integer.parseInt(useref), document.getIdDocument(), date));
					}
				}
			}
			
			model = new ModelAndView();
			model.addObject("message", shared);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		@RequestMapping(value = { "/user-share",}, method = RequestMethod.PUT)
		public ModelAndView documentGetUserShare(@RequestParam int documentref) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			documentService = (DocumentService) context.getBean("Document");
			Document document = (Document) documentService.findById(documentref);
			String rep = "";
			for(User user : document.getUsers())
			{
				rep+= user.getLogin()+",";
			}
			model = new ModelAndView();
			model.addObject("message", rep);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		
		@RequestMapping(value = { "/documents",}, method = RequestMethod.PUT)
		public ModelAndView documentRename(HttpServletRequest request,@RequestParam int documentref, @RequestParam String documentname ) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			documentService = (DocumentService) context.getBean("Document");
			
			Document document = (Document) documentService.findById(documentref);
			String oldName = document.getNomDocument();
			String newName = documentname +"_" +Dates.date().getTime() +"." +oldName.split("\\.")[oldName.split("\\.").length -1];
			
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/";
		    
		    String textFilePath = path+"Doc/";
			File oldfile = new File(textFilePath+oldName);
			File newFile = new File(textFilePath+newName);
			boolean renamed =false;
			
			if (oldfile.renameTo(newFile)) {
				document.setNomDocument(newName);
				renamed = documentService.save(document);
				
			}
			model = new ModelAndView();
			model.addObject("message", renamed);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		@RequestMapping(value = { "/documents",}, method = RequestMethod.DELETE)
		public ModelAndView documentDelete(HttpServletRequest request,@RequestParam int documentref ) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			documentService = (DocumentService) context.getBean("Document");
			userService = (UserService) context.getBean("User");
			Document document = (Document) documentService.findById(documentref);
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/";
		    
		    String textFilePath = path+"Doc/"+document.getNomDocument();
			File file = new File(textFilePath);
			boolean deleted =false;
			if(file.delete())
			{
				for(User user : document.getUsers())
				{
					user.getDocuments().remove(document);
					userService.save(user);
				}
				 deleted = documentService.delete(document);
			}
			
			
			model = new ModelAndView();
			model.addObject("message", deleted);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		 /**
		  * END Document Ajax Requests Controls
		  */
		
		 /**
		  * Template Ajax Requests Controls
		  */
		@RequestMapping(value = { "/get-template",}, method = RequestMethod.PUT)
		public ModelAndView getTemplate(@RequestParam int objectref, @RequestParam String type) {
			
			context = new ClassPathXmlApplicationContext(contextPath);
			objectService = (ObjectService) context.getBean("Object"); 
			templateService = (TemplateService) context.getBean("Template");
			net.madicorp.autodoc.models.Object object = (net.madicorp.autodoc.models.Object) objectService.findById(objectref);
			List<Template> templates = templateService.findByCategorieAndType(object.getCategorie(), type);
			
			model = new ModelAndView();
			model.addObject("message", templates);
			model.setViewName("response/rep");
			context.close();
			return model;
			
		}
		
		@RequestMapping(value = { "/template",}, method = RequestMethod.PUT)
		public ModelAndView templateRename(HttpServletRequest request,@RequestParam int templateref, @RequestParam String templatename, @RequestParam String type ) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			templateService = (TemplateService) context.getBean("Template");
			
			Template template = (Template) templateService.findById(templateref);
			String oldName = template.getNomTemplate();
			String newName = templatename +"_" +Dates.date().getTime() +"." +oldName.split("\\.")[oldName.split("\\.").length -1];
			
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/";
		    
		    String textFilePath = path+"Template/"+type+"/";
			File oldfile = new File(textFilePath+oldName);
			File newFile = new File(textFilePath+newName);
			boolean renamed =false;
			
			if (oldfile.renameTo(newFile)) {
				template.setNomTemplate(newName);
				renamed = templateService.save(template);
				
			}
			model = new ModelAndView();
			model.addObject("message", renamed);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		@RequestMapping(value = { "/template",}, method = RequestMethod.DELETE)
		public ModelAndView templateDelete(HttpServletRequest request,@RequestParam int templateref,@RequestParam String type ) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			templateService = (TemplateService) context.getBean("Template");
			documentService = (DocumentService) context.getBean("Document");
			Template template = (Template) templateService.findById(templateref);
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/";
		    
		    String textFilePath = path+"Template/"+type+"/"+template.getNomTemplate();
			File file = new File(textFilePath);
			boolean deleted =false;
			if(file.delete())
			{
				for(Document document : template.getDocuments())
				{
					document.setTemplate(null);
					documentService.save(document);
				}
				 deleted = templateService.delete(template);
			}
			
			
			model = new ModelAndView();
			model.addObject("message", deleted);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		
		@RequestMapping(value = { "/template-doc",}, method = RequestMethod.POST)
		public ModelAndView templateDocAdd(@RequestParam String templateName , @RequestParam int categorieref) {
			context = new ClassPathXmlApplicationContext(contextPath);
			categorieService = (CategorieService) context.getBean("Categorie");
			templateService = (TemplateService) context.getBean("Template");
			
			Categorie categorie = (Categorie) categorieService.findById(categorieref);
			
			Template template = new Template(categorie, templateName, "Docx");
			boolean saved = templateService.save(template);
			model = new ModelAndView();
			model.addObject("message", saved);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		
		@RequestMapping(value = { "/template-xls",}, method = RequestMethod.POST)
		public ModelAndView templateXlsAdd(@RequestParam String templateName , @RequestParam int categorieref) {
			context = new ClassPathXmlApplicationContext(contextPath);
			categorieService = (CategorieService) context.getBean("Categorie");
			templateService = (TemplateService) context.getBean("Template");
			
			Categorie categorie = (Categorie) categorieService.findById(categorieref);
			
			Template template = new Template(categorie, templateName, "Xls");
			boolean saved = templateService.save(template);
			model = new ModelAndView();
			model.addObject("message", saved);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		@RequestMapping(value = { "/template-ppt",}, method = RequestMethod.POST)
		public ModelAndView templatePptAdd(@RequestParam String templateName , @RequestParam int categorieref) {
			context = new ClassPathXmlApplicationContext(contextPath);
			categorieService = (CategorieService) context.getBean("Categorie");
			templateService = (TemplateService) context.getBean("Template");
			
			Categorie categorie = (Categorie) categorieService.findById(categorieref);
			
			Template template = new Template(categorie, templateName, "Pptx");
			boolean saved = templateService.save(template);
			model = new ModelAndView();
			model.addObject("message", saved);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		 /**
		  * END Template Ajax Requests Controls
		  */
		
		 /**
		  * Gabarit Ajax Requests Controls
		  */
		
		@RequestMapping(value = { "/gabarit",}, method = RequestMethod.POST)
		public ModelAndView gabaritAdd(@RequestParam String gabaritName , @RequestParam int categorieref) {
			context = new ClassPathXmlApplicationContext(contextPath);
			categorieService = (CategorieService) context.getBean("Categorie");
			gabaritService = (GabaritService) context.getBean("Gabarit");
			
			Categorie categorie = (Categorie) categorieService.findById(categorieref);
			
			Gabarit gabarit = new Gabarit(categorie, gabaritName);	
			boolean saved = gabaritService.save(gabarit);
			model = new ModelAndView();
			model.addObject("message", saved);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		@RequestMapping(value = { "/gabarit",}, method = RequestMethod.PUT)
		public ModelAndView gabaritRename(HttpServletRequest request,@RequestParam int gabaritref, @RequestParam String gabaritname ) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			gabaritService = (GabaritService) context.getBean("Gabarit");
			
			Gabarit gabarit = (Gabarit) gabaritService.findById(gabaritref);
			String oldName = gabarit.getNomGabarit();
			String newName = gabaritname +"_" +Dates.date().getTime() +"." +oldName.split("\\.")[oldName.split("\\.").length -1];
			
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/";
		    
		    String textFilePath = path+"Gabarit/";
			File oldfile = new File(textFilePath+oldName);
			File newFile = new File(textFilePath+newName);
			boolean renamed =false;
			
			if (oldfile.renameTo(newFile)) {
				gabarit.setNomGabarit(newName);
				renamed = gabaritService.save(gabarit);
				
			}
			model = new ModelAndView();
			model.addObject("message", renamed);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		
		@RequestMapping(value = { "/gabarit-arg",}, method = RequestMethod.GET)
		public ModelAndView gabaritGetArgs(HttpServletRequest request,@RequestParam int gabaritref) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			gabaritService = (GabaritService) context.getBean("Gabarit");
			
			Gabarit gabarit = (Gabarit) gabaritService.findById(gabaritref);
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/Gabarit/"+gabarit.getNomGabarit();
		    
		  FileEditer fileEditer = new FileEditer();
		  
		  fileEditer.setFile(new File(path));
			List<String> argsList = fileEditer.readFile();
			String args="" ;
			for(String arg : argsList)
			{
				args += arg+"/___/";
			}
			model = new ModelAndView();
			model.addObject("message", args);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		@RequestMapping(value = { "/gabarit-arg",}, method = RequestMethod.PUT)
		public ModelAndView gabaritSetArgs(HttpServletRequest request,@RequestParam int gabaritref, @RequestParam String args) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			gabaritService = (GabaritService) context.getBean("Gabarit");
			
			Gabarit gabarit = (Gabarit) gabaritService.findById(gabaritref);
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/Gabarit/"+gabarit.getNomGabarit();
		    
		  FileEditer fileEditer = new FileEditer();
		  
		  fileEditer.setFile(new File(path));
			ArrayList<String> argsList = new ArrayList<String>();
			String [] targs= args.split("/___/") ;
			
			for(String arg : targs)
			{
				argsList.add(arg);
			}
			fileEditer.setArgs(argsList);
			boolean rep = fileEditer.saveFile();
			model = new ModelAndView();
			model.addObject("message", rep);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		
		@RequestMapping(value = { "/gabarit-clone",}, method = RequestMethod.PUT)
		public ModelAndView gabaritClone(HttpServletRequest request,@RequestParam int gabaritref, @RequestParam String args) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			gabaritService = (GabaritService) context.getBean("Gabarit");
			
			Gabarit gabarit = (Gabarit) gabaritService.findById(gabaritref);
			
			String [] tname = gabarit.getNomGabarit().split("_");
			String _name = "";
			
			if(tname.length > 2)
			{
				for (int i = 0; i < tname.length-1; i++) {
					
					if(i < tname.length-2)
						_name +=tname[i]+"_";
						else
							_name +=tname[i];
					
				}
				
				_name +="_"+Dates.date().getTime()+"."+tname[tname.length-1].split("\\.")[1];
				
			}
			else{
				_name = tname[0] + "_"+Dates.date().getTime()+"."+tname[1].split("\\.")[1];
			}
			
			_name = "Clone of "+ _name;
			
			Gabarit newGabarit = new Gabarit(gabarit.getCategorie(), _name);
			
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/Gabarit/"+_name;
		    
		    FileEditer fileEditer = new FileEditer();
		  
		  fileEditer.setFile(new File(path));
			ArrayList<String> argsList = new ArrayList<String>();
			String [] targs= args.split("/___/") ;
			
			for(String arg : targs)
			{
				argsList.add(arg);
			}
			fileEditer.setArgs(argsList);
			boolean rep = fileEditer.saveFile();
			
			
			if(rep){
				 rep = gabaritService.save(newGabarit);
			}

			model = new ModelAndView();
			model.addObject("message", rep+","+newGabarit.getIdGabarit());
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		
		@RequestMapping(value = { "/gabarit",}, method = RequestMethod.DELETE)
		public ModelAndView gabaritDelete(HttpServletRequest request,@RequestParam int gabaritref ) {
		 	
			context = new ClassPathXmlApplicationContext(contextPath);
			gabaritService = (GabaritService) context.getBean("Gabarit");
			documentService = (DocumentService) context.getBean("Document");
			Gabarit gabarit = (Gabarit) gabaritService.findById(gabaritref);
			String name = "";
		    HttpSession usersession = request.getSession();
		    name = usersession.getAttribute("name").toString();
		    ClassPath classPath = new ClassPath();
		    String path = classPath.getWebInfPath();
		    path+="BACK-SPACE/"+name+"/";
		    
		    String textFilePath = path+"Gabarit/"+gabarit.getNomGabarit();
			File file = new File(textFilePath);
			boolean deleted =false;
			if(file.delete())
			{
				for(Document document : gabarit.getDocuments())
				{
					document.setGabarit(null);
					documentService.save(document);
				}
				 deleted = gabaritService.delete(gabarit);
			}
			
			
			model = new ModelAndView();
			model.addObject("message", deleted);
			model.setViewName("response/rep");
			context.close();
			return model;

		}
		
		 /**
		  * END Gabarit Ajax Requests Controls
		  */
		
		
		/**
		  * Object Type Ajax Requests Controls
		  */
		@RequestMapping(value = { "/get-object-type",}, method = RequestMethod.PUT)
		public ModelAndView getObjectType(@RequestParam int mod) {
			context = new ClassPathXmlApplicationContext(contextPath);
			objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
			categorieService = (CategorieService) context.getBean("Categorie");
			Categorie categorie = (Categorie) categorieService.findById(mod);
			List<ObjectType> objectTypes = objectTypeService.findByCategorie(categorie);
			String rep ="";
			for(ObjectType objectType : objectTypes)
			{
				rep += objectType.getIdObjectType()+"/__/"+objectType.getNomObjectType()+"/___/";
			}
			
			model = new ModelAndView();
			model.addObject("message", rep);
			model.setViewName("response/rep");
			context.close();
			return model;
		}
		
		@RequestMapping(value = { "/edit-object-type",}, method = RequestMethod.PUT)
		public ModelAndView editObjectType(@RequestParam int ref, @RequestParam String args, @RequestParam String vivible_fields) {
			context = new ClassPathXmlApplicationContext(contextPath);
			objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
			ObjectType objectType = (ObjectType) objectTypeService.findById(ref);
			objectType.setAttributes(args);
			objectType.setVisibleFields(vivible_fields);
			boolean rep = objectTypeService.save(objectType) ;
			
			model = new ModelAndView();
			model.addObject("message", rep);
			model.setViewName("response/rep");
			context.close();
			return model;
		}
		
		@RequestMapping(value = { "/rename-object-type",}, method = RequestMethod.PUT)
		public ModelAndView renameObjectType(@RequestParam int ref, @RequestParam String name) {
			context = new ClassPathXmlApplicationContext(contextPath);
			objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
			ObjectType objectType = (ObjectType) objectTypeService.findById(ref);
			objectType.setNomObjectType(name);
			
			boolean rep = objectTypeService.save(objectType) ;
			
			model = new ModelAndView();
			model.addObject("message", rep);
			model.setViewName("response/rep");
			context.close();
			return model;
		}
		
		@RequestMapping(value = { "/get-object-type",}, method = RequestMethod.POST)
		public ModelAndView getObjectTypeVariable(@RequestParam int objtype) {
			context = new ClassPathXmlApplicationContext(contextPath);
			objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
			categorieService = (CategorieService) context.getBean("Categorie");
			ObjectType objectType = (ObjectType) objectTypeService.findById(objtype);
			String rep = objectType.getAttributes();
			rep+="/__/"+objectType.getVisibleFields();
			model = new ModelAndView();
			model.addObject("message", rep);
			model.setViewName("response/rep");
			context.close();
			return model;
		}
		
		
		@RequestMapping(value = { "/delete-object-type",}, method = RequestMethod.DELETE)
		public ModelAndView deleteObjectType(@RequestParam int ref) {
			context = new ClassPathXmlApplicationContext(contextPath);
			objectTypeService = (ObjectTypeService) context.getBean("ObjectType"); 
			ObjectType objectType = (ObjectType) objectTypeService.findById(ref);
			boolean rep = objectTypeService.delete(objectType) ;
			
			model = new ModelAndView();
			model.addObject("message", rep);
			model.setViewName("response/rep");
			context.close();
			return model;
		}
		
		 /**
		  * END Object Type Ajax Requests Controls
		  */
		
		
		

}
