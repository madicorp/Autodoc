package net.madicorp.autodoc.classes;

import java.io.File;

/**
 * @author Diop Sega
 *
 */
public class ClassPath {
	
	
	    private static ClassPath instance = null;
	    private String webInfPath, webXmlPath, configXmlPath;
	 
	    /**
	     * The constructor will get the webInfPath and store it until the app close
	     */
	    public ClassPath(){
	        File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
	 
	        int packageSubFolder = getClass().getPackage().getName().replaceAll("[^.]", "").length() + 3;
	 
	        for(int i=0; i<packageSubFolder; i++){
	            myClass = myClass.getParentFile();
	        }
	 
	        this.webInfPath = myClass.getAbsolutePath().replaceAll("%20", " ") + File.separator;
	        this.webXmlPath = this.getWebInfPath() + "web.xml";
	        this.configXmlPath = this.getWebInfPath() + "config.xml";
	    }
	 
	    /**
	     * Singleton structure
	     * @return himself
	     */
	    public static ClassPath getInstance(){
	        if(instance == null){
	            instance = new ClassPath();
	        }
	        return instance;
	    }
	 
	 
	    /**
	     * Get back the WEB-INF path
	     * @return The WEB-INF path
	     */
	    public String getWebInfPath(){
	        return this.webInfPath;
	    }
	 
	    /**
	     * Get back the WEB-INF/web.xml path
	     * @return The WEB-INF/web.xml path
	     */
	    public  String getWebXmlPath(){
	        return this.webXmlPath;
	    }
	    
	 
	    /**
	     * Get back the WEB-INF/config.xml path
	     * @return The WEB-INF/config.xml path
	     */
	    public String getConfigXmlPath(){
	        return this.configXmlPath;
	    }
	}


