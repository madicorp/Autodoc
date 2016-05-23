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
@Table(name = "DropboxCpt", catalog = "autodocdb")
public class DropboxCpt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1513006302623295177L;
	private Integer idDropboxCpt;
	private String emailDropboxCpt;
	private String accessTokenDropboxCpt;
	private User user;
	
	
	
	public DropboxCpt() {
		// TODO Auto-generated constructor stub
	}
	
	public DropboxCpt(String emailDropboxCpt, String accessTokenDropboxCpt,
			User user) {
		super();
		this.emailDropboxCpt = emailDropboxCpt;
		this.accessTokenDropboxCpt = accessTokenDropboxCpt;
		this.user = user;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idDropboxCpt", unique = true, nullable = false)
	public Integer getIdDropboxCpt() {
		return idDropboxCpt;
	}
	public void setIdDropboxCpt(Integer idDropboxCpt) {
		this.idDropboxCpt = idDropboxCpt;
	}
	
	@Column(name = "emailDropboxCpt",length = 45)
	public String getEmailDropboxCpt() {
		return emailDropboxCpt;
	}
	public void setEmailDropboxCpt(String emailDropboxCpt) {
		this.emailDropboxCpt = emailDropboxCpt;
	}
	
	@Column(name = "accessTokenDropboxCpt", length = 65535)
	public String getAccessTokenDropboxCpt() {
		return this.accessTokenDropboxCpt;
	}
	public void setAccessTokenDropboxCpt(String accessTokenDropboxCpt) {
		this.accessTokenDropboxCpt = accessTokenDropboxCpt;
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
