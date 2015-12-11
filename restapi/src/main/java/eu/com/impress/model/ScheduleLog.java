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
@Table(name = "schedule_log")
public class ScheduleLog implements Serializable {
	
public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "schedule_log_func", sequenceName = "schedule_log_id_schedule_log_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_log_func")
	@Column(name = "id_schedule_log", nullable = false)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_schedule_fk", referencedColumnName = "id_schedule")
	private Schedule schedule;
	
	public ScheduleLog(){
		
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
	
	public void setSchedule(Schedule schedule){
		this.schedule = schedule;
	}
	
	public Schedule getSchedule(){
		return this.schedule;
	}
	
}
