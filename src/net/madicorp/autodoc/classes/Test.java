package net.madicorp.autodoc.classes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import net.madicorp.autodoc.models.GoogleDriveCpt;
import net.madicorp.autodoc.services.GoogleDriveCptService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.thoughtworks.xstream.XStream;

public class Test {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		 @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");

//		 DropboxCptService dropboxService = (DropboxCptService) context.getBean("DropboxCpt");
//		 DropboxCpt dropboxCpt = (DropboxCpt) dropboxService.findById(2);
//		 
//		 //dropboxService.createFolder(dropboxCpt, "/test/New folder");
//		 
//		 for (DbxEntry child : dropboxService.listByDirectory(dropboxCpt, "/")) {
//			    System.out.println("	" + child.name+ ": " + child.toString());
//			}
		 GoogleDriveCptService googleDriveCptService = (GoogleDriveCptService) context.getBean("GoogleDriveCpt");
		 
		 GoogleDriveCpt googleDriveCpt = (GoogleDriveCpt) googleDriveCptService.findById(1);
		 
		 
	
		 
		 for(com.google.api.services.drive.model.File file : googleDriveCptService.getElementsByFolder(googleDriveCpt,"root")){
			 System.out.println(file.getTitle()+" \n"+file.getMimeType()+" \n"+file.getId()+" \n"+file.getFileSize()+" \n"+file.size()+" \n"+file.getFileExtension());
			 
			 sc.nextLine();
		 }
		 //System.out.println(dropboxService.getAuthUrl());
	}

}
