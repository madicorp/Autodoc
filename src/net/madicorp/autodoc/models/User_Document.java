package net.madicorp.autodoc.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.madicorp.autodoc.classes.Dates;

/**
 * @author Diop Sega
 *
 */
@Entity
@IdClass(User_Document_PK.class)
@Table(name = "User_Document", catalog = "autodocdb")
public class User_Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7390027499107836103L;
	private Integer user_idUser;
	private	Integer document_idDocument;
	private Date sharedDate = Dates.date();
	private User user;
	

	public User_Document() {

	}
	
	
	
	public User_Document(User user,Integer user_idUser, Integer document_idDocument,
			Date sharedDate) {
		super();
		this.user_idUser = user_idUser;
		this.document_idDocument = document_idDocument;
		this.sharedDate = sharedDate;
		this.user = user;
	}



	@Id
	@Column(name = "User_idUser", nullable = false)
	public Integer getUser_idUser() {
		return user_idUser;
	}
	public void setUser_idUser(Integer user_idUser) {
		this.user_idUser = user_idUser;
	}
	@Id
	@Column(name = "Document_idDocument", nullable = false)
	public Integer getDocument_idDocument() {
		return document_idDocument;
	}
	public void setDocument_idDocument(Integer document_idDocument) {
		this.document_idDocument = document_idDocument;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "shared_Date", length = 10)
	public Date getSharedDate() {
		return sharedDate;
	}
	public void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "User_owner", nullable = true)
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


}
