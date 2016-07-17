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
@Table(name = "rule_action_log")
public class RuleActionLog implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "rule_action_log_func", sequenceName = "rule_action_log_id_rule_action_log_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rule_action_log_func")
	@Column(name = "id_rule_action_log", nullable = false)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_fk", referencedColumnName = "id_resource")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name = "id_rule_fk", referencedColumnName = "id_rule")
	private Rule rule;
	
	@ManyToOne
	@JoinColumn(name = "id_rsc_action_type_fk", referencedColumnName = "id_rsc_action_type")
	private ResourceActionType resourceActionType;
	
	@Column(name = "tracker")
	private boolean tracker;
	
	public RuleActionLog(){
		
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
	
	public void setResource(Resource resource){
		this.resource = resource;
	}
	
	public Resource getResource(){
		return this.resource;
	}
	
	public void setRule(Rule rule){
		this.rule = rule;
	}
	
	public Rule getRule(){
		return this.rule;
	}
	
	public void setResourceActionType(ResourceActionType resourceActionType){
		this.resourceActionType = resourceActionType;
	}
	
	public ResourceActionType getResourceActionType(){
		return this.resourceActionType;
	}

	public boolean isTracker() {
		return tracker;
	}

	public void setTracker(boolean tracker) {
		this.tracker = tracker;
	}

}
