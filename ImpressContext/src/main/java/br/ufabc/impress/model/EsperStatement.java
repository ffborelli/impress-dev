package br.ufabc.impress.model;
import com.espertech.esper.client.EPStatement;

public class EsperStatement {
	
	private EPStatement cepStatement;
	private long idFusion;
	
	public EPStatement getCepStatement() {
		return cepStatement;
	}
	public void setCepStatement(EPStatement cepStatement) {
		this.cepStatement = cepStatement;
	}
	public long getIdFusion() {
		return idFusion;
	}
	public void setIdFusion(long idFusion) {
		this.idFusion = idFusion;
	}
	
	

}