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
@Table(name = "resource_schedule")
public class ResourceSchedule implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "resource_schedule_func", sequenceName = "resource_schedule_id_resource_schedule_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_schedule_func")
	@Column(name = "id_resource_schedule", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_fk", referencedColumnName = "id_resource")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name = "id_schedule_fk", referencedColumnName = "id_schedule")
	private Schedule schedule;
	
	public ResourceSchedule(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setResource(Resource resource){
		this.resource = resource;
	}
	
	public Resource getResource(){
		return this.resource;
	}
	
	public void setSchedule(Schedule schedule){
		this.schedule = schedule;
	}
	
	public Schedule getSchedule(){
		return this.schedule;
	}
	
}
