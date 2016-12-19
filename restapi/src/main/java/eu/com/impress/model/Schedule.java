package eu.com.impress.model;

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
@Table(name = "schedule")
public class Schedule implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "schedule_func", sequenceName = "schedule_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_func")
	@Column(name = "id_schedule", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "initial", nullable = false)
	private Date initialDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "final", nullable = false)
	private Date finalDate;
	
	@Column(name = "priority_time", nullable = false)
	private String priorityTime;
	
	@Column(name = "recurrence", nullable = false)
	private String recurrence;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "id_place_fk", referencedColumnName = "id_place")
	private Place place;
	
	@ManyToOne
	@JoinColumn(name = "id_rule_fk", referencedColumnName = "id_rule")
	private Rule rule;
	
	public Schedule(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setInitialDate(Date initialDate){
		this.initialDate = initialDate;
	}
	
	public Date getInitialDate(){
		return this.initialDate;
	}
	
	public void setFinalDate(Date finalDate){
		this.finalDate = finalDate;
	}
	
	public Date getFinalDate(){
		return this.finalDate;
	}
	
	public void setPriorityTime(String priorityTime){
		this.priorityTime = priorityTime;
	}
	
	public String getPriorityTime(){
		return this.priorityTime;
	}
	
	public void setRecurrence(String recurrence){
		this.recurrence = recurrence;
	}
	
	public String getRecurrence(){
		return this.recurrence;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setPlace(Place place){
		this.place = place;
	}
	
	public Place getPlace(){
		return this.place;
	}
	
	public void setRule(Rule rule){
		this.rule = rule;
	}
	
	public Rule getRule(){
		return this.rule;
	}
	
}
