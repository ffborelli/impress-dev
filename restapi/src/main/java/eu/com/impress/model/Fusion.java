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
@Table(name = "fusion")
public class Fusion implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "fusion_func", sequenceName = "fusion_id_fusion_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fusion_func")
	@Column(name = "id_fusion", nullable = false)
	private Integer id;
	
	@Column(name = "fusion_name", nullable = false)
	private String fusionName;
	
	@Column(name = "fusion_text", nullable = false)
	private String fusionText;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	@Column(name = "is_running")
	private boolean isRunning;
	
	@Column(name = "is_avaliable")
	private boolean isAvaliable;
	
	public Fusion(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setFusionName(String fusionName){
		this.fusionName = fusionName;
	}
	
	public String getFusionName(){
		return this.fusionName;
	}
	
	public void setFusionText(String fusionText){
		this.fusionText = fusionText;
	}
	
	public String getFusionText(){
		return this.fusionText;
	}
	
	public void setDependentIndependent(int dependentIndependent){
		this.dependentIndependent = dependentIndependent;
	}
	
	public int getDependentIndependent(){
		return this.dependentIndependent;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public boolean isAvaliable() {
		return isRunning;
	}

	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
	}
	
}
