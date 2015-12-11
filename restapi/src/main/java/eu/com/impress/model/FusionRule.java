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
@Table(name = "fusion_rule")
public class FusionRule implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "fusion_rule_func", sequenceName = "fusion_rule_id_fusion_rule_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fusion_rule_func")
	@Column(name = "id_fusion_rule", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_fusion_fk", referencedColumnName = "id_fusion")
	private Fusion fusion;
	
	@ManyToOne
	@JoinColumn(name = "id_rule_fk", referencedColumnName = "id_rule")
	private Rule rule;
	
	public FusionRule(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setFusion(Fusion fusion){
		this.fusion = fusion;
	}
	
	public Fusion getFusion(){
		return this.fusion;
	}
	
	public void setRule(Rule rule){
		this.rule = rule;
	}
	
	public Rule getRule(){
		return this.rule;
	}
	
}
