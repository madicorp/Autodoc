package net.madicorp.autodoc.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.FilenameFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import net.madicorp.autodoc.classes.ClassPath;
import net.madicorp.autodoc.classes.FileEditer;
import net.madicorp.autodoc.models.DropboxCpt;
import net.madicorp.autodoc.models.GoogleDriveCpt;
import net.madicorp.autodoc.models.User;
import net.madicorp.autodoc.services.DropboxCptService;
import net.madicorp.autodoc.services.GoogleDriveCptService;
import net.madicorp.autodoc.services.UserService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

import com.dropbox.core.DbxEntry;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.model.About;
import com.thoughtworks.xstream.XStream;


@Controller
public class CloudController {
	
	private ClassPathXmlApplicationContext context;
	 private SecurityContext securityContext;
	 private Authentication authentication;
	 private UserService userService;
	 private DropboxCptService dropboxCptService;
	 private  GoogleDriveCptService googleDriveCptService;
	 private ModelAndView model;
	 private String contextPath = "net/madicorp/autodoc/config/autodoc-context.xml";
	
	@RequestMapping(value = { "/cloud-space",}, method = RequestMethod.GET)
	public ModelAndView cloudPage() {
		
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
	   DropboxCpt dropboxCpt = user.getDropboxCpt();
	    GoogleDriveCpt googleDriveCpt = user.getGoogleDriveCpt();
	    
	    model = new ModelAndView();
		model.addObject("role", user.getRole().getCodeRole());
		model.addObject("cloudPage","active");
		model.addObject("dropboxcpt",dropboxCpt);
		model.addObject("googleDriveCpt",googleDriveCpt);
		model.setViewName("user/cloudspace");
		context.close();
		return model;
 
	}
	
	/**
	 * LOCAL STORAGE SECTION
	 */
	
	@RequestMapping(value = { "/cloud-space",}, method = RequestMethod.POST)
	public ModelAndView cloudQuery(HttpServletRequest request) throws UnsupportedEncodingException {
		
	    String resp ="";	    
	    
	    String name = "";
	    HttpSession usersession = request.getSession();
	    name = usersession.getAttribute("name").toString();
	    ClassPath classPath = new ClassPath();
	    String path = classPath.getWebInfPath();
	    path+="BACK-SPACE/"+name+"/Cloud";
	    
	    
	    String dir = request.getParameter("dir");
	    String relativDir = dir;
	    dir = path+dir;
		
		if (dir.charAt(dir.length()-1) == '\\') {
	    	dir = dir.substring(0, dir.length()-1) + "/";
		} else if (dir.charAt(dir.length()-1) != '/') {
		    dir += "/";
		}
		
		dir = java.net.URLDecoder.decode(dir, "UTF-8");	
		
	    if (new File(dir).exists()) {
			String[] files = new File(dir).list(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
					return name.charAt(0) != '.';
			    }
			});
			Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);

			// All dirs
			for (String file : files) {
			    if (new File(dir, file).isDirectory()) {
			    	String contain ="";
			    	if(new File(dir, file).list().length > 0)
			    	{
			    		contain ="full";
			    	}
			    	resp+="<li title=\""+file+"\" class=\"Folders directory collapsed\">"
			    +"<a onclick=\"checkIt(this);\" href=\"#\" class=\"Folders\" rel=\"" + (relativDir + file).replace("%20", " ") + "/\">"
			    +"<span class=\"icon folder "+contain+"\"></span>"
			    +"<span class='name'>"+file+"</span>"
			    +"<span class='details'>"+new File(dir, file).list().length+" Items</span>"
				+"</a></li>";
			    }
			}
			// All files
			for (String file : files) {
			    if (!new File(dir, file).isDirectory()) {
					int dotIndex = file.lastIndexOf('.');
					String ext = dotIndex > 0 ? file.substring(dotIndex + 1) : "";
					resp+="<li title=\""+file+"\" class=\"files file ext_" + ext + "\">"
					+"<a onclick=\"checkIt(this);\" class=\"files\" href=\"#\" rel=\"" +( relativDir + file).replace("%20", " ") + "\">"
					+"<span class='icon file f-"+ext+"'>."+ext+"</span>"
					+"<span class='name'>"+file+"</span>"
					+"<span class='details'>"+new File(dir, file).length()+" Bytes</span>"
					+"</a></li>";
			    	}
			}
	    }
	    
	    model = new ModelAndView();
	    model.addObject("message", resp);
		model.setViewName("response/rep");
		return model;
 
	}
	
	/**
	 * END LOCAL STORAGE SECTION
	 */
	
	/**
	 * DROPBOX STORAGE SECTION
	 */

	@RequestMapping(value = { "/link-dropbox",}, method = RequestMethod.POST)
	public ModelAndView LinkDropbox() {
		
		securityContext = SecurityContextHolder.getContext();
		context = new ClassPathXmlApplicationContext(contextPath);
		dropboxCptService = (DropboxCptService) context.getBean("DropboxCpt");
	    String resp = dropboxCptService.getAuthUrl();
	    model = new ModelAndView();
	    model.addObject("message", resp);
		model.setViewName("response/rep");
		context.close();
		return model;
 
	}
	
	@RequestMapping(value = { "/dropbox-save-account",}, method = RequestMethod.POST)
	public ModelAndView DropboxAuthRedirectUrl(@RequestParam String code, @RequestParam String email) {
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		context = new ClassPathXmlApplicationContext(contextPath);
	    userService = (UserService) context.getBean("User");
	    dropboxCptService = (DropboxCptService) context.getBean("DropboxCpt");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    
	    
	    boolean resp = false;
	    code =  dropboxCptService.generateAccesstoken(code);
	    if(code != null)
	    {
	    	
		DropboxCpt dropboxCpt = new DropboxCpt(email, code, user);
		if(user.getDropboxCpt() !=null)
    	{
			dropboxCpt.setIdDropboxCpt(user.getDropboxCpt().getIdDropboxCpt());
    	}
	     resp = dropboxCptService.save(dropboxCpt);
	    }
	    	
	    model = new ModelAndView();
	    model.addObject("message", resp);
		model.setViewName("response/rep");
		context.close();
		return model;
 
	}
	
	
	@RequestMapping(value = { "/dropbox-space",}, method = RequestMethod.POST)
	public ModelAndView dropboxQuery(HttpServletRequest request) throws UnsupportedEncodingException {
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		
		context = new ClassPathXmlApplicationContext(contextPath);
		 userService = (UserService) context.getBean("User");
		 dropboxCptService = (DropboxCptService) context.getBean("DropboxCpt");
		 User user = new User();
		    Object principal = authentication.getPrincipal();  
		    if (principal instanceof UserDetails) {
		    	UserDetails u = (UserDetails) principal;
		    	user = (User) userService.findByName(u.getUsername()).get(0);
		    }
		    String dir = request.getParameter("dir");
		    dir = java.net.URLDecoder.decode(dir, "UTF-8");	
		    if (dir.length() > 1 && dir.charAt(dir.length()-1)=='/') {
		    	dir = dir.substring(0, dir.length()-1);
		      }
		    DropboxCpt dropboxCpt = user.getDropboxCpt();
		 List<DbxEntry> dbxEntries =  dropboxCptService.listByDirectory(dropboxCpt, dir);
		 String resp ="";
		 for (DbxEntry child : dbxEntries) {
			   if(child.isFolder())
			   {
				   String contain ="";
				   
				   List<DbxEntry> listChildren = dropboxCptService.listByDirectory(dropboxCpt, child.path);
				   
				   int items  = listChildren.size();
				   if( items >0)
				   {
					   contain ="full";
				   }
		
					  
				   
				   resp+="<li title=\""+child.name+"\" class=\"Folders directory collapsed\">"
						    +"<a onclick=\"checkIt(this);\" href=\"#\" class=\"Folders\" rel=\"" + child.path+ "/\">"
						    +"<span class=\"icon folder "+contain+"\"></span>"
						    +"<span class='name'>"+child.name+"</span>"
						    +"<span class='details'>"+items+" Items</span>"
							+"</a></li>";
			   }
			   else{
				   
				   int dotIndex = child.name.lastIndexOf('.');
					String ext = dotIndex > 0 ? child.name.substring(dotIndex + 1) : "";
					resp+="<li title=\""+child.name+"\" class=\"files file ext_" + ext + "\">"
					+"<a onclick=\"checkIt(this);\" class=\"files\" href=\"#\" rel=\"" +child.path+ "\">"
					+"<span class='icon file f-"+ext+"'>."+ext+"</span>"
					+"<span class='name'>"+child.name+"</span>"
					+"<span class='details'>"+child.asFile().numBytes+" Bytes</span>"
					+"</a></li>";
			   }
			   
			}
		
	    
	    model = new ModelAndView();
	    model.addObject("message", resp);
		model.setViewName("response/rep");
		return model;
 
	}
	
	
	@RequestMapping(value = { "/dropbox-create-folder",}, method = RequestMethod.PUT)
	public ModelAndView DropboxCreateFolder(@RequestParam String currentFolder, @RequestParam String NewFolderName) {
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		context = new ClassPathXmlApplicationContext(contextPath);
	    userService = (UserService) context.getBean("User");
	    dropboxCptService = (DropboxCptService) context.getBean("DropboxCpt");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    
	   DropboxCpt dropboxCpt = user.getDropboxCpt();
	   String path = currentFolder+NewFolderName;
	   boolean resp = dropboxCptService.createFolder(dropboxCpt, path);
	    	
	    model = new ModelAndView();
	    model.addObject("message", resp);
		model.setViewName("response/rep");
		context.close();
		return model;
 
	}
	
	@RequestMapping(value = { "/dropbox-download-file",}, method = RequestMethod.GET)
	public ModelAndView DropboxdownloadFile(@RequestParam String currentFolder, @RequestParam String FileName, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition",
	                     "attachment;filename="+FileName);
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		context = new ClassPathXmlApplicationContext(contextPath);
	    userService = (UserService) context.getBean("User");
	    dropboxCptService = (DropboxCptService) context.getBean("DropboxCpt");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    
	   DropboxCpt dropboxCpt = user.getDropboxCpt();
	   String path = currentFolder+FileName;
	   OutputStream outputStream = response.getOutputStream();
	    dropboxCptService.dowloadFile(dropboxCpt, path, outputStream);
	    	
	    model = new ModelAndView();
		model.setViewName("response/rep");
		context.close();
		return model;
 
	}
	
	
	@RequestMapping(value = { "/dropbox-upload-file",}, method = RequestMethod.POST)
	public ModelAndView DropboxuploadFile(@RequestParam String currentFolder, HttpServletResponse response, HttpServletRequest request) throws FileUploadException, IOException{
		
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		context = new ClassPathXmlApplicationContext(contextPath);
	    userService = (UserService) context.getBean("User");
	    dropboxCptService = (DropboxCptService) context.getBean("DropboxCpt");
	    User user = new User();
	    Object principal = authentication.getPrincipal();  
	    if (principal instanceof UserDetails) {
	    	UserDetails u = (UserDetails) principal;
	    	user = (User) userService.findByName(u.getUsername()).get(0);
	    }
	    
	   DropboxCpt dropboxCpt = user.getDropboxCpt();
	   FileItemFactory factory = new DiskFileItemFactory();
	   ServletFileUpload upload = new ServletFileUpload(factory);
	   List items = upload.parseRequest(request);

	   Iterator iter = items.iterator();
	   while (iter.hasNext()) {
	       FileItem item = (FileItem) iter.next();
	       
	       if (!item.isFormField()) {

	    	   String path = currentFolder+item.getName();
	  	       InputStream inputStream = item.getInputStream();
	  	       dropboxCptService.uploadFile(dropboxCpt, path, item.getSize(), inputStream);
	        }
	       
	      
	   }
	 
	  
	    
	    	
	    model = new ModelAndView();
		model.setViewName("response/rep");
		context.close();
		return model;
		
 
	}
	
	
	
	/**
	 * END DROPBOX STORAGE SECTION
	 */
	
	
	
	
	
	
	/**
	 *  GOOGLEDRIVE STORAGE SECTION
	 */
	
	@RequestMapping(value = { "/oauth2callback",}, method = RequestMethod.GET)
	public ModelAndView RedirectUrlLinkgoogledrive(@RequestParam String code) {
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		context = new ClassPathXmlApplicationContext(contextPath);
		 userService = (UserService) context.getBean("User");
		googleDriveCptService  = (GoogleDriveCptService) context.getBean("GoogleDriveCpt");
		
		 User user = new User();
		    Object principal = authentication.getPrincipal();  
		    if (principal instanceof UserDetails) {
		    	UserDetails u = (UserDetails) principal;
		    	user = (User) userService.findByName(u.getUsername()).get(0);
		    }
		
		Credential credential = googleDriveCptService.getCredentials(code);
		 XStream xstream = new XStream();
	     String SerializedCredential = xstream.toXML(credential);
	     About about = googleDriveCptService.getAccountInformations(credential);
	        GoogleDriveCpt googleDriveCpt = new GoogleDriveCpt(about.getName(), SerializedCredential, user);
	      boolean resp =  googleDriveCptService.save(googleDriveCpt);
	   
	    model = new ModelAndView();
	    model.addObject("message", resp+" PLEASE CLOSE THE WINDOW .");
		model.setViewName("response/rep");
		context.close();
		return model;
 
	}
	
	@RequestMapping(value = { "/link-googledrive",}, method = RequestMethod.POST)
	public ModelAndView Linkgoogledrive() {
		
		context = new ClassPathXmlApplicationContext(contextPath);
		googleDriveCptService  = (GoogleDriveCptService) context.getBean("GoogleDriveCpt");
	    String resp = googleDriveCptService.getAuthUrl();
	    model = new ModelAndView();
	    model.addObject("message", resp);
		model.setViewName("response/rep");
		context.close();
		return model;
 
	}
	
	
	
	
	
	
	@RequestMapping(value = { "/googledrive-space",}, method = RequestMethod.POST)
	public ModelAndView googleDriveQuery(HttpServletRequest request) throws UnsupportedEncodingException {
		
		securityContext = SecurityContextHolder.getContext();
	    authentication = securityContext.getAuthentication();
		
		context = new ClassPathXmlApplicationContext(contextPath);
		 userService = (UserService) context.getBean("User");
		 googleDriveCptService = (GoogleDriveCptService) context.getBean("GoogleDriveCpt");
		 User user = new User();
		    Object principal = authentication.getPrincipal();  
		    if (principal instanceof UserDetails) {
		    	UserDetails u = (UserDetails) principal;
		    	user = (User) userService.findByName(u.getUsername()).get(0);
		    }
		    String dir = request.getParameter("dir");
		    
		    if(dir.equalsIgnoreCase("/")){
		    	dir="/root/";
		    }
		    System.out.println(dir);
		    dir = java.net.URLDecoder.decode(dir, "UTF-8");	
		    if (dir.length() > 1 && dir.charAt(dir.length()-1)=='/') {
		    	dir = dir.substring(0, dir.length()-1);
		      }
		   
		    String [] tDir = dir.split("/");
		    String fileId = tDir[tDir.length-1];
		    GoogleDriveCpt googleDriveCpt = user.getGoogleDriveCpt();
		    
		    List<com.google.api.services.drive.model.File> files = googleDriveCptService.getElementsByFolder(googleDriveCpt, fileId);
		    
		 String resp ="";
		 for (com.google.api.services.drive.model.File file : files) {
			   if(file.getMimeType().contains("vnd.google-apps.folder"))
			   {
				   String contain ="";
				   
				   List<com.google.api.services.drive.model.File> listChildren = googleDriveCptService.getElementsByFolder(googleDriveCpt, file.getId());
				   
				   int items  = listChildren.size();
				   if( items >0)
				   {
					   contain ="full";
				   }
		
					  
				   
				   resp+="<li title=\""+file.getTitle()+"\" class=\"Folders directory collapsed\">"
						    +"<a onclick=\"checkIt(this);\" href=\"#\" class=\"Folders\" rel=\"" +dir+"/"+ file.getId()+ "/\">"
						    +"<span class=\"icon folder "+contain+"\"></span>"
						    +"<span class='name'>"+file.getTitle()+"</span>"
						    +"<span class='details'>"+items+" Items</span>"
							+"</a></li>";
			   }
			   else{
				   
					String ext = file.getFileExtension();
					resp+="<li title=\""+file.getTitle()+"\" class=\"files file ext_" + ext + "\">"
					+"<a onclick=\"checkIt(this);\" class=\"files\" href=\"#\" rel=\""+dir+"/" +file.getId()+ "\">"
					+"<span class='icon file f-"+ext+"'>."+ext+"</span>"
					+"<span class='name'>"+file.getTitle()+"</span>"
					+"<span class='details'>"+file.getFileSize()+" Bytes</span>"
					+"</a></li>";
			   }
			   
			}
		
	    
	    model = new ModelAndView();
	    model.addObject("message", resp);
		model.setViewName("response/rep");
		return model;
 
	}
	
	
	
	
	
	
	/**
	 * END GOOGLEDRIVE STORAGE SECTION
	 */
	

}
