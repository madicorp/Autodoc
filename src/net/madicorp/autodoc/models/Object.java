/**
 * 
 */
package net.madicorp.autodoc.models;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Diop Sega
 *
 */


@Entity
@Table(name = "Object", catalog = "autodocdb")
@DiscriminatorValue(value="OBJECT")
public class Object extends Gabarit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2259439752557446567L;
	/**
	 * 
	 */

	private ObjectType objectType;
	
	public Object() {
		// TODO Auto-generated constructor stub
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ObjectType_idObjectType", nullable = true)
	public ObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}
	
	

	
}
