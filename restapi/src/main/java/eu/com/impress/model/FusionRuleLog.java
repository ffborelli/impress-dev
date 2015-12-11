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
@Table(name = "fusion_rule_log")
public class FusionRuleLog implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "fusion_rule_log_func", sequenceName = "fusion_rule_log_id_fusion_rule_log_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fusion_rule_log_func")
	@Column(name = "id_fusion_rule_log", nullable = false)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_fusion_log_fk", referencedColumnName = "id_fusion_log")
	private FusionLog fusionLog;
	
	@ManyToOne
	@JoinColumn(name = "id_rule_action_log_fk", referencedColumnName = "id_rule_action_log")
	private RuleActionLog ruleActionLog;
	
	public FusionRuleLog(){
		
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
	
	public void setFusionLog(FusionLog fusionLog){
		this.fusionLog = fusionLog;
	}
	
	public FusionLog getFusionLog(){
		return this.fusionLog;
	}
	
	public void setRuleActionLog(RuleActionLog ruleActionLog){
		this.ruleActionLog = ruleActionLog;
	}
	
	public RuleActionLog getRuleActionLog(){
		return this.ruleActionLog;
	}
	
}
