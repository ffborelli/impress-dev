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
@Table(name = "resource")
public class Resource implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "resource_func", sequenceName = "resource_id_resource_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_func")
	@Column(name = "id_resource", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_type_fk", referencedColumnName = "id_resource_type")
	private ResourceType resourceType;
	
	@ManyToOne
	@JoinColumn(name = "id_place_fk", referencedColumnName = "id_place")
	private Place place;

	@Column(name = "uid")
	private String uid;

	@Column(name = "is_reserved", nullable = false)
	private boolean isReserved;
	
	@ManyToOne
	@JoinColumn(name = "dependence_fusion_log_fk", referencedColumnName = "id_fusion_log")
	private FusionLog fusionLog;
	
	@Column(name = "mqtt_topic")
	private String mqttTopic;
	
	@Column(name = "mqtt_address")
	private String mqttAddress;
	
	public Resource(){
		
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
	
	public void setDependentIndependent(int dependentIndependent){
		this.dependentIndependent = dependentIndependent;
	}
	
	public int getDependentIndependent(){
		return this.dependentIndependent;
	}
	
	public void setResourceType(ResourceType resourceType){
		this.resourceType = resourceType;
	}
	
	public ResourceType getResourceType(){
		return this.resourceType;
	}
	
	public void setPlace(Place place){
		this.place = place;
	}
	
	public Place getPlace(){
		return this.place;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public boolean isReserved() {
		return isReserved;
	}

	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	public String getMqttTopic() {
		return mqttTopic;
	}

	public void setMqttTopic(String mqttTopic) {
		this.mqttTopic = mqttTopic;
	}

	public String getMqttAddress() {
		return mqttAddress;
	}

	public void setMqttAddress(String mqttAddress) {
		this.mqttAddress = mqttAddress;
	}
	
	
	
//	public void setFusionLog(FusionLog fusionLog){
//		this.fusionLog = fusionLog;
//	}
//	
//	public FusionLog getFusionLog(){
//		return this.fusionLog;
//	}
	
	
	
}
