package eu.com.impress.model;

public class MonitorPerformance {
	
	private double processorUsage;
	private long memoryAvaliable;
	private long memoryTotal;
	private long diskReads;
	private long diskWrites;
	
	public double getProcessorUsage() {
		return processorUsage;
	}
	public void setProcessorUsage(double processorUsage) {
		this.processorUsage = processorUsage;
	}
	public long getMemoryAvaliable() {
		return memoryAvaliable;
	}
	public void setMemoryAvaliable(long memoryAvaliable) {
		this.memoryAvaliable = memoryAvaliable;
	}
	public long getMemoryTotal() {
		return memoryTotal;
	}
	public void setMemoryTotal(long memoryTotal) {
		this.memoryTotal = memoryTotal;
	}
	public long getDiskReads() {
		return diskReads;
	}
	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}
	public long getDiskWrites() {
		return diskWrites;
	}
	public void setDiskWrites(long diskWrites) {
		this.diskWrites = diskWrites;
	}
		
}
