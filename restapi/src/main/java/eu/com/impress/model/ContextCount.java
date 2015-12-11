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
@Table(name = "context_count")
public class ContextCount implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "context_count_func", sequenceName = "context_count_id_context_count_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "context_count_func")
	@Column(name = "id_context_count", nullable = false)
	private Integer id;
	
	@Column(name = "context_sequence", nullable = false)
	private String contextSequence;
	
	@Column(name = "context_count", nullable = false)
	private int contextCount;
	
	@Column(name = "context_registered", nullable = false)
	private int contextRegistered;
	
	public ContextCount(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setContextSequence(String contextSequence){
		this.contextSequence = contextSequence;
	}
	
	public String getContextSequence(){
		return this.contextSequence;
	}
	
	public void setContextCount(int contextCount){
		this.contextCount = contextCount;
	}
	
	public int getContextCount(){
		return this.contextCount;
	}
	
	public void setContextRegistered(int contextRegistered){
		this.contextRegistered = contextRegistered;
	}
	
	public int getContextRegistred(){
		return this.contextRegistered;
	}
	
}
