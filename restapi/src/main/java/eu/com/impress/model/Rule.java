package eu.com.impress.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "rule")
public class Rule implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "rule_func", sequenceName = "rule_id_rule_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rule_func")
	@Column(name = "id_rule", nullable = false)
	private Integer id;
	
	@Column(name = "rule_name", nullable = false)
	private String ruleName;
	
	@Column(name = "rule_text", nullable = false)
	private String ruleText;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	public Rule(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setRuleName(String ruleName){
		this.ruleName = ruleName;
	}
	
	public String getRuleName(){
		return this.ruleName;
	}
	
	public void setRuleText(String ruleText){
		this.ruleText = ruleText;
	}
	
	public String getRuleText(){
		return this.ruleText;
	}
	
	public void setDependentIndependent(int dependentIndependent){
		this.dependentIndependent = dependentIndependent;
	}
	
	public int getDependentIndependent(){
		return this.dependentIndependent;
	}
	
}
