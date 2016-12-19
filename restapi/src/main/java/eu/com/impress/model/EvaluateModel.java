package eu.com.impress.model;

import java.util.List;

import eu.com.impress.facade.EvalSdpFacade;
import eu.com.impress.util.EvalUtil;

public class EvaluateModel {
	
	public EvaluateModel(int limit){
		this.limit = limit;
		list = this.getEvalSdpFacade().getLastRegisters(limit);
	}
	
	private EvalSdpFacade evalSdpFacade;

	private List<EvalSdp> list = null;
	
	private double avgP1P2 = 0;
	private double avgP2P3 = 0;
	private double avgP3P4 = 0;
	private double avgP4P5 = 0;
	private double avgP5P6 = 0;
	
	private double desvP1P2 = 0;
	private double desvP2P3 = 0;
	private double desvP3P4 = 0;
	private double desvP4P5 = 0;
	private double desvP5P6 = 0;
	
	private int count = 0;
	
	private int limit = 0;

	public void evalAvg(){
		
		for (int i = 0; i < list.size();i++){
//			if ( list.get(i).getP2(). > 0 & list.get(i).getP3() != null && list.get(i).getP4() != null
//					&& list.get(i).getP5() != null && list.get(i).getP6() != null){
				
				count++;
				
				avgP1P2 += EvalUtil.getDateDiff(list.get(i).getP1(), list.get(i).getP2());
				avgP2P3 += EvalUtil.getDateDiff(list.get(i).getP2(), list.get(i).getP3());
				avgP3P4 += EvalUtil.getDateDiff(list.get(i).getP3(), list.get(i).getP4());
				avgP4P5 += EvalUtil.getDateDiff(list.get(i).getP4(), list.get(i).getP5());
				//avgP5P6 += EvalUtil.getDateDiff(list.get(i).getP5(), list.get(i).getP6());
				
			//}
		}
		
		avgP1P2 = avgP1P2/count;
		avgP2P3 = avgP2P3/count;
		avgP3P4 = avgP3P4/count;
		avgP4P5 = avgP4P5/count;
		//avgP5P6 = avgP5P6/count;
	}
	
	public void evalDesv(){
		
		for (int i = 0; i < list.size();i++){
//			if (list.get(i).getP1() != null & list.get(i).getP2() != null & list.get(i).getP3() != null && list.get(i).getP4() != null
//					&& list.get(i).getP5() != null && list.get(i).getP6() != null){
				
				desvP1P2 += Math.pow((avgP1P2 - (EvalUtil.getDateDiff(list.get(i).getP1(), list.get(i).getP2()))), 2);
				desvP2P3 += Math.pow((avgP2P3 - (EvalUtil.getDateDiff(list.get(i).getP2(), list.get(i).getP3()))), 2);
				desvP3P4 += Math.pow((avgP3P4 - (EvalUtil.getDateDiff(list.get(i).getP3(), list.get(i).getP4()))), 2);
				desvP4P5 += Math.pow((avgP4P5 - (EvalUtil.getDateDiff(list.get(i).getP4(), list.get(i).getP5()))), 2);
				//desvP5P6 += Math.pow((avgP5P6 - (EvalUtil.getDateDiff(list.get(i).getP5(), list.get(i).getP6()))), 2);
			//}
		}
		
		desvP1P2 = Math.sqrt(desvP1P2/(count));
		desvP2P3 = Math.sqrt(desvP2P3/(count));
		desvP3P4 = Math.sqrt(desvP3P4/(count));
		desvP4P5 = Math.sqrt(desvP4P5/(count));
		//desvP5P6 = Math.sqrt(desvP5P6/(count-1));
	}
	
	public List<EvalSdp> getList() {
		return list;
	}

	public void setList(List<EvalSdp> list) {
		this.list = list;
	}

	public double getAvgP1P2() {
		return avgP1P2;
	}

	public void setAvgP1P2(double avgP1P2) {
		this.avgP1P2 = avgP1P2;
	}

	public double getAvgP2P3() {
		return avgP2P3;
	}

	public void setAvgP2P3(double avgP2P3) {
		this.avgP2P3 = avgP2P3;
	}

	public double getAvgP3P4() {
		return avgP3P4;
	}

	public void setAvgP3P4(double avgP3P4) {
		this.avgP3P4 = avgP3P4;
	}

	public double getAvgP4P5() {
		return avgP4P5;
	}

	public void setAvgP4P5(double avgP4P5) {
		this.avgP4P5 = avgP4P5;
	}

	public double getAvgP5P6() {
		return avgP5P6;
	}

	public void setAvgP5P6(double avgP5P6) {
		this.avgP5P6 = avgP5P6;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public double getDesvP1P2() {
		return desvP1P2;
	}

	public void setDesvP1P2(double desvP1P2) {
		this.desvP1P2 = desvP1P2;
	}

	public double getDesvP2P3() {
		return desvP2P3;
	}

	public void setDesvP2P3(double desvP2P3) {
		this.desvP2P3 = desvP2P3;
	}

	public double getDesvP3P4() {
		return desvP3P4;
	}

	public void setDesvP3P4(double desvP3P4) {
		this.desvP3P4 = desvP3P4;
	}

	public double getDesvP4P5() {
		return desvP4P5;
	}

	public void setDesvP4P5(double desvP4P5) {
		this.desvP4P5 = desvP4P5;
	}

	public double getDesvP5P6() {
		return desvP5P6;
	}

	public void setDesvP5P6(double desvP5P6) {
		this.desvP5P6 = desvP5P6;
	}

	private EvalSdpFacade getEvalSdpFacade(){
		if (evalSdpFacade == null){
			evalSdpFacade = new EvalSdpFacade();
		}
		return evalSdpFacade;
	}
	
}
