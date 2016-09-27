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
@Table(name = "place")
public class Place implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "place_func", sequenceName = "place_id_place_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_func")
	@Column(name = "id_place", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	@ManyToOne
	@JoinColumn(name = "id_place_type_fk", referencedColumnName = "id_place_type")
	private PlaceType placeType;
	
	@ManyToOne
	@JoinColumn(name = "id_place_fk", referencedColumnName = "id_place")
	private Place place;
	
	public Place(){
		
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
	
	public void setPlaceType(PlaceType placeType){
		this.placeType = placeType;
	}
	
	public PlaceType getPlaceType(){
		return this.placeType;
	}
	
	public void setPlace(Place place){
		this.place = place;
	}
	
	public Place getPlace(){
		return this.place;
	}
	
}
