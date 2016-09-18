package br.ufabc.impress.model;

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
@Table(name = "action")
public class Action implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "action_func", sequenceName = "action_id_action_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "action_func")
	@Column(name = "id_action", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_rule_fk", referencedColumnName = "id_rule")
	private Rule rule;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_fk", referencedColumnName = "id_resource")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name = "id_rsc_action_type_fk", referencedColumnName = "id_rsc_action_type")
	private ResourceActionType resourceActionType;
	
	public Action(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setRule(Rule rule){
		this.rule = rule;
	}
	
	public Rule getRule(){
		return this.rule;
	}
	
	public void setResource(Resource resource){
		this.resource = resource;
	}
	
	public Resource getResource(){
		return this.resource;
	}
	
	public void setResourceActionType(ResourceActionType resourceActionType){
		this.resourceActionType = resourceActionType;
	}
	
	public ResourceActionType getResourceActionType(){
		return this.resourceActionType;
	}
	
}
