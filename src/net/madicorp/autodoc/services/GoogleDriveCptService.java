package net.madicorp.autodoc.services;

import java.util.List;

import net.madicorp.autodoc.models.GoogleDriveCpt;

import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.File;
import com.google.api.client.auth.oauth2.Credential;

public interface GoogleDriveCptService extends AppService {

	
	public String getAuthUrl() ;
	
	public About getAccountInformations(Credential credential);
	
	public Credential getCredentials (String authorizationCode);
	
	public List<File> getElementsByFolder (GoogleDriveCpt googleDriveCpt,String path);
	

}
