package net.madicorp.autodoc.models;

import java.io.Serializable;

/**
 * @author Diop Sega
 *
 */
public class User_Document_PK implements Serializable{

	

	private static final long serialVersionUID = 4212453881336457697L;
	private Integer user_idUser;
	private	Integer document_idDocument;
	
	
	public User_Document_PK() {
	}
	
	public User_Document_PK(Integer user_idUser, Integer document_idDocument) {
		super();
		this.user_idUser = user_idUser;
		this.document_idDocument = document_idDocument;
	}
	public Integer getUser_idUser() {
		return user_idUser;
	}
	public void setUser_idUser(Integer user_idUser) {
		this.user_idUser = user_idUser;
	}
	public Integer getDocument_idDocument() {
		return document_idDocument;
	}
	public void setDocument_idDocument(Integer document_idDocument) {
		this.document_idDocument = document_idDocument;
	}
	
	
	
}
