package net.madicorp.autodoc.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import net.madicorp.autodoc.models.DropboxCpt;

import com.dropbox.core.DbxEntry;

public interface DropboxCptService extends AppService {
	
	public String getAuthUrl() ;
	
	public String generateAccesstoken(String code) ;
	
	public boolean createFolder(DropboxCpt dropboxCpt,String path);
	
	public void dowloadFile (DropboxCpt dropboxCpt,String path, OutputStream outputStream);
	
	public void uploadFile (DropboxCpt dropboxCpt,String path,long length,InputStream inputStream );
	
	public void deleteFile (DropboxCpt dropboxCpt,String path);
	
	public List<DbxEntry> listByDirectory(DropboxCpt dropboxCpt,String path);

}
