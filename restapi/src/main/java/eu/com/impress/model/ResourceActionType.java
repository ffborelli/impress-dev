package eu.com.impress.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "rsc_action_type")
public class ResourceActionType implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "rsc_action_type_func", sequenceName = "rsc_action_type_id_rsc_action_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rsc_action_type_func")
	@Column(name = "id_rsc_action_type", nullable = false)
	private Integer id;
	
	@Column(name = "rsc_action_type_text", nullable = false)
	private String resourceActionTypeText;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_type_fk", referencedColumnName = "id_resource_type")
	private ResourceType resourceType;
	
	public ResourceActionType(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setResourceActionTypeText(String resourceActionTypeText){
		this.resourceActionTypeText = resourceActionTypeText;
	}
	
	public String getResourceActionTypeText(){
		return this.resourceActionTypeText;
	}
	
	public void setResourceType(ResourceType resourceType){
		this.resourceType = resourceType;
	}
	
	public ResourceType getResourceType(){
		return this.resourceType;
	}
	
}
