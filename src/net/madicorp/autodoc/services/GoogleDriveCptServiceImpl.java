package net.madicorp.autodoc.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.madicorp.autodoc.classes.FileEditer;
import net.madicorp.autodoc.models.GoogleDriveCpt;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.FileList;
import com.thoughtworks.xstream.XStream;


@Service("GoogleDriveCpt")
@Transactional
public class GoogleDriveCptServiceImpl implements GoogleDriveCptService {

	
	
	
	@Autowired
	private SessionFactory sf ;
	

	@Override
	public String getAuthUrl() {
		// TODO Auto-generated method stub
		JacksonFactory jsonFactory = new JacksonFactory();
		HttpTransport httpTransport = new NetHttpTransport();
		String url = "";
		
		try {
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
					new FileReader(FileEditer.class.getResource("../config/client_drive_secret_autodoc.json").getFile()));
			GoogleAuthorizationCodeFlow  flow =
			          new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, Arrays.asList(
			        	      "https://www.googleapis.com/auth/drive",
			        	      "email",
			        	      "profile"))
			              .setAccessType("offline").setApprovalPrompt("force").build();
			
			GoogleAuthorizationCodeRequestUrl urlBuilder =
					flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/AutoDoc/oauth2callback");
			
			url = urlBuilder.build();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	@Override
	public Credential getCredentials(String authorizationCode) {
		// TODO Auto-generated method stub
		JacksonFactory jsonFactory = new JacksonFactory();
		HttpTransport httpTransport = new NetHttpTransport();
		GoogleClientSecrets clientSecrets;
		try {
			clientSecrets = GoogleClientSecrets.load(jsonFactory,
					new FileReader(FileEditer.class.getResource("../config/client_drive_secret_autodoc.json").getFile()));
			GoogleAuthorizationCodeFlow  flow =
			          new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, Arrays.asList(
			        	      "https://www.googleapis.com/auth/drive",
			        	      "email",
			        	      "profile")).setAccessType("offline").setApprovalPrompt("force").build();;

		      GoogleTokenResponse response =
		          flow.newTokenRequest(authorizationCode).setRedirectUri("http://localhost:8080/AutoDoc/oauth2callback").execute();
		      return flow.createAndStoreCredential(response, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	      
	}

	@Override
	public List<com.google.api.services.drive.model.File> getElementsByFolder(
			GoogleDriveCpt googleDriveCpt, String path) {
		
		JacksonFactory jsonFactory = new JacksonFactory();
		HttpTransport httpTransport = new NetHttpTransport();
		
		
		 XStream xstream = new XStream();
		 googleDriveCpt = RefreshCredentialToken(googleDriveCpt);
		 Credential credential = (Credential) xstream.fromXML(googleDriveCpt.getCredential());
		
		 Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
	    Files.List request = null;
		try {
			request = service.files().list();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Collection<com.google.api.services.drive.model.File> collection = new ArrayList<com.google.api.services.drive.model.File>();
	    do {
	      try {
	        FileList files = request.setQ("'"+path+"' in parents and trashed=false").execute();
	       
	        collection.addAll(files.getItems());
	        request.setPageToken(files.getNextPageToken());
	      } catch (IOException e) {
	        System.out.println("An error occurred: " + e);
	        request.setPageToken(null);
	      }
	    } while (request.getPageToken() != null &&
	             request.getPageToken().length() > 0);

	    return (List<com.google.api.services.drive.model.File>) collection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoogleDriveCpt> findAll() {

		List<GoogleDriveCpt> googleDriveCpts = null;
		
		try {
			googleDriveCpts = sf.getCurrentSession().createQuery("FROM GoogleDriveCpt g").list();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return googleDriveCpts;
	}

	@Override
	public GoogleDriveCpt findById(Integer id) {
		
		GoogleDriveCpt googleDriveCpt = null;
		try {
			
			googleDriveCpt = (GoogleDriveCpt) sf.getCurrentSession().get(GoogleDriveCpt.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return googleDriveCpt;
	}

	@Override
	public List<GoogleDriveCpt> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Object googleDriveCpt) {
		boolean saved = false;
		
		googleDriveCpt = (GoogleDriveCpt) googleDriveCpt;
		
		try {
			
			sf.getCurrentSession().saveOrUpdate(googleDriveCpt);
			saved = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}

	@Override
	public boolean delete(Object googleDriveCpt) {
		boolean deleted = false;
		
		googleDriveCpt = (GoogleDriveCpt) googleDriveCpt;
		
		try {
			
			sf.getCurrentSession().delete(googleDriveCpt);
			deleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleted;
	}

	@Override
	public About getAccountInformations(Credential credential) {
		JacksonFactory jsonFactory = new JacksonFactory();
		HttpTransport httpTransport = new NetHttpTransport();
		 Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
		 About about = null;
		 try {
			 about = service.about().get().execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return about;
	}
	
	
	public GoogleDriveCpt RefreshCredentialToken(GoogleDriveCpt googleDriveCpt){
		XStream xstream = new XStream();
		 
		 Credential credential = (Credential) xstream.fromXML(googleDriveCpt.getCredential());
		 
		 if(credential.getExpiresInSeconds() <= 0)
		 {
			 try {
				credential.refreshToken();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 String SerializedCredential = xstream.toXML(credential);
			 
			 googleDriveCpt.setCredential(SerializedCredential);
			 
			 save(googleDriveCpt);
			 
		 }
		 
		 return googleDriveCpt;
	}


}
