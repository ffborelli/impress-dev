package br.ufabc.impress.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the eval_sdp database table.
 * 
 */
@Entity
@Table(name="eval_sdp")
@NamedQuery(name="EvalSdp.findAll", query="SELECT e FROM EvalSdp e")
public class EvalSdp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "eval_sdp_func", sequenceName = "eval_sdp_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eval_sdp_func")
	private Integer id;

	private String description;

	private Timestamp duration;
	
	@Column(name = "duration_mil")
	private long durationMil;

	private Timestamp finish;

	private String module;

	private String name;

	private Timestamp start;

	public EvalSdp() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDuration() {
		return this.duration;
	}

	public void setDuration(Timestamp duration) {
		this.duration = duration;
	}

	public long getDurationMil() {
		return durationMil;
	}

	public void setDurationMil(long durationMil) {
		this.durationMil = durationMil;
	}

	public Timestamp getFinish() {
		return this.finish;
	}

	public void setFinish(Timestamp finish) {
		this.finish = finish;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStart() {
		return this.start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

}