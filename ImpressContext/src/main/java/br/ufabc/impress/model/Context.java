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
@Table(name = "context")
public class Context implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int ENABLE = 0;
	public static final int DISABLE = 1;
	
	@Id
	@SequenceGenerator(name = "context_func", sequenceName = "context_id_context_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "context_func")
	@Column(name = "id_context", nullable = false)
	private Integer id;
	
	@Column(name = "context_name", nullable = false)
	private String contextName;
	
	@Column(name = "enable_0_disable_1", nullable = false)
	private int enableDisable;
	
	@ManyToOne
	@JoinColumn(name = "id_context_type_fk", referencedColumnName = "id_context_type")
	private ContextType contextType;
	
	@ManyToOne
	@JoinColumn(name = "id_context_fk", referencedColumnName = "id_context")
	private Context context;
	
	@ManyToOne
	@JoinColumn(name = "id_place_fk", referencedColumnName = "id_place")
	private Place place;
	
	public Context(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setContextName(String contextName){
		this.contextName = contextName;
	}
	
	public String getContextName(){
		return this.contextName;
	}
	
	public void setEnableDisable(int enableDisable){
		this.enableDisable = enableDisable;
	}
	
	public int getEnableDisable(){
		return this.enableDisable;
	}
	
	public void setContextType(ContextType contextType){
		this.contextType = contextType;
	}
	
	public ContextType getContextType(){
		return this.contextType;
	}
	
	public void setContext(Context context){
		this.context = context;
	}
	
	public Context getContext(){
		return this.context;
	}
	
	public void setPlace(Place place){
		this.place = place;
	}
	
	public Place getPlace(){
		return this.place;
	}
	
}
