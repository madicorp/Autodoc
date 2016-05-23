package net.madicorp.autodoc.filemanager.configuration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Diop Sega
 *
 */
public class MyConfiguration extends Configuration {
	
	
	   public MyConfiguration(ServletConfig servletConfig) {
			super(servletConfig);
		}	
	 
	 
	        @Override
		protected Configuration createConfigurationInstance() {
			return new MyConfiguration(this.servletConf);
		}
	        
	        @Override
	        public void init() throws Exception {
	        	super.init();
	        	
	        }
	        
	        @Override
	        public boolean checkAuthentication(final HttpServletRequest request) {
	        	return true;
	        }
	      

}
