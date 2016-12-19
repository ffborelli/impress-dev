package eu.com.impress.model;

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

	private Timestamp p1;
	
	@Column(name = "p1_disk_read")
	private long p1DiskRead;
	
	@Column(name = "p1_disk_write")
	private long p1DiskWrite;
	
	@Column(name = "p1_mem_usage")
	private long p1MemUsage;
	
	@Column(name = "p1_mem_total")
	private long p1MemTotal;
	
	@Column(name = "p1_proc_usage")
	private double p1ProcUsage;

	private Timestamp p2;
	
	@Column(name = "p2_disk_read")
	private long p2DiskRead;
	
	@Column(name = "p2_disk_write")
	private long p2DiskWrite;
	
	@Column(name = "p2_mem_usage")
	private long p2MemUsage;
	
	@Column(name = "p2_mem_total")
	private long p2MemTotal;
	
	@Column(name = "p2_proc_usage")
	private double p2ProcUsage;
	
	private Timestamp p3;
	
	@Column(name = "p3_disk_read")
	private long p3DiskRead;
	
	@Column(name = "p3_disk_write")
	private long p3DiskWrite;
	
	@Column(name = "p3_mem_usage")
	private long p3MemUsage;
	
	@Column(name = "p3_mem_total")
	private long p3MemTotal;
	
	@Column(name = "p3_proc_usage")
	private double p3ProcUsage;
	
	private Timestamp p4;
	
	@Column(name = "p4_disk_read")
	private long p4DiskRead;
	
	@Column(name = "p4_disk_write")
	private long p4DiskWrite;
	
	@Column(name = "p4_mem_usage")
	private long p4MemUsage;
	
	@Column(name = "p4_mem_total")
	private long p4MemTotal;
	
	@Column(name = "p4_proc_usage")
	private double p4ProcUsage;
	
	private Timestamp p5;
	
	@Column(name = "p5_disk_read")
	private long p5DiskRead;
	
	@Column(name = "p5_disk_write")
	private long p5DiskWrite;
	
	@Column(name = "p5_mem_usage")
	private long p5MemUsage;
	
	@Column(name = "p5_mem_total")
	private long p5MemTotal;
	
	@Column(name = "p5_proc_usage")
	private double p5ProcUsage;
	
	private Timestamp p6;
	
	@Column(name = "p6_disk_read")
	private long p6DiskRead;
	
	@Column(name = "p6_disk_write")
	private long p6DiskWrite;
	
	@Column(name = "p6_mem_usage")
	private long p6MemUsage;
	
	@Column(name = "p6_mem_total")
	private long p6MemTotal;
	
	@Column(name = "p6_proc_usage")
	private double p6ProcUsage;
	
	
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
		return p1;
	}

	public void setP1(Timestamp p1) {
		this.p1 = p1;
	}

	public long getP1DiskRead() {
		return p1DiskRead;
	}

	public void setP1DiskRead(long p1DiskRead) {
		this.p1DiskRead = p1DiskRead;
	}

	public long getP1DiskWrite() {
		return p1DiskWrite;
	}

	public void setP1DiskWrite(long p1DiskWrite) {
		this.p1DiskWrite = p1DiskWrite;
	}

	public long getP1MemUsage() {
		return p1MemUsage;
	}

	public void setP1MemUsage(long p1MemUsage) {
		this.p1MemUsage = p1MemUsage;
	}

	public long getP1MemTotal() {
		return p1MemTotal;
	}

	public void setP1MemTotal(long p1MemTotal) {
		this.p1MemTotal = p1MemTotal;
	}

	public double getP1ProcUsage() {
		return p1ProcUsage;
	}

	public void setP1ProcUsage(double p1ProcUsage) {
		this.p1ProcUsage = p1ProcUsage;
	}

	public Timestamp getP2() {
		return p2;
	}

	public void setP2(Timestamp p2) {
		this.p2 = p2;
	}

	public long getP2DiskRead() {
		return p2DiskRead;
	}

	public void setP2DiskRead(long p2DiskRead) {
		this.p2DiskRead = p2DiskRead;
	}

	public long getP2DiskWrite() {
		return p2DiskWrite;
	}

	public void setP2DiskWrite(long p2DiskWrite) {
		this.p2DiskWrite = p2DiskWrite;
	}

	public long getP2MemUsage() {
		return p2MemUsage;
	}

	public void setP2MemUsage(long p2MemUsage) {
		this.p2MemUsage = p2MemUsage;
	}

	public long getP2MemTotal() {
		return p2MemTotal;
	}

	public void setP2MemTotal(long p2MemTotal) {
		this.p2MemTotal = p2MemTotal;
	}

	public double getP2ProcUsage() {
		return p2ProcUsage;
	}

	public void setP2ProcUsage(double p2ProcUsage) {
		this.p2ProcUsage = p2ProcUsage;
	}

	public Timestamp getP3() {
		return p3;
	}

	public void setP3(Timestamp p3) {
		this.p3 = p3;
	}

	public long getP3DiskRead() {
		return p3DiskRead;
	}

	public void setP3DiskRead(long p3DiskRead) {
		this.p3DiskRead = p3DiskRead;
	}

	public long getP3DiskWrite() {
		return p3DiskWrite;
	}

	public void setP3DiskWrite(long p3DiskWrite) {
		this.p3DiskWrite = p3DiskWrite;
	}

	public long getP3MemUsage() {
		return p3MemUsage;
	}

	public void setP3MemUsage(long p3MemUsage) {
		this.p3MemUsage = p3MemUsage;
	}

	public long getP3MemTotal() {
		return p3MemTotal;
	}

	public void setP3MemTotal(long p3MemTotal) {
		this.p3MemTotal = p3MemTotal;
	}

	public double getP3ProcUsage() {
		return p3ProcUsage;
	}

	public void setP3ProcUsage(double p3ProcUsage) {
		this.p3ProcUsage = p3ProcUsage;
	}

	public Timestamp getP4() {
		return p4;
	}

	public void setP4(Timestamp p4) {
		this.p4 = p4;
	}

	public long getP4DiskRead() {
		return p4DiskRead;
	}

	public void setP4DiskRead(long p4DiskRead) {
		this.p4DiskRead = p4DiskRead;
	}

	public long getP4DiskWrite() {
		return p4DiskWrite;
	}

	public void setP4DiskWrite(long p4DiskWrite) {
		this.p4DiskWrite = p4DiskWrite;
	}

	public long getP4MemUsage() {
		return p4MemUsage;
	}

	public void setP4MemUsage(long p4MemUsage) {
		this.p4MemUsage = p4MemUsage;
	}

	public long getP4MemTotal() {
		return p4MemTotal;
	}

	public void setP4MemTotal(long p4MemTotal) {
		this.p4MemTotal = p4MemTotal;
	}

	public double getP4ProcUsage() {
		return p4ProcUsage;
	}

	public void setP4ProcUsage(double p4ProcUsage) {
		this.p4ProcUsage = p4ProcUsage;
	}

	public Timestamp getP5() {
		return p5;
	}

	public void setP5(Timestamp p5) {
		this.p5 = p5;
	}

	public long getP5DiskRead() {
		return p5DiskRead;
	}

	public void setP5DiskRead(long p5DiskRead) {
		this.p5DiskRead = p5DiskRead;
	}

	public long getP5DiskWrite() {
		return p5DiskWrite;
	}

	public void setP5DiskWrite(long p5DiskWrite) {
		this.p5DiskWrite = p5DiskWrite;
	}

	public long getP5MemUsage() {
		return p5MemUsage;
	}

	public void setP5MemUsage(long p5MemUsage) {
		this.p5MemUsage = p5MemUsage;
	}

	public long getP5MemTotal() {
		return p5MemTotal;
	}

	public void setP5MemTotal(long p5MemTotal) {
		this.p5MemTotal = p5MemTotal;
	}

	public double getP5ProcUsage() {
		return p5ProcUsage;
	}

	public void setP5ProcUsage(double p5ProcUsage) {
		this.p5ProcUsage = p5ProcUsage;
	}

	public Timestamp getP6() {
		return p6;
	}

	public void setP6(Timestamp p6) {
		this.p6 = p6;
	}

	public long getP6DiskRead() {
		return p6DiskRead;
	}

	public void setP6DiskRead(long p6DiskRead) {
		this.p6DiskRead = p6DiskRead;
	}

	public long getP6DiskWrite() {
		return p6DiskWrite;
	}

	public void setP6DiskWrite(long p6DiskWrite) {
		this.p6DiskWrite = p6DiskWrite;
	}

	public long getP6MemUsage() {
		return p6MemUsage;
	}

	public void setP6MemUsage(long p6MemUsage) {
		this.p6MemUsage = p6MemUsage;
	}

	public long getP6MemTotal() {
		return p6MemTotal;
	}

	public void setP6MemTotal(long p6MemTotal) {
		this.p6MemTotal = p6MemTotal;
	}

	public double getP6ProcUsage() {
		return p6ProcUsage;
	}

	public void setP6ProcUsage(double p6ProcUsage) {
		this.p6ProcUsage = p6ProcUsage;
	}
	
	

}