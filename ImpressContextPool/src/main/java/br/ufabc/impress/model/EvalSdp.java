package br.ufabc.impress.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the eval_sdp database table.
 * 	uid bigint NOT NULL,
	exp int NOT NULL,
	resource_type int references resource_type(id_resource_type), 
	resource int NOT NULL references resource(id_resource),
	rep int NOT NULL,
	P1 timestamp,
	P2 timestamp,
	P3 timestamp,
	P4 timestamp,
	P5 timestamp,
	P6 timestamp
 */
@Entity
@Table(name="eval_sdp")
@NamedQuery(name="EvalSdp.findAll", query="SELECT e FROM EvalSdp e")
public class EvalSdp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "eval_sdp_func", sequenceName = "eval_sdp_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eval_sdp_func")
	@Column(name = "id", nullable = false)
	private Long uid;

	//private Long uid;
	
	private Integer exp;
	
	@Column(name = "resource_type")
	private Integer resourceType;
	
	private Integer resource;
	
	private Integer rep;

	private Timestamp P1;
	private Timestamp P2;
	private Timestamp P3;
	private Timestamp P4;
	private Timestamp P5;
	private Timestamp P6;
	
	
	public EvalSdp() {
	}

	public Long getUid() {
		return uid;
	}


	public void setUid(Long uid) {
		this.uid = uid;
	}


	public Integer getExp() {
		return exp;
	}


	public void setExp(Integer exp) {
		this.exp = exp;
	}


	public Integer getResourceType() {
		return resourceType;
	}


	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}


	public Integer getResource() {
		return resource;
	}


	public void setResource(Integer resource) {
		this.resource = resource;
	}


	public Integer getRep() {
		return rep;
	}


	public void setRep(Integer rep) {
		this.rep = rep;
	}


	public Timestamp getP1() {
		return P1;
	}


	public void setP1(Timestamp p1) {
		P1 = p1;
	}


	public Timestamp getP2() {
		return P2;
	}


	public void setP2(Timestamp p2) {
		P2 = p2;
	}


	public Timestamp getP3() {
		return P3;
	}


	public void setP3(Timestamp p3) {
		P3 = p3;
	}


	public Timestamp getP4() {
		return P4;
	}


	public void setP4(Timestamp p4) {
		P4 = p4;
	}


	public Timestamp getP5() {
		return P5;
	}


	public void setP5(Timestamp p5) {
		P5 = p5;
	}


	public Timestamp getP6() {
		return P6;
	}


	public void setP6(Timestamp p6) {
		P6 = p6;
	}

}