package br.ufabc.impress.model;

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
@Table(name = "resource_type")
public class ResourceType implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int SENSOR = 0;
	public static final int ACTUATOR = 1;
	
	@Id
	@SequenceGenerator(name = "resource_type_func", sequenceName = "resource_type_id_resource_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_type_func")
	@Column(name = "id_resource_type", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "sensor_0_actuator_1", nullable = false)
	private int sensorActuator;
	
	public ResourceType(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setSensorActuator(int sensorActuator){
		this.sensorActuator = sensorActuator;
	}
	
	public int getSensorActuator(){
		return this.sensorActuator;
	}
	
}
