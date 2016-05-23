package net.madicorp.autodoc.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "GoogleDriveCpt", catalog = "autodocdb")
public class GoogleDriveCpt implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2431276352250295025L;
	private Integer idGoogleDriveCpt;
	private String	emailGoogleDriveCpt;
	private	String  credential;
	private User	user;
	
	
	public GoogleDriveCpt() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	public GoogleDriveCpt(String credential, User user) {
		super();
		this.credential = credential;
		this.user = user;
	}






	public GoogleDriveCpt(String emailGoogleDriveCpt, String credential,
			User user) {
		super();
		this.emailGoogleDriveCpt = emailGoogleDriveCpt;
		this.credential = credential;
		this.user = user;
	}




	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idGoogleDriveCpt", unique = true, nullable = false)
	public Integer getIdGoogleDriveCpt() {
		return idGoogleDriveCpt;
	}
	public void setIdGoogleDriveCpt(Integer idGoogleDriveCpt) {
		this.idGoogleDriveCpt = idGoogleDriveCpt;
	}
	
	@Column(name = "emailGoogleDriveCpt", length = 45)
	public String getEmailGoogleDriveCpt() {
		return emailGoogleDriveCpt;
	}
	public void setEmailGoogleDriveCpt(String emailGoogleDriveCpt) {
		this.emailGoogleDriveCpt = emailGoogleDriveCpt;
	}
	
	@Column(name = "credential", length = 65535)
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "User_idUser", nullable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
