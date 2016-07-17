package br.ufabc.impress.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "resource_log")
public class ResourceLog implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "resource_log_func", sequenceName = "resource_log_id_resource_log_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_log_func")
	@Column(name = "id_resource_log", nullable = false)
	private Integer id;
	
	@Column(name = "resource_log_value", nullable = false)
	private String resourceLogValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_fk", referencedColumnName = "id_resource")
	private Resource resource;
	
	public ResourceLog(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setResourceLogValue(String resourceLogValue){
		this.resourceLogValue = resourceLogValue;
	}
	
	public String getResourceLogValue(){
		return this.resourceLogValue;
	}
	
	public void setCreationDate(Date creationDate){
		this.creationDate = creationDate;
	}
	
	public Date getCreationDate(){
		return this.creationDate;
	}
	
	public void setResource(Resource resource){
		this.resource = resource;
	}
	
	public Resource getResource(){
		return this.resource;
	}
	
}