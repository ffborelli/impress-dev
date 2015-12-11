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
@Table(name = "rsc_fusion_log")
public class ResourceFusionLog implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "rsc_fusion_log_func", sequenceName = "rsc_fusion_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rsc_fusion_log_func")
	@Column(name = "id_rsc_fusion_log", nullable = false)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_log_fk", referencedColumnName = "id_resource_log")
	private ResourceLog resourceLog;
	
	@ManyToOne
	@JoinColumn(name = "id_fusion_log_fk", referencedColumnName = "id_fusion_log")
	private FusionLog fusionLog;
	
	public ResourceFusionLog(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setCreationDate(Date creationDate){
		this.creationDate = creationDate;
	}
	
	public Date getCreationDate(){
		return this.creationDate;
	}
	
	public void setResourceLog(ResourceLog resourceLog){
		this.resourceLog = resourceLog;
	}
	
	public ResourceLog getResourceLog(){
		return this.resourceLog;
	}
	
	public void setFusionLog(FusionLog fusionLog){
		this.fusionLog = fusionLog;
	}
	
	public FusionLog getFusionLog(){
		return this.fusionLog;
	}
	
}
