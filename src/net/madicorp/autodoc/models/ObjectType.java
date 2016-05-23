package net.madicorp.autodoc.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Diop Sega
 *
 */

@Entity
@Table(name = "ObjectType", catalog = "autodocdb")
public class ObjectType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3632227752025879616L;
	private Integer idObjectType ;
	private String nomObjectType;
	private String attributes;
	private String visibleFields;
	private Categorie categorie;
	private Set<Object> object = new HashSet<Object>(0);
	
	public ObjectType() {
	}
	
	
	
	public ObjectType(String nomObjectType, String attributes,
			Categorie categorie) {
		super();
		this.nomObjectType = nomObjectType;
		this.attributes = attributes;
		this.categorie = categorie;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idObjectType", unique = true, nullable = false)
	public Integer getIdObjectType() {
		return idObjectType;
	}
	public void setIdObjectType(Integer idObjectType) {
		this.idObjectType = idObjectType;
	}
	@Column(name = "nomObjectType", length = 65535)
	public String getNomObjectType() {
		return nomObjectType;
	}
	public void setNomObjectType(String nomObjectType) {
		this.nomObjectType = nomObjectType;
	}
	@Column(name = "attributes", length = 65535)
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	
	@Column(name = "visibleFields", length = 65535)
	public String getVisibleFields() {
		return visibleFields;
	}



	public void setVisibleFields(String visibleFields) {
		this.visibleFields = visibleFields;
	}



	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Categorie_idCategorie", nullable = true)
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "objectType")
	public Set<Object> getObject() {
		return object;
	}
	public void setObject(Set<Object> object) {
		this.object = object;
	}
	
	
	
	

}
