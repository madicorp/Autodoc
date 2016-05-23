package net.madicorp.autodoc.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import net.madicorp.autodoc.models.DropboxCpt;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;


@Service("DropboxCpt")
@Transactional
public class DropboxCptServiceImpl implements DropboxCptService {
	
	@Autowired
	private DbxAppInfo dbxAppInfo;
	
	@Autowired
	private SessionFactory sf ;


	@SuppressWarnings("unchecked")
	@Override
	public List<DropboxCpt> findAll() {
		
		List<DropboxCpt> dropboxCpts = null;
		
		try {
			dropboxCpts = sf.getCurrentSession().createQuery("FROM DropboxCpt d").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dropboxCpts;
	}







	@Override
	public DropboxCpt findById(Integer id) {
		
		DropboxCpt dropboxCpt = null;
		try {
			
			dropboxCpt = (DropboxCpt) sf.getCurrentSession().get(DropboxCpt.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dropboxCpt;
	}







	@Override
	public List<?> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public boolean save(Object dropboxCpt) {
		boolean saved = false;
		
		dropboxCpt = (DropboxCpt) dropboxCpt;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(dropboxCpt);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}







	@Override
	public boolean delete(Object dropboxCpt) {
		boolean deleted = false;
		
		dropboxCpt = (DropboxCpt) dropboxCpt;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(dropboxCpt);
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleted;
	}

	
	
	
	
	
	@Override
	public String getAuthUrl() {
		// TODO Auto-generated method stub
		
		DbxRequestConfig config = new DbxRequestConfig(
	            "JavaTutorial/1.0", Locale.getDefault().toString());
	        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, dbxAppInfo);
	        String authorizeUrl = webAuth.start();
		return authorizeUrl;
	}




	@Override
	public List<DbxEntry> listByDirectory(DropboxCpt dropboxCpt, String path) {
		
		DbxRequestConfig config = new DbxRequestConfig(
	            "JavaTutorial/1.0", Locale.getDefault().toString());
		DbxClient client = new DbxClient(config, dropboxCpt.getAccessTokenDropboxCpt());
		try {
			DbxEntry.WithChildren listing = client.getMetadataWithChildren(path);
			return listing.children;
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		
		}
	}







	@Override
	public String generateAccesstoken(String code) {
		DbxRequestConfig config = new DbxRequestConfig(
	            "JavaTutorial/1.0", Locale.getDefault().toString());
	        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, dbxAppInfo);
	        DbxAuthFinish authFinish;
			try {
				authFinish = webAuth.finish(code);
				  String accessToken = authFinish.accessToken;
				  return accessToken;
			} catch (DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		  
		
	}







	@Override
	public boolean createFolder(DropboxCpt dropboxCpt, String path) {

		DbxRequestConfig config = new DbxRequestConfig(
	            "JavaTutorial/1.0", Locale.getDefault().toString());
		DbxClient client = new DbxClient(config, dropboxCpt.getAccessTokenDropboxCpt());
		try {
			 client.createFolder(path);
			return true;
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}







	@Override
	public void dowloadFile(DropboxCpt dropboxCpt, String path,
			OutputStream outputStream) {
		// TODO Auto-generated method stub
		
		DbxRequestConfig config = new DbxRequestConfig(
	            "JavaTutorial/1.0", Locale.getDefault().toString());
		DbxClient client = new DbxClient(config, dropboxCpt.getAccessTokenDropboxCpt());
		try {
		      client.getFile(path, "",outputStream);
		 } catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			 try {
				 outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
	}







	@Override
	public void uploadFile(DropboxCpt dropboxCpt, String path, long length,
			InputStream inputStream) {
		// TODO Auto-generated method stub
		DbxRequestConfig config = new DbxRequestConfig(
	            "JavaTutorial/1.0", Locale.getDefault().toString());
		DbxClient client = new DbxClient(config, dropboxCpt.getAccessTokenDropboxCpt());
		
		try {
			client.uploadFile(path, DbxWriteMode.add(), length, inputStream);
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}







	@Override
	public void deleteFile(DropboxCpt dropboxCpt, String path) {
		// TODO Auto-generated method stub
		DbxRequestConfig config = new DbxRequestConfig(
	            "JavaTutorial/1.0", Locale.getDefault().toString());
		DbxClient client = new DbxClient(config, dropboxCpt.getAccessTokenDropboxCpt());
		
		try {
			client.delete(path);
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}






	
}
