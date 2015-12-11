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
@Table(name = "rule_context")
public class RuleContext implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "rule_context_func", sequenceName = "rule_context_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rule_context_func")
	@Column(name = "id_rule_context", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_rule_fk", referencedColumnName = "id_rule")
	private Rule rule;
	
	@ManyToOne
	@JoinColumn(name = "id_context_fk", referencedColumnName = "id_context")
	private Context context;
	
	public RuleContext(){
		
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
	
	public void setContext(Context context){
		this.context = context;
	}
	
	public Context getContext(){
		return this.context;
	}
	
}
