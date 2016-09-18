package br.ufabc.impress.model;
import com.espertech.esper.client.EPStatement;

public class EsperStatement {
	
	private EPStatement cepStatement;
	private int idFusion;
	
	public EPStatement getCepStatement() {
		return cepStatement;
	}
	public void setCepStatement(EPStatement cepStatement) {
		this.cepStatement = cepStatement;
	}
	public int getIdFusion() {
		return idFusion;
	}
	public void setIdFusion(int idFusion) {
		this.idFusion = idFusion;
	}
	
	

}