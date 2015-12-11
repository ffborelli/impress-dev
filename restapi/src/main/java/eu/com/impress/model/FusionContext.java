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
@Table(name = "fusion_context")
public class FusionContext implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "fusion_context_func", sequenceName = "fusion_context_id_fusion_context_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fusion_context_func")
	@Column(name = "id_fusion_context", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_fusion_fk", referencedColumnName = "id_fusion")
	private Fusion fusion;
	
	@ManyToOne
	@JoinColumn(name = "id_context_fk", referencedColumnName = "id_context")
	private Context context;
	
	public FusionContext(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setContext(Context context){
		this.context = context;
	}
	
	public Context getContext(){
		return this.context;
	}
	
	public void setFusion(Fusion fusion){
		this.fusion = fusion;
	}
	
	public Fusion getFusion(){
		return this.fusion;
	}
	
}
